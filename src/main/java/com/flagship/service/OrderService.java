package com.flagship.service;

import com.flagship.dto.request.*;
import com.flagship.dto.response.*;

import java.util.List;

public interface OrderService {
  OrderResponse createOrder(OrderMasterRequest orderMasterRequest);

  ChallanResponse getLastInvoice(String company);

  UomAndAvailableResponse getUomAndAvailable(String product, String shipment);

  AllBillsResponse getBills();

  BillsDetailsResponse getBillsDetails(Long orderId);

  VatDetailsResponse getVatDetails(Long orderId);

  LastPriceResponse getProductLastPrice(String customer, String product);

  DailyOrderResponse getDailyOrder(String date, String type);

  DailyOrderShortResponse getDailyOrderShort(String date, String type);

  OrderTableResponse getTable();

  EditOrderResponse editOrder(Long order, List<EditOrderRequest> updateOrderRequest);

  ReturnDetailsResponse getReturn(Long serial);

  AllPendingOrdersResponse getPendingOrder();

  OrderRequisitionResponse updateOrderStatus(List<UpdateOrderRequest> updateOrderRequest);

  OrderPaymentResponse createPayment(PaymentRequest request);

  AllPaymentsResponse getAllPayments();

  LedgerResponse getAllLedger(String customer);

  AllOrderIdResponse getAllOrderId(String product);

  GetUomResponse getProductUom(String product, Long order, String warehouse);

  AllPendingOrdersResponse getWaitingOrder();

  OrderRequisitionResponse updateOrderWaitingStatus(UpdateOrder updateOrder);

  OrderRequisitionResponse getRequisition(Long serial);

  OrderWarehouseResponse getAllWarehouse(String product, Long order);

  DeleteResponse deleteOrderDetails(Long order, String product, String warehouse);
}
