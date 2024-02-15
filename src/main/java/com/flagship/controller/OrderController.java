package com.flagship.controller;

import com.flagship.dto.request.*;
import com.flagship.dto.response.*;
import com.flagship.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE
  )

  private ResponseEntity<OrderResponse> createOrder(@Valid @NotNull @RequestBody OrderMasterRequest orderMasterRequest) {
    OrderResponse orderMasterResponse = orderService.createOrder(orderMasterRequest);
    return new ResponseEntity<>(orderMasterResponse, HttpStatus.OK);
  }

  @GetMapping(
          value = "/invoice",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ChallanResponse> getLastInvoice() {
    ChallanResponse response = orderService.getLastInvoice();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/uom/available",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<UomAndAvailableResponse> getUomAndAvailable(@RequestParam(value = "product") String product,
                                                                    @RequestParam(value = "shipment") String shipment) {
    UomAndAvailableResponse response = orderService.getUomAndAvailable(product, shipment);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/bills",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllBillsResponse> getBills() {
    AllBillsResponse response = orderService.getBills();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/bill/details",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<BillsDetailsResponse> getBillsDetails(@RequestParam(value = "order") Long orderId) {
    BillsDetailsResponse response = orderService.getBillsDetails(orderId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/bill/vat",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<VatDetailsResponse> getVatDetails(@RequestParam(value = "order") Long orderId) {
    VatDetailsResponse response = orderService.getVatDetails(orderId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/price",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<LastPriceResponse> getProductLastPrice(@RequestParam(value = "customer") String customer,
                                                               @RequestParam(value = "product") String product) {
    LastPriceResponse response = orderService.getProductLastPrice(customer, product);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/daily",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<DailyOrderResponse> getDailyOrder(@RequestParam(value = "date", required = false) String date,
                                                          @RequestParam(value = "type") String type) {
    DailyOrderResponse response = orderService.getDailyOrder(date, type);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/short",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<DailyOrderShortResponse> getDailyOrderShort(@RequestParam(value = "date", required = false)
                                                                    String date, @RequestParam(value = "type") String type) {
    DailyOrderShortResponse response = orderService.getDailyOrderShort(date, type);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/table",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OrderTableResponse> getTable() {
    OrderTableResponse response = orderService.getTable();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<EditOrderResponse> editOrder(@RequestParam(value = "order") Long order,
                                                     @Valid @NotNull @RequestBody List<EditOrderRequest> updateOrderRequest) {
    EditOrderResponse response = orderService.editOrder(order, updateOrderRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/return",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ReturnDetailsResponse> getReturn(@RequestParam(value = "serial") Long serial) {
    ReturnDetailsResponse response = orderService.getReturn(serial);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/pending",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllPendingOrdersResponse> getPendingOrder() {
    AllPendingOrdersResponse response = orderService.getPendingOrder();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(
          value = "/status",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OrderRequisitionResponse> updateOrderStatus(@RequestBody @Valid @NotNull
                                                                    List<UpdateOrderRequest> selectedRows) {
    OrderRequisitionResponse response = orderService.updateOrderStatus(selectedRows);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(
          value = "/payment",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OrderPaymentResponse> createOrderPayment(@RequestBody @Valid @NotNull PaymentRequest request) {
    OrderPaymentResponse response = orderService.createPayment(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/payments",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllPaymentsResponse> getAllPayments() {
    AllPaymentsResponse response = orderService.getAllPayments();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/ledger",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<LedgerResponse> getAllLedger(@RequestParam(value = "customer") String customer) {
    LedgerResponse response = orderService.getAllLedger(customer);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/product",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllOrderIdResponse> getAllOrderId(@RequestParam(value = "product") String product) {
    AllOrderIdResponse response = orderService.getAllOrderId(product);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/uom",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<GetUomResponse> getProductUom(@RequestParam(value = "product") String product,
                                                      @RequestParam(value = "order") Long order,
                                                      @RequestParam(value = "warehouse") String warehouse) {
    GetUomResponse response = orderService.getProductUom(product, order, warehouse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/waiting",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllPendingOrdersResponse> getWaitingOrder() {
    AllPendingOrdersResponse response = orderService.getWaitingOrder();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(
          value = "/waiting_status",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OrderRequisitionResponse> updateOrderWaitingStatus(@RequestBody @Valid @NotNull
                                                                           UpdateOrder updateOrder) {
    OrderRequisitionResponse response = orderService.updateOrderWaitingStatus(updateOrder);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/requisition",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OrderRequisitionResponse> getRequisition(@RequestParam(value = "serial") Long serial) {
    OrderRequisitionResponse response = orderService.getRequisition(serial);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/warehouse",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<OrderWarehouseResponse> getWarehouse(@RequestParam(value = "product") String product,
                                                        @RequestParam(value = "order") Long order) {
    OrderWarehouseResponse response = orderService.getAllWarehouse(product, order);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
