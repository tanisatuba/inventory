package com.flagship.service;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.request.MoveRequest;
import com.flagship.dto.response.*;

import java.util.List;

public interface ImportService {
  ImportResponse addImport(ImportRequest importRequest);

  ShipmentResponse getShipment(String product);

  AllImportResponse getAllImport();

  GetUomAndAvailableResponse getProductUomAndAvailable(String product, String shipment, String warehouse);

  WastageDetailsResponse getWastage(Long serial);

  WarehouseResponse getWarehouse(String product, String shipment);

  ImportResponse moveImport(List<MoveRequest> moveRequestList);
}