package com.flagship.controller;

import com.flagship.dto.request.*;
import com.flagship.dto.response.*;
import com.flagship.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/common")
public class CommonController {
  private final CommonService commonService;

  @Autowired
  public CommonController(CommonService commonService) {
    this.commonService = commonService;
  }
  @PostMapping(
          value = "/product",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ProductResponse> createProduct(@Valid @NotNull @RequestBody ProductRequest productRequest) {
    ProductResponse response = commonService.createProduct(productRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/brand",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<BrandResponse> addBrand(@Valid @NotNull @RequestBody BrandRequest request) {
    BrandResponse response = commonService.addBrand(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/country",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CountryResponse> addCountry(@Valid @NotNull @RequestBody CountryRequest request) {
    CountryResponse response = commonService.addCountry(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/category",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CategoriesResponse> addCategory(@Valid @NotNull @RequestBody CategoriesRequest request) {
    CategoriesResponse response = commonService.addCategory(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/sale",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SaleResponse> addSale(@RequestBody @Valid @NotNull SaleRequest request) {
    SaleResponse response = commonService.addSale(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/branch",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<BranchResponse> addBranch(@RequestBody @Valid @NotNull BranchRequest request) {
    BranchResponse response = commonService.addBranch(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/supplier",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SupplierResponse> addSupplier(@Valid @NotNull @RequestBody SupplierRequest request) {
    SupplierResponse response = commonService.addSupplier(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/product",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllProductResponse> getProduct() {
    AllProductResponse allProduct = commonService.getProduct();
    return new ResponseEntity<>(allProduct, HttpStatus.OK);
  }
  @GetMapping(
          value = "/stock",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllStockResponse> getStock() {
    AllStockResponse allStockResponse = commonService.getStock();
    return new ResponseEntity<>(allStockResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/brand",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllBrandResponse> getBrand() {
    AllBrandResponse allBrandResponse = commonService.getBrand();
    return new ResponseEntity<>(allBrandResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/branch",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllBranchResponse> getBranch(@RequestParam(value = "supplier") String supplier) {
    AllBranchResponse allBranchResponse = commonService.getAllBranch(supplier);
    return new ResponseEntity<>(allBranchResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/country",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllCountryResponse> getCountry() {
    AllCountryResponse allCountryResponse = commonService.getCountry();
    return new ResponseEntity<>(allCountryResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/category",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllCategoryResponse> getCategory() {
    AllCategoryResponse allCategoryResponse = commonService.getCategory();
    return new ResponseEntity<>(allCategoryResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/sale",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SingleSale> getSale(@RequestParam(value = "product") String product,
                                            @RequestParam(value = "supplier") String supplier) {
    SingleSale singleSale = commonService.getSale(product, supplier);
    return new ResponseEntity<>(singleSale, HttpStatus.OK);
  }
  @GetMapping(
          value = "/supplier",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<GetAllSuppliers> getSupplier() {
    GetAllSuppliers response = commonService.getSupplier();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/customer",
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CustomerResponse> addCustomer(@Valid @NotEmpty @NotNull
                                                      @RequestBody CustomerRequest customerRequest) {
    CustomerResponse customerResponse = commonService.addCustomer(customerRequest);
    return new ResponseEntity<>(customerResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/customer",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<GetAllCustomer> getCustomer() {
    GetAllCustomer response = commonService.getCustomer();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @PostMapping(
          value = "/sales_person",
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SalesPersonResponse> addSalesPerson(@Valid @NotEmpty @NotNull
                                                            @RequestBody SalesPersonRequest salesPersonRequest) {
    SalesPersonResponse salesPersonResponse = commonService.addSalesPerson(salesPersonRequest);
    return new ResponseEntity<>(salesPersonResponse, HttpStatus.OK);
  }
  @GetMapping(
          value = "/sales_person",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllSalesPersonResponse> getSalesPerson() {
    AllSalesPersonResponse allSalesPersonResponse = commonService.getSalesPerson();
    return new ResponseEntity<>(allSalesPersonResponse, HttpStatus.OK);
  }
  @PostMapping(
          value = "/wastage",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<WastageAddResponse> addWastage(@Valid @NotNull @RequestBody WastageMasterRequest wastageMasterRequest) {
    WastageAddResponse wastageResponse = commonService.addWastage(wastageMasterRequest);
    return new ResponseEntity<>(wastageResponse, HttpStatus.OK);
  }

  @PostMapping(
          value = "/return",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ReturnAddResponse> addReturn(@Valid @NotNull @RequestBody ReturnMasterRequest request) {
    ReturnAddResponse response = commonService.addReturn(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/revenue",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllProductRevenueResponse> calculateRevenue() {
    AllProductRevenueResponse response = commonService.calculateRevenue();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/welcome",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<FinalResponse> getAllProductAndArticleAndSale() {
    FinalResponse response = commonService.getAllProductAndArticleAndSale();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/wastage",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SuccessWastageResponseUsingSerial> getAllWastage() {
    SuccessWastageResponseUsingSerial response = commonService.getAllWastage();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/return",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SuccessReturnResponseUsingSerial> getAllReturn() {
    SuccessReturnResponseUsingSerial response = commonService.getAllReturn();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping(
          value = "/requisition",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SuccessRequisitionResponseUsingSerial> getAllRequisition() {
    SuccessRequisitionResponseUsingSerial response = commonService.getAllRequisition();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
