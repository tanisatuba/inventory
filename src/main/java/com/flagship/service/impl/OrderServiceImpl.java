package com.flagship.service.impl;

import com.flagship.constant.enums.*;
import com.flagship.dto.request.*;
import com.flagship.dto.response.*;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.*;
import com.flagship.repository.*;
import com.flagship.service.OrderService;
import com.flagship.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderMasterRepository orderMasterRepository;
  private final OrderDetailsRepository orderDetailsRepository;
  private final CustomerRepository customerRepository;
  private final ImportDetailsRepository importDetailsRepository;
  private final ProductRepository productRepository;
  private final ImportMasterRepository importMasterRepository;
  private final UserRepository userRepository;
  private final StockRepository stockRepository;
  private final SalesPersonRepository salesPersonRepository;
  private final SaleRepository saleRepository;
  private final ReturnsRepository returnsRepository;
  private final SupplierRepository supplierRepository;
  private final OrderBillsRepository orderBillsRepository;
  private final BranchRepository branchRepository;
  private final RequisitionRepository requisitionRepository;
  DecimalFormat decimalFormat = new DecimalFormat("#.###");

  @Autowired
  public OrderServiceImpl(OrderMasterRepository orderMasterRepository, OrderDetailsRepository orderDetailsRepository,
                          CustomerRepository customerRepository, ImportDetailsRepository importDetailsRepository,
                          ProductRepository productRepository, ImportMasterRepository importMasterRepository,
                          UserRepository userRepository, StockRepository stockRepository,
                          SalesPersonRepository salesPersonRepository, SaleRepository saleRepository,
                          ReturnsRepository returnsRepository, SupplierRepository supplierRepository,
                          OrderBillsRepository orderBillsRepository, BranchRepository branchRepository,
                          RequisitionRepository requisitionRepository) {
    this.orderMasterRepository = orderMasterRepository;
    this.orderDetailsRepository = orderDetailsRepository;
    this.customerRepository = customerRepository;
    this.importDetailsRepository = importDetailsRepository;
    this.productRepository = productRepository;
    this.importMasterRepository = importMasterRepository;
    this.userRepository = userRepository;
    this.stockRepository = stockRepository;
    this.salesPersonRepository = salesPersonRepository;
    this.saleRepository = saleRepository;
    this.returnsRepository = returnsRepository;
    this.supplierRepository = supplierRepository;
    this.orderBillsRepository = orderBillsRepository;
    this.branchRepository = branchRepository;
    this.requisitionRepository = requisitionRepository;
  }

  @Override
  public OrderResponse createOrder(OrderMasterRequest orderMasterRequest) {
    List<OrderDetailsRequest> orderDetailsRequestList = orderMasterRequest.getOrderDetails();
    OrderMaster orderMaster = new OrderMaster();
    Long orderId = getOrderId();
    orderMaster.setOrderId(orderId);
    orderMaster.setCustomer(getCustomer(orderMasterRequest.getCustomer().getValue()));
    orderMaster.setCompanyName(orderMasterRequest.getCompanyName());
    if (orderMasterRequest.getSupplierId() != null && !orderMasterRequest.getSupplierId().isEmpty()) {
      orderMaster.setSupplier(getSupplier(orderMasterRequest.getSupplierId()));
      if (orderMasterRequest.getBranch() != null) {
        orderMaster.setBranch(getBranch(orderMasterRequest.getBranch().getValue(), getSupplier(orderMasterRequest.getSupplierId())));
      }
    }
    orderMaster.setCustomerType(CustomerType.fromName(orderMasterRequest.getCustomerType().getName()));
    orderMaster.setOrderDate(DateUtil.getZoneDateTime(dateConversion(orderMasterRequest.getOrderDate()) + "T00:00:00"));
    orderMaster.setDeliveryDate(DateUtil.getZoneDateTime(dateConversion(orderMasterRequest.getDeliveryDate()) + "T00:00:00"));
    orderMaster.setCreditTerm(orderMasterRequest.getCreditTerm());
    orderMaster.setChallan(orderMasterRequest.getChallanNo() != null ? orderMasterRequest.getChallanNo() : 0);
    orderMaster.setAddress(orderMasterRequest.getAddress());
    orderMaster.setOrderBy(getSalesPerson(orderMasterRequest.getOrderBy().getValue()));
    orderMaster.setCreatedBy(getUser(orderMasterRequest.getUser()));
    orderMaster.setChallanCompany(orderMaster.getChallanCompany() != null ? ChallanCompany.fromName(
            orderMasterRequest.getChallanCompany().getName()) : null);
    OrderMaster master = orderMasterRepository.save(orderMaster);
    List<OrderDetailsResponse> orderDetailsResponses = addOrderDetails(master, orderDetailsRequestList);
    return OrderResponse.from("Order create successfully", orderMaster, orderDetailsResponses);
  }

  private List<OrderDetailsResponse> addOrderDetails(OrderMaster orderMaster, List<OrderDetailsRequest> orderDetailsRequestList) {
    List<OrderDetailsResponse> orderDetailsResponses = new ArrayList<>();
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    List<CommonRequest> commonRequests = new ArrayList<>();
    List<Warehouse> warehouseList = new ArrayList<>();
    Map<String, OrderDetails> productsQuantity = new HashMap<>();
    Map<String, OrderDetails> productsDiscount = new HashMap<>();
    for (OrderDetailsRequest orderDetailsRequest : orderDetailsRequestList) {
      String key = orderDetailsRequest.getProduct().getValue() + "_" + orderDetailsRequest.getShipment().getValue() + "_"
              + orderDetailsRequest.getWarehouse().getValue();
      if (productsQuantity.containsKey(key)) {
        OrderDetails existingOrderDetails = productsQuantity.get(key);
        existingOrderDetails.setDiscount(existingOrderDetails.getDiscount() + (orderDetailsRequest.getDiscount() != null ?
                Double.parseDouble(decimalFormat.format(orderDetailsRequest.getDiscount())) : 0.0));
        existingOrderDetails.setQuantity(existingOrderDetails.getQuantity() + (orderDetailsRequest.getQuantity() -
                orderDetailsRequest.getDiscount()));
        System.out.println("Total price of existing: " + existingOrderDetails.getTotalPrice());
        System.out.println("Total price of new: " + orderDetailsRequest.getTotalPrice());
        existingOrderDetails.setTotalPrice(existingOrderDetails.getTotalPrice() + orderDetailsRequest.getTotalPrice());
      } else {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(orderMaster);
        orderDetails.setProduct(getProduct(orderDetailsRequest.getProduct().getValue()));
        orderDetails.setVat(orderDetailsRequest.getVat() != null ? Double.parseDouble(decimalFormat.format
                (orderDetailsRequest.getVat())) : 0.0);
        orderDetails.setUom(UOM.fromName(orderDetailsRequest.getUom().getName()));
        if (orderDetailsRequest.getDiscount() != null) {
          orderDetails.setDiscount(Double.parseDouble(decimalFormat.format(orderDetailsRequest.getDiscount())));
        } else {
          orderDetails.setDiscount(0.0);
        }
        orderDetails.setQuantity(Double.parseDouble(decimalFormat.format(orderDetailsRequest.getQuantity() -
                orderDetails.getDiscount())));
        orderDetails.setRemarks(orderDetailsRequest.getRemarks());
        orderDetails.setPrice(Double.parseDouble(decimalFormat.format(orderDetailsRequest.getPrice())));
        orderDetails.setTotalPrice(Double.parseDouble(decimalFormat.format(orderDetailsRequest.getTotalPrice())));
        orderDetails.setShipment(getShipment(orderDetailsRequest.getShipment().getValue()));
        if (orderDetailsRequest.getSaleCode() != null && !orderDetailsRequest.getSaleCode().isEmpty()) {
          orderDetails.setSale(getSale(orderDetailsRequest.getSaleCode(), orderDetailsRequest.getArticle(),
                  orderMaster.getSupplier().getSupplierId(), orderDetailsRequest.getProduct().getValue()));
        }
        orderDetails.setStatus(OrderStatus.PENDING);
        orderDetails.setWarehouse(Warehouse.valueOf(orderDetailsRequest.getWarehouse().getValue()));
        orderDetails.setOrderStatus(Status.ACTIVE);
        orderDetailsList.add(orderDetails);
        commonRequests.add(orderDetailsRequest.getShipment());
        warehouseList.add(Warehouse.valueOf(orderDetailsRequest.getWarehouse().getValue()));
        orderDetailsResponses.add(OrderDetailsResponse.from(orderDetails));
        productsQuantity.put(key, orderDetails);
      }
    }
    orderDetailsRepository.saveAll(orderDetailsList);
    double sales = 0.0;
    for (int i = 0; i < orderDetailsList.size(); i++) {
      sales = sales + orderDetailsList.get(i).getTotalPrice();
      updateStock(orderDetailsList.get(i), commonRequests.get(i), warehouseList.get(i));
    }
    saveBills(sales, orderMaster);
    return orderDetailsResponses;
  }

  private void saveBills(Double sales, OrderMaster orderId) {
    OrderBills orderBills = new OrderBills();
    orderBills.setOrder(orderId);
    orderBills.setSales(Double.parseDouble(decimalFormat.format(sales)));
    orderBills.setDue(Double.parseDouble(decimalFormat.format(sales)));
    orderBills.setPayment(0.0);
    orderBillsRepository.save(orderBills);
  }

  private ImportMaster getShipment(String shipment) {
    Optional<ImportMaster> optionalImportMaster = importMasterRepository.findByShipmentNo(shipment);
    if (optionalImportMaster.isPresent()) {
      return optionalImportMaster.get();
    } else {
      throw new RequestValidationException("Shipment not exist");
    }
  }

  private SalesPerson getSalesPerson(String salesPersonId) {
    Optional<SalesPerson> optionalSalesPerson = salesPersonRepository.findBySalesPersonId(salesPersonId);
    if (optionalSalesPerson.isPresent()) {
      return optionalSalesPerson.get();
    } else {
      throw new RequestValidationException("Sales person did not exist");
    }
  }

  @Override
  public ChallanResponse getLastInvoice(String company) {
    Optional<OrderMaster> optionalInvoice = orderMasterRepository.findFirstByChallanCompanyOrderByOrderIdDesc(ChallanCompany.valueOf(company));
    return optionalInvoice.map(ChallanResponse::from).orElse(null);
  }

  @Override
  public UomAndAvailableResponse getUomAndAvailable(String product, String shipment) {
    Optional<ImportDetails> optionalImportDetails = importDetailsRepository.
            findByProductAndImportMaster(getProduct(product), getImportMaster(shipment));
    if (optionalImportDetails.isEmpty()) {
      return null;
    } else {
      if (optionalImportDetails.get().getUom().equals(UOM.KG)) {
        return UomAndAvailableResponse.from(optionalImportDetails.get().getUom(), optionalImportDetails.get().getKgLt());
      } else if (optionalImportDetails.get().getUom().equals(UOM.PIECE)) {
        return UomAndAvailableResponse.from(optionalImportDetails.get().getUom(), optionalImportDetails.get().getPiece());
      } else if (optionalImportDetails.get().getUom().equals(UOM.LT)) {
        return UomAndAvailableResponse.from(optionalImportDetails.get().getUom(), optionalImportDetails.get().getKgLt());
      } else {
        return UomAndAvailableResponse.from(optionalImportDetails.get().getUom(), optionalImportDetails.get().getCartoon());
      }
    }
  }

  @Override
  public AllBillsResponse getBills() {
    List<OrderMaster> orderMasters = orderMasterRepository.findByOrderByCreatedOnDesc();
    List<SingleBillResponse> singleBillResponses = new ArrayList<>();
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
      if (!orderDetailsList.isEmpty()) {
        Optional<OrderBills> optionalOrderBills = orderBillsRepository.findFirstByOrderOrderByCreatedOnDesc(orderMaster);
        if (optionalOrderBills.isPresent() && optionalOrderBills.get().getSales() > 0) {
          singleBillResponses.add(SingleBillResponse.from(orderMaster, optionalOrderBills.get().getSales(),
                  optionalOrderBills.get().getDue()));
        }
      }
    }
    return AllBillsResponse.from(singleBillResponses);
  }

  @Override
  public BillsDetailsResponse getBillsDetails(Long orderId) {
    Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findByOrderId(orderId);
    if (optionalOrderMaster.isPresent()) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(optionalOrderMaster.get(), Status.ACTIVE);
      List<SingleBillsDetailsResponse> singleBillsDetailsResponses = new ArrayList<>();
      for (OrderDetails orderDetails : orderDetailsList) {
        if (orderDetails.getOrderStatus().equals(Status.ACTIVE)) {
          singleBillsDetailsResponses.add(SingleBillsDetailsResponse.from(orderDetails));
        }
      }
      return BillsDetailsResponse.from(SingleBillResponse.from(optionalOrderMaster.get(), 0.0, 0.0), singleBillsDetailsResponses);
    } else {
      throw new RequestValidationException("Order id not found");
    }
  }

  @Override
  public VatDetailsResponse getVatDetails(Long orderId) {
    Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findByOrderId(orderId);
    if (optionalOrderMaster.isPresent()) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(optionalOrderMaster.get(), Status.ACTIVE);
      List<SingleVatDetailsResponse> singleVatDetailsResponses = new ArrayList<>();
      for (OrderDetails orderDetails : orderDetailsList) {
        if (orderDetails.getOrderStatus().equals(Status.ACTIVE)) {
          singleVatDetailsResponses.add(SingleVatDetailsResponse.from(orderDetails));
        }
      }
      return VatDetailsResponse.from(SingleBillResponse.from(optionalOrderMaster.get(), 0.0, 0.0), singleVatDetailsResponses);
    } else {
      throw new RequestValidationException("Order id not found");
    }
  }

  @Override
  public LastPriceResponse getProductLastPrice(String customer, String product) {
    List<OrderMaster> orderMasters = orderMasterRepository.findByCustomerOrderByCreatedOnDesc(getCustomer(customer));
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
      for (OrderDetails orderDetails : orderDetailsList) {
        if (orderDetails.getProduct().getProductId().equals(product)) {
          return LastPriceResponse.from(orderDetails.getPrice());
        }
      }
    }
    return null;
  }

  @Override
  public DailyOrderResponse getDailyOrder(String date, String type) {
    System.out.println(date);
    String startDate = DateUtil.getFormattedDate(ZonedDateTime.now());
    HashMap<String, String> uniqueCustomers = new HashMap<>();
    List<String> uniqueName = new ArrayList<>();
    List<OrderMaster> orderMasters;
    if (date != null && !date.isEmpty()) {
      if (type.equals("Order")) {
        orderMasters = orderMasterRepository.findByOrderDateBetween(
                DateUtil.getZoneDateTime(date + "T00:00:00"), DateUtil.getZoneDateTime(date + "T23:59:59"));
      } else {
        orderMasters = orderMasterRepository.findByDeliveryDateBetween(
                DateUtil.getZoneDateTime(date + "T00:00:00"), DateUtil.getZoneDateTime(date + "T23:59:59"));
      }
    } else {
      if (type.equals("Order")) {
        orderMasters = orderMasterRepository.findByOrderDateBetween(
                DateUtil.getZoneDateTime(startDate + "T00:00:00"), DateUtil.getZoneDateTime(startDate + "T23:59:59"));
      } else {
        orderMasters = orderMasterRepository.findByDeliveryDateBetween(
                DateUtil.getZoneDateTime(startDate + "T00:00:00"), DateUtil.getZoneDateTime(startDate + "T23:59:59"));
      }
    }
    List<DailyOrderProductsResponse> productsResponses = new ArrayList<>();
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
      for (OrderDetails orderDetails : orderDetailsList) {
        if (orderDetails.getOrderStatus().equals(Status.ACTIVE)) {
          productsResponses.add(DailyOrderProductsResponse.from(orderDetails, orderMaster));
        }
      }
      if (!uniqueCustomers.containsKey(orderMaster.getCustomer().getCustomerName())) {
        uniqueCustomers.put(orderMaster.getCustomer().getCustomerName(), orderMaster.getCustomer().getCustomerName());
      }
    }
    for (String string : uniqueCustomers.keySet()) {
      uniqueName.add(uniqueCustomers.get(string));
    }
    return DailyOrderResponse.from(productsResponses, uniqueName);
  }

  @Override
  public DailyOrderShortResponse getDailyOrderShort(String date, String type) {
    String startDate = DateUtil.getFormattedDate(ZonedDateTime.now());
    HashMap<String, Double> uniqueProducts = new HashMap<>();
    List<OrderMaster> orderMasters;
    if (date != null && !date.isEmpty()) {
      if (type.equals("Order")) {
        orderMasters = orderMasterRepository.findByOrderDateBetween(
                DateUtil.getZoneDateTime(date + "T00:00:00"), DateUtil.getZoneDateTime(date + "T23:59:59"));
      } else {
        orderMasters = orderMasterRepository.findByDeliveryDateBetween(
                DateUtil.getZoneDateTime(date + "T00:00:00"), DateUtil.getZoneDateTime(date + "T23:59:59"));
      }
    } else {
      if (type.equals("Order")) {
        orderMasters = orderMasterRepository.findByOrderDateBetween(
                DateUtil.getZoneDateTime(startDate + "T00:00:00"), DateUtil.getZoneDateTime(startDate + "T23:59:59"));
      } else {
        orderMasters = orderMasterRepository.findByDeliveryDateBetween(
                DateUtil.getZoneDateTime(startDate + "T00:00:00"), DateUtil.getZoneDateTime(startDate + "T23:59:59"));
      }
    }
    List<DailyShortResponse> dailyShortResponses = new ArrayList<>();
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
      for (OrderDetails orderDetails : orderDetailsList) {
        if (!uniqueProducts.containsKey(orderDetails.getProduct().getProductId())) {
          uniqueProducts.put(orderDetails.getProduct().getProductId(), orderDetails.getQuantity());
        } else {
          uniqueProducts.put(
                  orderDetails.getProduct().getProductId(),
                  uniqueProducts.get(orderDetails.getProduct().getProductId()) + orderDetails.getQuantity()
          );
        }
      }
    }
    for (String string : uniqueProducts.keySet()) {
      dailyShortResponses.add(DailyShortResponse.from(getProduct(string).getProductName(), uniqueProducts.get(string)));
    }
    return DailyOrderShortResponse.from(dailyShortResponses);
  }

  @Override
  public OrderTableResponse getTable() {
    List<OrderMaster> orderMasters = (List<OrderMaster>) orderMasterRepository.findAll();
    Map<Long, Double> grandTotalMap = new HashMap<>();
    List<SingleOrderTableResponse> singleOrderTableResponses = new ArrayList<>();
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
      double grandTotal = 0.0; // Initialize grandTotal for each orderId
      int i = 1;
      for (OrderDetails orderDetails : orderDetailsList) {

        grandTotal += orderDetails.getTotalPrice(); // Assuming getTotalPrice() is the method to get total price
        if (i <= orderDetailsList.size() - 1) {
          singleOrderTableResponses.add(SingleOrderTableResponse.from(orderMaster, orderDetails));
        } else {
          grandTotalMap.put(orderMaster.getId(), grandTotal);
          SingleOrderTableResponse singleOrderTableResponse = SingleOrderTableResponse.from(orderMaster, orderDetails);
          singleOrderTableResponse.setGrandTotal(grandTotalMap.get(orderMaster.getId()));
          singleOrderTableResponses.add(singleOrderTableResponse);
        }
        i++;
      }
    }
    return OrderTableResponse.from(singleOrderTableResponses);
  }

  @Override
  public EditOrderResponse editOrder(Long orderId, List<EditOrderRequest> updateOrderRequest) {
    List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(getOrder(orderId), Status.ACTIVE);
    List<OrderDetails> orderDetails1 = new ArrayList<>();
    for (OrderDetails orderDetail : orderDetailsList) {
      EditOrderRequest matchingEditRequest = findMatchingEditRequest(updateOrderRequest,
              orderDetail.getProduct().getProductName());
      if (matchingEditRequest != null) {
        updateStockWhenEdit(orderDetail, matchingEditRequest);
        orderDetail.setQuantity(matchingEditRequest.getQuantity());
        orderDetail.setTotalPrice(matchingEditRequest.getTotal());
        orderDetails1.add(orderDetail);
      }
    }
    orderDetailsRepository.saveAll(orderDetails1);
    updateOrderBills(orderId);
    return EditOrderResponse.from();
  }

  private void updateOrderBills(Long orderId) {
    List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(getOrder(orderId), Status.ACTIVE);
    double total = 0.0;
    for (OrderDetails orderDetails : orderDetailsList) {
      total = total + orderDetails.getTotalPrice();
    }
    Optional<OrderBills> optionalOrderBills = orderBillsRepository.findFirstByOrderOrderByCreatedOnDesc(getOrder(orderId));
    if (optionalOrderBills.isPresent()) {
      double payment = optionalOrderBills.get().getPayment();
      optionalOrderBills.get().setSales(total);
      optionalOrderBills.get().setDue(total - optionalOrderBills.get().getPayment());
      orderBillsRepository.save(optionalOrderBills.get());
    } else {
      throw new RequestValidationException("No bills found for this Order id: " + orderId);
    }
  }

  @Override
  public ReturnDetailsResponse getReturn(Long serial) {
    List<Returns> returnsList = returnsRepository.findBySerialNo(serial);
    List<SingleReturnResponse> singleReturnResponses = new ArrayList<>();
    double piece = 0.0;
    double cartoon = 0.0;
    double kgLt = 0.0;
    for (Returns returns : returnsList) {
      piece = piece + returns.getPiece();
      cartoon = cartoon + returns.getCartoon();
      kgLt = kgLt + returns.getKgLt();
      singleReturnResponses.add(SingleReturnResponse.from(returns));
    }
    if (!returnsList.isEmpty()) {
      return ReturnDetailsResponse.from(returnsList.get(0).getDeliveryMan(), returnsList.get(0).getCreatedOn(),
              piece, cartoon, kgLt, singleReturnResponses);
    } else {
      return null;
    }
  }

  @Override
  public AllPendingOrdersResponse getPendingOrder() {
    List<OrderMaster> orderMasters = (List<OrderMaster>) orderMasterRepository.findAll();
    List<PendingOrderResponse> pendingOrderResponseList = new ArrayList<>();
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderAndStatus(orderMaster, OrderStatus.PENDING);
      for (OrderDetails orderDetails : orderDetailsList) {
        if (orderDetails.getOrderStatus().equals(Status.ACTIVE)) {
          pendingOrderResponseList.add(PendingOrderResponse.from(orderMaster, orderDetails));
        }
      }
    }
    return AllPendingOrdersResponse.from(pendingOrderResponseList);
  }

  @Override
  public OrderRequisitionResponse updateOrderStatus(List<UpdateOrderRequest> updateOrderRequestList) {
    List<RequisitionResponse> requisitionResponses = new ArrayList<>();
    double totalPiece = 0.0;
    double totalKg = 0.0;
    double totalCartoon = 0.0;

    for (UpdateOrderRequest updateOrderRequest : updateOrderRequestList) {
      Optional<OrderDetails> orderDetails = orderDetailsRepository.findByOrderAndProductAndWarehouse(
              getOrder(updateOrderRequest.getOrderId()), getProduct(updateOrderRequest.getProductId()),
              Warehouse.valueOf(updateOrderRequest.getWarehouse()));

      if (orderDetails.isPresent()) {
        Optional<ImportDetails> importDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(
                orderDetails.get().getProduct(), orderDetails.get().getShipment(), orderDetails.get().getWarehouse());

        if (importDetails.isPresent()) {
          if (importDetails.get().getUom().equals(UOM.KG)) {
            double cartoon = orderDetails.get().getQuantity() / importDetails.get().getUnitCartoon();
            double piece = orderDetails.get().getQuantity() / importDetails.get().getUnitPiece();
            totalCartoon = totalCartoon + cartoon;
            totalPiece = totalPiece + piece;
            totalKg = totalKg + orderDetails.get().getQuantity();
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(piece)),
                    Double.parseDouble(decimalFormat.format(cartoon)),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity()))));

          } else if (importDetails.get().getUom().equals(UOM.LT)) {
            double cartoon = orderDetails.get().getQuantity() / importDetails.get().getUnitCartoon();
            double piece = orderDetails.get().getQuantity() / importDetails.get().getUnitPiece();
            totalCartoon = totalCartoon + cartoon;
            totalPiece = totalPiece + piece;
            totalKg = totalKg + orderDetails.get().getQuantity();
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(piece)),
                    Double.parseDouble(decimalFormat.format(cartoon)),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity()))));

          } else if (importDetails.get().getUom().equals(UOM.PIECE)) {
            double cartoon = (orderDetails.get().getQuantity() * importDetails.get().getUnitPiece())
                    / importDetails.get().getUnitCartoon();
            double kgLt = orderDetails.get().getQuantity() * importDetails.get().getUnitCartoon();
            totalCartoon = totalCartoon + cartoon;
            totalPiece = totalPiece + orderDetails.get().getQuantity();
            totalKg = totalKg + kgLt;
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity())),
                    Double.parseDouble(decimalFormat.format(cartoon)),
                    Double.parseDouble(decimalFormat.format(kgLt))));

          } else {
            double piece = (orderDetails.get().getQuantity() * importDetails.get().getUnitCartoon())
                    / importDetails.get().getUnitPiece();
            double kgLt = orderDetails.get().getQuantity() * importDetails.get().getUnitCartoon();
            totalCartoon = totalCartoon + orderDetails.get().getQuantity();
            totalPiece = totalPiece + piece;
            totalKg = totalKg + kgLt;
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(piece)),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity())),
                    Double.parseDouble(decimalFormat.format(kgLt))));
          }
        } else {
          throw new RequestValidationException("Shipment is not exist");
        }

        orderDetails.get().setStatus(OrderStatus.WAITING);
        orderDetailsRepository.save(orderDetails.get());
      } else {
        throw new RequestValidationException("Order is not exist");
      }
    }

    return OrderRequisitionResponse.from(requisitionResponses,
            Double.parseDouble(decimalFormat.format(totalKg)),
            Double.parseDouble(decimalFormat.format(totalCartoon)),
            Double.parseDouble(decimalFormat.format(totalPiece)));
  }

  @Override
  public OrderPaymentResponse createPayment(PaymentRequest request) {
    Optional<OrderBills> optionalOrderBills = orderBillsRepository.findFirstByOrderOrderByCreatedOnDesc(
            getOrder(request.getOrderId()));
    if (optionalOrderBills.isPresent()) {
      OrderBills orderBills = new OrderBills();
      orderBills.setOrder(optionalOrderBills.get().getOrder());
      orderBills.setSales(optionalOrderBills.get().getSales());
      orderBills.setDue(Double.parseDouble(decimalFormat.format(optionalOrderBills.get().getDue() - request.getPayment())));
      orderBills.setPayment(Double.parseDouble(decimalFormat.format(request.getPayment())));
      orderBillsRepository.save(orderBills);
      return OrderPaymentResponse.from();
    } else {
      throw new RequestValidationException("Order is not exist");
    }
  }

  @Override
  public AllPaymentsResponse getAllPayments() {
    List<Customer> customers = (List<Customer>) customerRepository.findAll();
    List<SinglePaymentResponse> singlePaymentResponses = new ArrayList<>();
    for (Customer customer : customers) {
      List<OrderMaster> orderMasters = orderMasterRepository.findByCustomerOrderByCreatedOnDesc(customer);
      double due = 0.0;
      for (OrderMaster orderMaster : orderMasters) {
        Optional<OrderBills> optionalOrderBills = orderBillsRepository.findFirstByOrderOrderByCreatedOnDesc(orderMaster);
        if (optionalOrderBills.isPresent()) {
          due = due + optionalOrderBills.get().getDue();
        }
      }
      if(due > 0) {
        singlePaymentResponses.add(SinglePaymentResponse.from(customer.getCustomerId(), customer.getCustomerName(),
                customer.getCompany(), due));
      }
    }
    return AllPaymentsResponse.from(singlePaymentResponses);
  }

  @Override
  public LedgerResponse getAllLedger(String customer) {
    List<OrderMaster> orderMasters = orderMasterRepository.findByCustomer(getCustomer(customer));
    List<AllLedgerResponse> allLedgerResponses = new ArrayList<>();
    Set<Long> processedOrderIds = new HashSet<>();
    for (OrderMaster orderMaster : orderMasters) {
      if (!processedOrderIds.contains(orderMaster.getId())) {
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
        if (!orderDetailsList.isEmpty()) {
          List<OrderBills> orderBillsList = orderBillsRepository.findByOrder(orderMaster);
          List<SingleLedgerResponse> singleLedgerResponseList = new ArrayList<>();
          double totalPayments = 0.0;
          double totalSales = calculateTotalSales(orderMaster);
          for (OrderBills orderBills : orderBillsList) {
            totalPayments = totalPayments + orderBills.getPayment();
            singleLedgerResponseList.add(SingleLedgerResponse.from(orderBills));
          }
          double discount = calculateDiscount(orderMaster);
          allLedgerResponses.add(AllLedgerResponse.from(orderMaster, singleLedgerResponseList, discount, totalSales, totalPayments));
          processedOrderIds.add(orderMaster.getId());
        }
      }
    }
    return LedgerResponse.from(allLedgerResponses);
  }

  // Calculate totalSales for an order once
  private double calculateTotalSales(OrderMaster orderMaster) {
    Optional<OrderBills> optionalOrderBills = orderBillsRepository.findFirstByOrderOrderByCreatedOnDesc(orderMaster);
    if (optionalOrderBills.isPresent()) {
      return optionalOrderBills.get().getSales();
    }
    return 0.0;
  }

  // Calculate discount for an order
  private double calculateDiscount(OrderMaster orderMaster) {
    double discount = 0.0;
    List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllByOrderAndOrderStatus(orderMaster, Status.ACTIVE);
    for (OrderDetails orderDetails : orderDetailsList) {
      discount += orderDetails.getDiscount() * orderDetails.getPrice();
    }

    List<Returns> returnsList = returnsRepository.findByOrder(orderMaster);
    for (Returns returns : returnsList) {
      Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findByOrderAndProduct(orderMaster,
              returns.getProduct());

      if (optionalOrderDetails.isPresent()) {
        double price = optionalOrderDetails.get().getPrice();

        if (optionalOrderDetails.get().getUom().equals(UOM.LT)) {
          discount += returns.getKgLt() * price;
        } else if (optionalOrderDetails.get().getUom().equals(UOM.KG)) {
          discount += returns.getKgLt() * price;
        } else if (optionalOrderDetails.get().getUom().equals(UOM.PIECE)) {
          discount += returns.getPiece() * price;
        } else {
          discount += returns.getCartoon() * price;
        }
      }
    }
    return discount;
  }


  @Override
  public AllOrderIdResponse getAllOrderId(String product) {
    List<OrderDetails> orderDetailsList = orderDetailsRepository.findByProduct(getProduct(product));
    List<SingleOrderId> singleOrderIds = new ArrayList<>();
    for (OrderDetails orderDetails : orderDetailsList) {
      singleOrderIds.add(SingleOrderId.from(orderDetails.getOrder().getOrderId()));
    }
    return AllOrderIdResponse.from(singleOrderIds);
  }

  @Override
  public GetUomResponse getProductUom(String product, Long order, String warehouse) {
    Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findByOrderAndProductAndWarehouse(
            getOrder(order), getProduct(product), Warehouse.valueOf(warehouse));
    if (optionalOrderDetails.isPresent()) {
      return GetUomResponse.from(optionalOrderDetails.get().getUom());
    } else {
      throw new RequestValidationException("Order Id is not exist");
    }
  }

  @Override
  public AllPendingOrdersResponse getWaitingOrder() {
    List<OrderMaster> orderMasters = (List<OrderMaster>) orderMasterRepository.findAll();
    List<PendingOrderResponse> pendingOrderResponseList = new ArrayList<>();
    for (OrderMaster orderMaster : orderMasters) {
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderAndStatus(orderMaster, OrderStatus.WAITING);
      for (OrderDetails orderDetails : orderDetailsList) {
        pendingOrderResponseList.add(PendingOrderResponse.from(orderMaster, orderDetails));
      }
    }
    return AllPendingOrdersResponse.from(pendingOrderResponseList);
  }

  @Override
  public OrderRequisitionResponse updateOrderWaitingStatus(UpdateOrder updateOrder) {
    List<RequisitionResponse> requisitionResponses = new ArrayList<>();
    double totalPiece = 0.0;
    double totalKg = 0.0;
    double totalCartoon = 0.0;
    User user = getUser(updateOrder.getUser());
    Warehouse warehouse = updateOrder.getWarehouse();
    String deliveryMan = updateOrder.getDeliveryMan();
    for (UpdateOrderRequest updateOrderRequest : updateOrder.getUpdateOrderRequestList()) {
      Optional<OrderDetails> orderDetails = orderDetailsRepository.findByOrderAndProductAndWarehouse(
              getOrder(updateOrderRequest.getOrderId()), getProduct(updateOrderRequest.getProductId()),
              Warehouse.valueOf(updateOrderRequest.getWarehouse()));

      if (orderDetails.isPresent()) {
        Optional<ImportDetails> importDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(
                orderDetails.get().getProduct(), orderDetails.get().getShipment(), orderDetails.get().getWarehouse());

        if (importDetails.isPresent()) {
          if (importDetails.get().getUom().equals(UOM.KG)) {
            double cartoon = orderDetails.get().getQuantity() / importDetails.get().getUnitCartoon();
            double piece = orderDetails.get().getQuantity() / importDetails.get().getUnitPiece();
            totalCartoon = totalCartoon + cartoon;
            totalPiece = totalPiece + piece;
            totalKg = totalKg + orderDetails.get().getQuantity();
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(piece)),
                    Double.parseDouble(decimalFormat.format(cartoon)),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity()))));

          } else if (importDetails.get().getUom().equals(UOM.LT)) {
            double cartoon = orderDetails.get().getQuantity() / importDetails.get().getUnitCartoon();
            double piece = orderDetails.get().getQuantity() / importDetails.get().getUnitPiece();
            totalCartoon = totalCartoon + cartoon;
            totalPiece = totalPiece + piece;
            totalKg = totalKg + orderDetails.get().getQuantity();
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(piece)),
                    Double.parseDouble(decimalFormat.format(cartoon)),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity()))));

          } else if (importDetails.get().getUom().equals(UOM.PIECE)) {
            double cartoon = (orderDetails.get().getQuantity() * importDetails.get().getUnitPiece())
                    / importDetails.get().getUnitCartoon();
            double kgLt = orderDetails.get().getQuantity() * importDetails.get().getUnitCartoon();
            totalCartoon = totalCartoon + cartoon;
            totalPiece = totalPiece + orderDetails.get().getQuantity();
            totalKg = totalKg + kgLt;
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity())),
                    Double.parseDouble(decimalFormat.format(cartoon)),
                    Double.parseDouble(decimalFormat.format(kgLt))));

          } else {
            double piece = (orderDetails.get().getQuantity() * importDetails.get().getUnitCartoon())
                    / importDetails.get().getUnitPiece();
            double kgLt = orderDetails.get().getQuantity() * importDetails.get().getUnitCartoon();
            totalCartoon = totalCartoon + orderDetails.get().getQuantity();
            totalPiece = totalPiece + piece;
            totalKg = totalKg + kgLt;
            requisitionResponses.add(RequisitionResponse.from(
                    orderDetails.get().getProduct().getProductName(),
                    Double.parseDouble(decimalFormat.format(piece)),
                    Double.parseDouble(decimalFormat.format(orderDetails.get().getQuantity())),
                    Double.parseDouble(decimalFormat.format(kgLt))));
          }
        } else {
          throw new RequestValidationException("Shipment is not exist");
        }

        orderDetails.get().setStatus(OrderStatus.DELIVERED);
        orderDetailsRepository.save(orderDetails.get());
      } else {
        throw new RequestValidationException("Order is not exist");
      }
    }
    Long serial = getSerial();
    for (RequisitionResponse requisitionResponse : requisitionResponses) {
      Requisition requisition = new Requisition();
      requisition.setSerialNo(serial);
      requisition.setProduct(requisitionResponse.getProduct());
      requisition.setWarehouse(warehouse);
      requisition.setDeliveryMan(deliveryMan);
      System.out.println("Piece: " + requisitionResponse.getPiece());
      requisition.setPiece(requisitionResponse.getPiece());
      requisition.setQuantity(requisitionResponse.getQuantity());
      requisition.setCreatedBy(user);
      requisitionRepository.save(requisition);
    }
    return OrderRequisitionResponse.from(requisitionResponses,
            Double.parseDouble(decimalFormat.format(totalKg)),
            Double.parseDouble(decimalFormat.format(totalCartoon)),
            Double.parseDouble(decimalFormat.format(totalPiece)));
  }

  @Override
  public OrderRequisitionResponse getRequisition(Long serial) {
    List<Requisition> requisitionList = requisitionRepository.findBySerialNo(serial);
    List<RequisitionResponse> requisitionResponseList = new ArrayList<>();
    double totalPiece = 0.0;
    double totalKg = 0.0;
    double totalCartoon = 0.0;
    for (Requisition requisition : requisitionList) {
      String[] ctnKgLt = requisition.getQuantity().split(" ");
      totalPiece = totalPiece + requisition.getPiece();
      totalKg = totalKg + Double.parseDouble(ctnKgLt[2]);
      totalCartoon = totalCartoon + Double.parseDouble(ctnKgLt[0]);
      requisitionResponseList.add(RequisitionResponse.from(requisition.getProduct(), requisition.getPiece(),
              Double.parseDouble(ctnKgLt[0]), Double.parseDouble(ctnKgLt[2])));
    }
    return OrderRequisitionResponse.from(requisitionResponseList, totalKg, totalCartoon, totalPiece);
  }

  @Override
  public OrderWarehouseResponse getAllWarehouse(String product, Long order) {
    List<OrderDetails> orderDetailsList = orderDetailsRepository.findByProductAndOrder(getProduct(product),
            getOrder(order));
    List<SingleOrderWarehouse> warehouseList = new ArrayList<>();
    for (OrderDetails orderDetails : orderDetailsList) {
      warehouseList.add(SingleOrderWarehouse.from(orderDetails));
    }
    return OrderWarehouseResponse.from(warehouseList);
  }

  @Override
  public DeleteResponse deleteOrderDetails(Long order, String product, String warehouse) {
    Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findByOrderAndProductAndWarehouse(
            getOrder(order), getProduct(product), Warehouse.valueOf(warehouse));
    if (optionalOrderDetails.isPresent()) {
      optionalOrderDetails.get().setOrderStatus(Status.INACTIVE);
      orderDetailsRepository.save(optionalOrderDetails.get());
      updateImportDetails(optionalOrderDetails.get());
      updateStockWhenDelete(optionalOrderDetails.get());
      updateBill(optionalOrderDetails.get());
      return DeleteResponse.from();
    } else {
      throw new RequestValidationException("Order details is not exist");
    }
  }

  private void updateBill(OrderDetails orderDetails) {
    Optional<OrderBills> optionalOrderBills = orderBillsRepository.findFirstByOrderOrderByCreatedOnDesc(
            orderDetails.getOrder());
    if (optionalOrderBills.isPresent()) {
      optionalOrderBills.get().setDue(optionalOrderBills.get().getDue() - orderDetails.getTotalPrice());
      optionalOrderBills.get().setSales(optionalOrderBills.get().getSales() - orderDetails.getTotalPrice());
      orderBillsRepository.save(optionalOrderBills.get());
    } else {
      throw new RequestValidationException("Bill is not exist for this order id: " +
              orderDetails.getOrder().getOrderId());
    }
  }

  private void updateStockWhenDelete(OrderDetails orderDetails) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(orderDetails.getProduct());
    if (optionalStock.isPresent()) {
      optionalStock.get().setInStock(optionalStock.get().getInStock() + orderDetails.getQuantity());
      optionalStock.get().setTotalSell(optionalStock.get().getTotalSell() - orderDetails.getQuantity());
      stockRepository.save(optionalStock.get());
    } else {
      throw new RequestValidationException("Product is not found in stock");
    }
  }

  private void updateImportDetails(OrderDetails orderDetails) {
    Optional<ImportDetails> optionalImportDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(orderDetails.getProduct(),
            orderDetails.getShipment(), orderDetails.getWarehouse());
    if (optionalImportDetails.isPresent()) {
      if (orderDetails.getUom().equals(UOM.KG) || orderDetails.getUom().equals(UOM.LT)) {
        optionalImportDetails.get().setKgLt(optionalImportDetails.get().getKgLt() + orderDetails.getQuantity());
      } else if (orderDetails.getUom().equals(UOM.PIECE)) {
        optionalImportDetails.get().setPiece(optionalImportDetails.get().getPiece() + orderDetails.getQuantity());
      } else {
        optionalImportDetails.get().setCartoon(optionalImportDetails.get().getCartoon() + orderDetails.getQuantity());
      }
      importDetailsRepository.save(optionalImportDetails.get());
    } else {
      throw new RequestValidationException("Import details is not found");
    }
  }

  private Long getSerial() {
    Optional<Requisition> optionalRequisition = requisitionRepository.findFirstByOrderBySerialNoDesc();
    Date currentDate = new Date();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
    String currentYear = yearFormat.format(currentDate);
    if (optionalRequisition.isPresent()) {
      String serialIdYear = String.valueOf(optionalRequisition.get().getSerialNo()).substring(0, 2);
      if (!serialIdYear.equals(currentYear)) {
        return Long.parseLong(currentYear + "000001");
      } else {
        return optionalRequisition.get().getSerialNo() + 1;
      }
    } else {
      return Long.parseLong(currentYear + "000001");
    }
  }

  private EditOrderRequest findMatchingEditRequest(List<EditOrderRequest> editOrderRequests, String productName) {
    for (EditOrderRequest editRequest : editOrderRequests) {
      if (editRequest.getProduct().equals(productName)) {
        return editRequest;
      }
    }
    return null;
  }

  private OrderMaster getOrder(Long order) {
    Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findByOrderId(order);
    if (optionalOrderMaster.isPresent()) {
      return optionalOrderMaster.get();
    } else {
      throw new RequestValidationException("Order id is not found");
    }
  }

  private ImportMaster getImportMaster(String shipment) {
    Optional<ImportMaster> optionalImportMaster = importMasterRepository.findByShipmentNo(shipment);
    if (optionalImportMaster.isEmpty()) {
      throw new RequestValidationException("Shipment not exist");
    } else {
      return optionalImportMaster.get();
    }
  }

  private Long getOrderId() {
    Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findFirstByOrderByOrderIdDesc();
    Date currentDate = new Date();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
    String currentYear = yearFormat.format(currentDate);
    if (optionalOrderMaster.isPresent()) {
      String orderIdYear = String.valueOf(optionalOrderMaster.get().getOrderId()).substring(0, 2);
      if (!orderIdYear.equals(currentYear)) {
        return Long.parseLong(currentYear + "000001");
      } else {
        return optionalOrderMaster.get().getOrderId() + 1;
      }
    } else {
      return Long.parseLong(currentYear + "000001");
    }
  }

  private Customer getCustomer(String customer) {
    Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customer);
    if (optionalCustomer.isPresent()) {
      return optionalCustomer.get();
    } else {
      throw new RequestValidationException("Customer is not exist");
    }
  }

  private Supplier getSupplier(String supplier) {
    Optional<Supplier> optionalSupplier = supplierRepository.findBySupplierId(supplier);
    if (optionalSupplier.isPresent()) {
      return optionalSupplier.get();
    } else {
      throw new RequestValidationException("Supplier is not exist");
    }
  }

  private Branch getBranch(String branch, Supplier supplier) {
    Optional<Branch> optionalBranch = branchRepository.findByBranchNameAndSupplier(branch, supplier);
    if (optionalBranch.isPresent()) {
      return optionalBranch.get();
    } else {
      throw new RequestValidationException("Branch is not exist");
    }
  }

  private Product getProduct(String product) {
    Optional<Product> optionalProduct = productRepository.findByProductId(product);
    if (optionalProduct.isEmpty()) {
      throw new RequestValidationException("Product not exist");
    } else {
      return optionalProduct.get();
    }
  }

  private Sale getSale(String sale, String article, String supplierId, String value) {
    Optional<Sale> optionalSale = saleRepository.findBySupplierAndProductAndArticleAndSaleCode(getSupplier(supplierId),
            getProduct(value), article, sale);
    if (optionalSale.isPresent()) {
      return optionalSale.get();
    } else {
      throw new RequestValidationException("Sale not exist");
    }
  }

  private Double getTotal(OrderDetailsRequest orderDetailsRequest) {
    if (orderDetailsRequest.getDiscount() != null) {
      return (orderDetailsRequest.getQuantity() - orderDetailsRequest.getDiscount()) *
              orderDetailsRequest.getPrice();
    } else {
      return (orderDetailsRequest.getQuantity() *
              orderDetailsRequest.getPrice());
    }
  }

  private User getUser(String createdBy) {
    Optional<User> optionalUser = userRepository.findByEmail(createdBy);
    if (optionalUser.isEmpty()) {
      throw new RequestValidationException("User not exist");
    } else {
      return optionalUser.get();
    }
  }

  private void updateStock(OrderDetails orderDetails, CommonRequest commonRequest, Warehouse warehouse) {
    Optional<ImportDetails> optionalImportDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(
            orderDetails.getProduct(), getImportMaster(commonRequest.getValue()), warehouse);
    if (optionalImportDetails.isPresent()) {
      UOM uom = UOM.fromName(optionalImportDetails.get().getUom().getName());
      if ((Objects.equals(uom, orderDetails.getUom())) && Objects.equals(uom, UOM.KG)) {
        Double kgLt = optionalImportDetails.get().getKgLt() - (orderDetails.getQuantity() + orderDetails.getDiscount());

        Double cartoon = optionalImportDetails.get().getCartoon() - ((orderDetails.getQuantity() + orderDetails.getDiscount()) /
                optionalImportDetails.get().getUnitCartoon());

        Double piece = optionalImportDetails.get().getPiece() - ((orderDetails.getQuantity() + orderDetails.getDiscount()) /
                optionalImportDetails.get().getUnitPiece());

        updateStockData((orderDetails.getQuantity() + orderDetails.getDiscount()), orderDetails.getProduct());
        optionalImportDetails.get().setCartoon(cartoon);
        optionalImportDetails.get().setPiece(piece);
        optionalImportDetails.get().setKgLt(kgLt);
        importDetailsRepository.save(optionalImportDetails.get());
      } else if ((Objects.equals(uom, orderDetails.getUom())) && Objects.equals(uom, UOM.LT)) {
        Double kgLt = optionalImportDetails.get().getKgLt() - (orderDetails.getQuantity() + orderDetails.getDiscount());

        Double cartoon = optionalImportDetails.get().getCartoon() - ((orderDetails.getQuantity() + orderDetails.getDiscount()) /
                optionalImportDetails.get().getUnitCartoon());

        Double piece = optionalImportDetails.get().getPiece() - ((orderDetails.getQuantity() + orderDetails.getDiscount()) /
                optionalImportDetails.get().getUnitPiece());

        updateStockData((orderDetails.getQuantity() + orderDetails.getDiscount()), orderDetails.getProduct());
        optionalImportDetails.get().setCartoon(cartoon);
        optionalImportDetails.get().setPiece(piece);
        optionalImportDetails.get().setKgLt(kgLt);
        importDetailsRepository.save(optionalImportDetails.get());
      } else if ((Objects.equals(uom, orderDetails.getUom())) && Objects.equals(uom, UOM.PIECE)) {
        Double piece = optionalImportDetails.get().getPiece() - (orderDetails.getQuantity() + orderDetails.getDiscount());

        Double cartoon = optionalImportDetails.get().getCartoon() - (((orderDetails.getQuantity() + orderDetails.getDiscount()) *
                optionalImportDetails.get().getUnitPiece()) / optionalImportDetails.get().getUnitCartoon());

        Double kgLt = optionalImportDetails.get().getKgLt() - ((orderDetails.getQuantity() + orderDetails.getDiscount()) *
                optionalImportDetails.get().getUnitPiece());

        updateStockData((orderDetails.getQuantity() + orderDetails.getDiscount()), orderDetails.getProduct());
        optionalImportDetails.get().setCartoon(cartoon);
        optionalImportDetails.get().setPiece(piece);
        optionalImportDetails.get().setKgLt(kgLt);
        importDetailsRepository.save(optionalImportDetails.get());
      } else {
        Double cartoon = optionalImportDetails.get().getCartoon() - (orderDetails.getQuantity() + orderDetails.getDiscount());

        Double piece = optionalImportDetails.get().getPiece() -
                (((orderDetails.getQuantity() + orderDetails.getDiscount()) * optionalImportDetails.get().getUnitCartoon())
                        / optionalImportDetails.get().getUnitPiece());

        Double kgLt = optionalImportDetails.get().getKgLt() - ((orderDetails.getQuantity() + orderDetails.getDiscount()) *
                optionalImportDetails.get().getUnitCartoon());

        updateStockData((orderDetails.getQuantity() + orderDetails.getDiscount()), orderDetails.getProduct());
        optionalImportDetails.get().setCartoon(cartoon);
        optionalImportDetails.get().setPiece(piece);
        optionalImportDetails.get().setKgLt(kgLt);
        importDetailsRepository.save(optionalImportDetails.get());
      }
    } else {
      throw new RequestValidationException("Product is not found");
    }
  }

  private void updateStockData(Double quantity, Product product) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isEmpty()) {
      throw new RequestValidationException("Stock is not found for this product");
    } else {
      optionalStock.get().setTotalSell(optionalStock.get().getTotalSell() + quantity);
      optionalStock.get().setInStock(optionalStock.get().getInStock() - quantity);
      stockRepository.save(optionalStock.get());
    }
  }

  private void updateStockWhenEdit(OrderDetails orderDetails, EditOrderRequest request) {
    Optional<ImportDetails> optionalImportDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(
            orderDetails.getProduct(), getImportMaster(orderDetails.getShipment().getShipmentNo()),
            orderDetails.getWarehouse());
    if (optionalImportDetails.isPresent()) {
      UOM uom = UOM.fromName(optionalImportDetails.get().getUom().getName());
      if ((Objects.equals(uom, orderDetails.getUom())) && Objects.equals(uom, UOM.KG)) {
        if (orderDetails.getQuantity() < request.getQuantity()) {
          Double kgLt = (optionalImportDetails.get().getKgLt()) - (request.getQuantity() - orderDetails.getQuantity());
          Double cartoon = (optionalImportDetails.get().getCartoon()) - ((request.getQuantity() - orderDetails.getQuantity()) /
                  optionalImportDetails.get().getUnitCartoon());
          Double piece = (optionalImportDetails.get().getPiece()) - ((request.getQuantity() - orderDetails.getQuantity()) /
                  optionalImportDetails.get().getUnitPiece());
          updateStockData((request.getQuantity() - orderDetails.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        } else {
          Double kgLt = (optionalImportDetails.get().getKgLt()) + (orderDetails.getQuantity() - request.getQuantity());
          Double cartoon = (optionalImportDetails.get().getCartoon()) + ((orderDetails.getQuantity() - request.getQuantity()) /
                  optionalImportDetails.get().getUnitCartoon());
          Double piece = (optionalImportDetails.get().getPiece()) + ((orderDetails.getQuantity() - request.getQuantity()) /
                  optionalImportDetails.get().getUnitPiece());
          updateStockDataWhenEdit((orderDetails.getQuantity() - request.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        }
      } else if ((Objects.equals(uom, orderDetails.getUom())) && Objects.equals(uom, UOM.LT)) {
        if (orderDetails.getQuantity() < request.getQuantity()) {
          Double kgLt = (optionalImportDetails.get().getKgLt()) - (request.getQuantity() - orderDetails.getQuantity());
          Double cartoon = (optionalImportDetails.get().getCartoon()) - ((request.getQuantity() - orderDetails.getQuantity()) /
                  optionalImportDetails.get().getUnitCartoon());
          Double piece = (optionalImportDetails.get().getPiece()) - ((request.getQuantity() - orderDetails.getQuantity()) /
                  optionalImportDetails.get().getUnitPiece());
          updateStockData((request.getQuantity() - orderDetails.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        } else {
          Double kgLt = (optionalImportDetails.get().getKgLt()) + (orderDetails.getQuantity() - request.getQuantity());
          Double cartoon = (optionalImportDetails.get().getCartoon()) + ((orderDetails.getQuantity() - request.getQuantity()) /
                  optionalImportDetails.get().getUnitCartoon());
          Double piece = (optionalImportDetails.get().getPiece()) + ((orderDetails.getQuantity() - request.getQuantity()) /
                  optionalImportDetails.get().getUnitPiece());
          updateStockDataWhenEdit((orderDetails.getQuantity() - request.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        }
      } else if ((Objects.equals(uom, orderDetails.getUom())) && Objects.equals(uom, UOM.PIECE)) {
        if (orderDetails.getQuantity() < request.getQuantity()) {
          Double piece = optionalImportDetails.get().getPiece() - (request.getQuantity() - orderDetails.getQuantity());

          Double cartoon = optionalImportDetails.get().getCartoon() - (((request.getQuantity() - orderDetails.getQuantity()) *
                  optionalImportDetails.get().getUnitPiece()) / optionalImportDetails.get().getUnitCartoon());

          Double kgLt = optionalImportDetails.get().getKgLt() - ((request.getQuantity() - orderDetails.getQuantity()) *
                  optionalImportDetails.get().getUnitPiece());

          updateStockData((request.getQuantity() - orderDetails.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        } else {
          Double piece = optionalImportDetails.get().getPiece() + (orderDetails.getQuantity() - request.getQuantity());

          Double cartoon = optionalImportDetails.get().getCartoon() + (((orderDetails.getQuantity() - request.getQuantity()) *
                  optionalImportDetails.get().getUnitPiece()) / optionalImportDetails.get().getUnitCartoon());

          Double kgLt = optionalImportDetails.get().getKgLt() + ((orderDetails.getQuantity() - request.getQuantity()) *
                  optionalImportDetails.get().getUnitPiece());

          updateStockDataWhenEdit((orderDetails.getQuantity() - request.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        }
      } else {
        if (orderDetails.getQuantity() < request.getQuantity()) {
          Double cartoon = optionalImportDetails.get().getCartoon() - (request.getQuantity() - orderDetails.getQuantity());

          Double piece = optionalImportDetails.get().getPiece() -
                  (((request.getQuantity() - orderDetails.getQuantity()) * optionalImportDetails.get().getUnitCartoon())
                          / optionalImportDetails.get().getUnitPiece());

          Double kgLt = optionalImportDetails.get().getKgLt() - ((request.getQuantity() - orderDetails.getQuantity()) *
                  optionalImportDetails.get().getUnitCartoon());

          updateStockData((request.getQuantity() - orderDetails.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        } else {
          Double cartoon = optionalImportDetails.get().getCartoon() + (orderDetails.getQuantity() - request.getQuantity());

          Double piece = optionalImportDetails.get().getPiece() +
                  (((orderDetails.getQuantity() + request.getQuantity()) * optionalImportDetails.get().getUnitCartoon())
                          / optionalImportDetails.get().getUnitPiece());

          Double kgLt = optionalImportDetails.get().getKgLt() + ((orderDetails.getQuantity() + request.getQuantity()) *
                  optionalImportDetails.get().getUnitCartoon());

          updateStockDataWhenEdit((orderDetails.getQuantity() - request.getQuantity()), orderDetails.getProduct());
          optionalImportDetails.get().setCartoon(cartoon);
          optionalImportDetails.get().setPiece(piece);
          optionalImportDetails.get().setKgLt(kgLt);
          importDetailsRepository.save(optionalImportDetails.get());
        }
      }
    } else {
      throw new RequestValidationException("Product is not found");
    }
  }

  @Transactional
  private void updateStockDataWhenEdit(Double quantity, Product product) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isEmpty()) {
      throw new RequestValidationException("Stock is not found for this product");
    } else {
      optionalStock.get().setProduct(optionalStock.get().getProduct());
      optionalStock.get().setTotalSell(optionalStock.get().getTotalSell() - quantity);
      optionalStock.get().setInStock(optionalStock.get().getInStock() + quantity);
      stockRepository.save(optionalStock.get());
    }
  }

  private User setUser(String user) {
    return null;
  }

  private String dateConversion(String date) {
    DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate parsedDate = LocalDate.parse(date, originalFormatter);

    // Format the LocalDate object using the new format "MM-dd-yyyy"
    DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println(parsedDate.format(newFormatter));
    return parsedDate.format(newFormatter);
  }
}