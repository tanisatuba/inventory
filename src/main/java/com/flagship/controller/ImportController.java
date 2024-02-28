package com.flagship.controller;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.request.MoveRequest;
import com.flagship.dto.response.*;
import com.flagship.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/import")
public class ImportController {
  private final ImportService importService;

  @Autowired
  public ImportController(ImportService importService) {
    this.importService = importService;
  }

  @PostMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ImportResponse> addImport(@Valid @NotNull @RequestBody ImportRequest importRequest) {
    ImportResponse response = importService.addImport(importRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/shipment",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ShipmentResponse> getShipment(@RequestParam(value = "product") String product) {
    ShipmentResponse response = importService.getShipment(product);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllImportResponse> getAllImport() {
    AllImportResponse response = importService.getAllImport();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/uom/available",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<GetUomAndAvailableResponse> getProductUomAndAvailable(@RequestParam(value = "product") String product,
                                                                              @RequestParam(value = "shipment") String shipment,
                                                                              @RequestParam(value = "warehouse") String warehouse) {
    GetUomAndAvailableResponse response = importService.getProductUomAndAvailable(product, shipment, warehouse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/wastage",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<WastageDetailsResponse> getWastage(@RequestParam(value = "serial") Long serial) {
    WastageDetailsResponse response = importService.getWastage(serial);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/warehouse",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<WarehouseResponse> getWarehouse(@RequestParam(value = "product") String product,
                                                        @RequestParam(value = "shipment") String shipment) {
    WarehouseResponse response = importService.getWarehouse(product, shipment);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/move",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ImportResponse> moveImport(@Valid @NotNull @RequestBody List<MoveRequest> moveRequestList) {
    ImportResponse response = importService.moveImport(moveRequestList);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping(
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<DeleteResponse> deleteProduct(@RequestParam(value = "product") String product,
                                                      @RequestParam(value = "shipment") String shipment,
                                                      @RequestParam(value = "warehouse") String warehouse) {
    DeleteResponse response = importService.deleteProduct(product, shipment, warehouse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
