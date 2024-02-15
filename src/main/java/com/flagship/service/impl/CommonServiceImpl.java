package com.flagship.service.impl;

import com.flagship.constant.enums.Cause;
import com.flagship.constant.enums.CustomerType;
import com.flagship.constant.enums.UOM;
import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.request.*;
import com.flagship.dto.response.*;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.*;
import com.flagship.repository.*;
import com.flagship.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommonServiceImpl implements CommonService {
  private final BrandRepository brandRepository;
  private final CountryRepository countryRepository;
  private final CategoriesRepository categoriesRepository;
  private final UserRepository userRepository;
  private final BranchRepository branchRepository;
  private final SaleRepository saleRepository;
  private final SupplierRepository supplierRepository;
  private final ProductRepository productRepository;
  private final StockRepository stockRepository;
  private final CustomerRepository customerRepository;
  private final SalesPersonRepository salesPersonRepository;
  private final WastageRepository wastageRepository;
  private final ImportMasterRepository importMasterRepository;
  private final OrderMasterRepository orderMasterRepository;
  private final ReturnsRepository returnsRepository;
  private final ImportDetailsRepository importDetailsRepository;
  private final OrderDetailsRepository orderDetailsRepository;
  private final RequisitionRepository requisitionRepository;

  @Autowired
  public CommonServiceImpl(BrandRepository brandRepository, CountryRepository countryRepository,
                           CategoriesRepository categoriesRepository, UserRepository userRepository,
                           BranchRepository branchRepository, SaleRepository saleRepository,
                           SupplierRepository supplierRepository, ProductRepository productRepository,
                           StockRepository stockRepository, CustomerRepository customerRepository,
                           SalesPersonRepository salesPersonRepository, WastageRepository wastageRepository,
                           ImportMasterRepository importMasterRepository, OrderMasterRepository orderMasterRepository,
                           ReturnsRepository returnsRepository, ImportDetailsRepository importDetailsRepository,
                           OrderDetailsRepository orderDetailsRepository, RequisitionRepository requisitionRepository) {
    this.brandRepository = brandRepository;
    this.countryRepository = countryRepository;
    this.categoriesRepository = categoriesRepository;
    this.userRepository = userRepository;
    this.branchRepository = branchRepository;
    this.saleRepository = saleRepository;
    this.supplierRepository = supplierRepository;
    this.productRepository = productRepository;
    this.stockRepository = stockRepository;
    this.customerRepository = customerRepository;
    this.salesPersonRepository = salesPersonRepository;
    this.wastageRepository = wastageRepository;
    this.importMasterRepository = importMasterRepository;
    this.orderMasterRepository = orderMasterRepository;
    this.returnsRepository = returnsRepository;
    this.importDetailsRepository = importDetailsRepository;
    this.orderDetailsRepository = orderDetailsRepository;
    this.requisitionRepository = requisitionRepository;
  }

  @Override
  public ProductResponse createProduct(ProductRequest productRequest) {
    Boolean productExist = checkProduct(productRequest.getProductId());
    if (productExist) {
      throw new RequestValidationException("Product Id is exist. Give a valid Product Id");
    }
    Product product = new Product();
    product.setProductId(productRequest.getProductId());
    product.setProductName(productRequest.getProductName());
    Optional<User> user = userRepository.findByEmail(productRequest.getUserEmail());
    if (user.isEmpty()) {
      throw new RequestValidationException("User Email did not exist");
    }
    product.setCreatedBy(user.get());
    productRepository.save(product);
    return ProductResponse.from("Product Added Successful", product);
  }

  @Override
  public BrandResponse addBrand(BrandRequest request) {
    Optional<Brand> optionalBrand = brandRepository.findByBrandId(request.getBrandId());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalBrand.isPresent()) {
      throw new RequestValidationException("Brand is exist");
    } else {
      Brand brand = new Brand();
      brand.setBrandId(request.getBrandId());
      brand.setBrandName(request.getBrandName());
      brand.setCreatedBy(user.get());
      brandRepository.save(brand);
      return BrandResponse.from("Brand Added Successfully", brand);
    }
  }

  @Override
  public CountryResponse addCountry(CountryRequest request) {
    Optional<Country> optionalCountry = countryRepository.findByCountryId(request.getCountryId());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalCountry.isPresent()) {
      throw new RequestValidationException("Country is exist");
    } else {
      Country country = new Country();
      country.setCountryId(request.getCountryId());
      country.setCountryName(request.getCountryName());
      country.setCreatedBy(user.get());
      countryRepository.save(country);
      return CountryResponse.from("Country Added Successfully", country);
    }
  }

  @Override
  public CategoriesResponse addCategory(CategoriesRequest request) {
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    Optional<Categories> optionalCategories = categoriesRepository.findByCategoryId(request.getCategoryId());
    if (optionalCategories.isPresent()) {
      throw new RequestValidationException("Category is exist");
    } else {
      Categories categories = new Categories();
      categories.setCategoryId(request.getCategoryId());
      categories.setCategoryName(request.getCategoryName());
      categories.setCreatedBy(user.get());
      categoriesRepository.save(categories);
      return CategoriesResponse.from("Category Added Successfully", categories);
    }
  }

  @Override
  public SaleResponse addSale(SaleRequest request) {
    Optional<Sale> optionalSale1 = saleRepository.findBySupplierAndProductAndArticleAndSaleCode(
            getSupplier(request.getSupplier().getValue()), getProduct(request.getProduct().getValue())
            , request.getArticle(), request.getSaleCode());
    Optional<Sale> optionalSale2 = saleRepository.findBySupplierAndProductAndArticle(
            getSupplier(request.getSupplier().getValue()), getProduct(request.getProduct().getValue())
            , request.getArticle());
    Optional<Sale> optionalSale3 = saleRepository.findBySupplierAndProductAndSaleCode(
            getSupplier(request.getSupplier().getValue()), getProduct(request.getProduct().getValue()),
            request.getSaleCode());
    Optional<Sale> optionalSale4 = saleRepository.findBySupplierAndProduct(
            getSupplier(request.getSupplier().getValue()), getProduct(request.getProduct().getValue()));
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalSale1.isPresent() || optionalSale2.isPresent() || optionalSale3.isPresent() || optionalSale4.isPresent()) {
      throw new RequestValidationException("Article and Sale code is exist.");
    }
    Sale sale = new Sale();
    sale.setSupplier(getSupplier(request.getSupplier().getValue()));
    sale.setProduct(getProduct(request.getProduct().getValue()));
    sale.setArticle(request.getArticle());
    sale.setSaleCode(request.getSaleCode());
    sale.setCreatedBy(user.get());
    saleRepository.save(sale);
    return SaleResponse.from("Sale And Article code Added Successfully", sale);

  }

  private Product getProduct(String product) {
    Optional<Product> optionalProduct = productRepository.findByProductId(product);
    if (optionalProduct.isPresent()) {
      return optionalProduct.get();
    } else {
      throw new RequestValidationException("Product is not exist");
    }
  }

  private Supplier getSupplier(String supplier) {
    Optional<Supplier> optionalSupplier = supplierRepository.findBySupplierId(supplier);
    if (optionalSupplier.isPresent()) {
      return optionalSupplier.get();
    } else {
      throw new RequestValidationException("Supplier is not present");
    }
  }

  @Override
  public BranchResponse addBranch(BranchRequest request) {
    Optional<Branch> optionalBranch = branchRepository.findByBranchNameAndSupplier(request.getName(),
            getSupplier(request.getSupplier().getValue()));
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalBranch.isPresent()) {
      throw new RequestValidationException("Branch is exist");
    } else {
      Branch branch = new Branch();
      branch.setSupplier(getSupplier(request.getSupplier().getValue()));
      branch.setBranchName(request.getName());
      branch.setAddress(request.getAddress());
      branch.setCreatedBy(user.get());
      branchRepository.save(branch);
      return BranchResponse.from("Branch Added Successfully", branch);
    }
  }

  @Override
  public SupplierResponse addSupplier(SupplierRequest request) {
    Optional<Supplier> optionalSupplier = supplierRepository.findBySupplierId(request.getSupplierId());
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    if (optionalSupplier.isPresent()) {
      throw new RequestValidationException("Supplier is exist");
    } else {
      Supplier supplier = new Supplier();
      supplier.setSupplierId(request.getSupplierId());
      supplier.setSupplierName(request.getSupplierName());
      supplier.setCreatedBy(user.get());
      supplierRepository.save(supplier);
      return SupplierResponse.from("Supplier Added Successfully", supplier);
    }
  }

  @Override
  public AllProductResponse getProduct() {
    List<Product> allProduct = (List<Product>) productRepository.findAll();
    List<SingleProductResponse> getProductResponse = new ArrayList<>();
    for (Product product : allProduct) {
      getProductResponse.add(SingleProductResponse.from(product));
    }
    return AllProductResponse.from(getProductResponse);
  }

  @Override
  public AllStockResponse getStock() {
    List<Stock> stockList = (List<Stock>) stockRepository.findAll();
    List<StockResponse> stockResponseList = new ArrayList<>();
    for (Stock stock : stockList) {
      stockResponseList.add(StockResponse.from(stock));
    }
    return AllStockResponse.from(stockResponseList);
  }

  @Override
  public AllBrandResponse getBrand() {
    List<Brand> brandList = (List<Brand>) brandRepository.findAll();
    List<SingleBrandResponse> singleBrandResponses = new ArrayList<>();
    for (Brand brand : brandList) {
      singleBrandResponses.add(SingleBrandResponse.from(brand));
    }
    return AllBrandResponse.from(singleBrandResponses);
  }

  @Override
  public AllCountryResponse getCountry() {
    List<Country> countryList = (List<Country>) countryRepository.findAll();
    List<SingleCountryResponse> singleCountryResponseList = new ArrayList<>();
    for (Country country : countryList) {
      singleCountryResponseList.add(SingleCountryResponse.from(country));
    }
    return AllCountryResponse.from(singleCountryResponseList);
  }

  @Override
  public AllCategoryResponse getCategory() {
    List<Categories> categoriesList = (List<Categories>) categoriesRepository.findAll();
    List<SingleCategoryResponse> singleCategoryResponseList = new ArrayList<>();
    for (Categories categories : categoriesList) {
      singleCategoryResponseList.add(SingleCategoryResponse.from(categories));
    }
    return AllCategoryResponse.from(singleCategoryResponseList);
  }

  @Override
  public SingleSale getSale(String product, String supplier) {
    Optional<Sale> optionalSale = saleRepository.findByProductAndSupplier(getProduct(product), getSupplier(supplier));
    return optionalSale.map(sale -> SingleSale.from(sale.getSaleCode(), sale.getArticle()))
            .orElse(null);
  }

  @Override
  public GetAllSuppliers getSupplier() {
    List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
    List<SingleSupplier> singleSuppliers = new ArrayList<>();
    for (Supplier supplier : supplierList) {
      singleSuppliers.add(SingleSupplier.from(supplier));
    }
    return GetAllSuppliers.from(singleSuppliers);
  }

  private Boolean checkProduct(String productId) {
    Optional<Product> product = productRepository.findByProductId(productId);
    return product.isPresent();
  }

  @Override
  public CustomerResponse addCustomer(CustomerRequest customerRequest) {
    customerRequest.validate();
    Boolean checkCustomerExist = customerExist(customerRequest.getId());
    if (checkCustomerExist) {
      throw new RequestValidationException("Customer id is exist");
    }
    Optional<User> user = userRepository.findByEmail(customerRequest.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    Customer customer = new Customer();
    customer.setCustomerId(customerRequest.getId());
    customer.setCustomerName(customerRequest.getName());
    customer.setCompany(customerRequest.getCompany());
    customer.setCustomerType(CustomerType.fromName(customerRequest.getType().getName()));
    customer.setAddress(customerRequest.getAddress());
    customer.setCreditTerm(customerRequest.getCreditTerm() != null ? customerRequest.getCreditTerm() : 0);
    customer.setPhoneNumber(customerRequest.getContact());
    customer.setBinNo(customerRequest.getBinNo());
    customer.setSupplier(!(customerRequest.getSupplier().isEmpty()) ? getSupplier(customerRequest.getSupplier()) : null);
    customer.setCreatedBy(user.get());
    customerRepository.save(customer);
    return CustomerResponse.from("Customer added Successfully", customer);
  }

  private Boolean customerExist(String customerId) {
    Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
    return customer.isPresent();
  }

  private Branch getBranch(String branch, String supplier) {
    Optional<Branch> optionalBranch = branchRepository.findByBranchNameAndSupplier(branch, getSupplier(supplier));
    if (optionalBranch.isPresent()) {
      return optionalBranch.get();
    } else {
      throw new RequestValidationException("Branch is not present");
    }
  }

  @Override
  public GetAllCustomer getCustomer() {
    List<Customer> customerList = (List<Customer>) customerRepository.findAll();
    List<SingleCustomer> singleCustomers = new ArrayList<>();
    for (Customer customer : customerList) {
      singleCustomers.add(SingleCustomer.from(customer));
    }
    return GetAllCustomer.from(singleCustomers);
  }

  @Override
  public AllBranchResponse getAllBranch(String supplier) {
    List<Branch> branchList = branchRepository.findBySupplier(getSupplier(supplier));
    List<SingleBranchResponse> singleBranchResponses = new ArrayList<>();
    for (Branch branch : branchList) {
      singleBranchResponses.add(SingleBranchResponse.from(branch));
    }
    return AllBranchResponse.from(singleBranchResponses);
  }

  @Override
  public SalesPersonResponse addSalesPerson(SalesPersonRequest salesPersonRequest) {
    salesPersonRequest.validate();
    Boolean checkSalesPersonExist = salesPersonExist(salesPersonRequest.getSalesPersonId());
    if (checkSalesPersonExist) {
      throw new RequestValidationException("Sales Person exist");
    }
    Optional<User> user = userRepository.findByEmail(salesPersonRequest.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not found");
    }
    SalesPerson salesPerson = new SalesPerson();
    salesPerson.setSalesPersonId(salesPersonRequest.getSalesPersonId());
    salesPerson.setSalesPersonName(salesPersonRequest.getSalesPersonName());
    salesPerson.setPhoneNumber(salesPersonRequest.getPhoneNumber());
    salesPerson.setArea(salesPersonRequest.getArea());
    salesPerson.setCreatedBy(user.get());
    salesPersonRepository.save(salesPerson);
    return SalesPersonResponse.from("Sales Person added Successfully", salesPerson);
  }

  @Override
  public AllSalesPersonResponse getSalesPerson() {
    List<SalesPerson> salesPersonList = (List<SalesPerson>) salesPersonRepository.findAll();
    List<SingleSalesPersonResponse> singleSalesPersonResponseList = new ArrayList<>();
    for (SalesPerson salesPerson : salesPersonList) {
      singleSalesPersonResponseList.add(SingleSalesPersonResponse.from(salesPerson));
    }
    return AllSalesPersonResponse.from(singleSalesPersonResponseList);
  }

  private Boolean salesPersonExist(String salesPersonId) {
    Optional<SalesPerson> salesPerson = salesPersonRepository.findBySalesPersonId(salesPersonId);
    return salesPerson.isPresent();
  }

  @Override
  public WastageAddResponse addWastage(WastageMasterRequest request) {
    request.validate();
    List<WastageDetailsRequest> wastageDetailsRequests = request.getWastageDetailsRequestList();
    for (WastageDetailsRequest wastageDetailsRequest : wastageDetailsRequests) {
      wastageDetailsRequest.validate();
    }
    Warehouse warehouse = request.getWarehouse();
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    List<WastageResponse> wastageResponses = new ArrayList<>();
    Long serialNo = getSerialNo();
    for (WastageDetailsRequest wastageDetailsRequest : wastageDetailsRequests) {
      Warehouse warehouse1 = Warehouse.fromName(wastageDetailsRequest.getWastageFrom().getValue());
      Optional<ImportDetails> optionalImportDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(
              getProduct(wastageDetailsRequest.getProduct().getValue()),
              getShipment(wastageDetailsRequest.getShipment().getValue()), Warehouse.valueOf(
                      wastageDetailsRequest.getWastageFrom().getValue()));
      if (optionalImportDetails.isPresent()) {
        Wastage wastage = new Wastage();
        wastage.setProduct(optionalImportDetails.get().getProduct());
        wastage.setShipment(optionalImportDetails.get().getImportMaster());
        if (wastageDetailsRequest.getUom().equals(UOM.LT)) {
          wastage.setCartoon(wastageDetailsRequest.getQuantity() / optionalImportDetails.get().getUnitCartoon());
          wastage.setPiece(wastageDetailsRequest.getQuantity() / optionalImportDetails.get().getUnitPiece());
          wastage.setKgLt(wastageDetailsRequest.getQuantity());
        }
        if (wastageDetailsRequest.getUom().equals(UOM.KG)) {
          wastage.setCartoon(wastageDetailsRequest.getQuantity() / optionalImportDetails.get().getUnitCartoon());
          wastage.setPiece(wastageDetailsRequest.getQuantity() / optionalImportDetails.get().getUnitPiece());
          wastage.setKgLt(wastageDetailsRequest.getQuantity());
        }
        if (wastageDetailsRequest.getUom().equals(UOM.PIECE)) {
          wastage.setCartoon(wastageDetailsRequest.getQuantity());
          wastage.setPiece((wastageDetailsRequest.getQuantity() * optionalImportDetails.get().getUnitPiece())
                  / optionalImportDetails.get().getUnitPiece());
          wastage.setKgLt(wastageDetailsRequest.getQuantity() * optionalImportDetails.get().getUnitPiece());
        }
        if (wastageDetailsRequest.getUom().equals(UOM.CARTOON)) {
          wastage.setCartoon((wastageDetailsRequest.getQuantity() * optionalImportDetails.get().getUnitPiece())
                  / optionalImportDetails.get().getUnitCartoon());
          wastage.setPiece(wastageDetailsRequest.getQuantity());
          wastage.setKgLt(wastageDetailsRequest.getQuantity() * optionalImportDetails.get().getUnitPiece());
        }
        wastage.setCreatedBy(user.get());
        wastage.setCause(Cause.fromName(wastageDetailsRequest.getCause().toString()));
        wastage.setWarehouse(warehouse);
        wastage.setSerialNo(serialNo);
        wastageRepository.save(wastage);
        updateStock(wastage, wastageDetailsRequest);
        wastageResponses.add(WastageResponse.from(wastage));
      } else {
        throw new RequestValidationException("Import not found.");
      }
    }
    return WastageAddResponse.from("Wastage added successfully", warehouse, user.get().getName(), wastageResponses);
  }

  private Long getSerialNo() {
    Optional<Wastage> optionalWastage = wastageRepository.findFirstByOrderBySerialNoDesc();
    Date currentDate = new Date();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
    String currentYear = yearFormat.format(currentDate);
    if (optionalWastage.isPresent()) {
      String serialId = String.valueOf(optionalWastage.get().getSerialNo()).substring(0, 2);
      if (!serialId.equals(currentYear)) {
        return Long.parseLong(currentYear + "000001");
      } else {
        return optionalWastage.get().getSerialNo() + 1;
      }
    } else {
      return Long.parseLong(currentYear + "000001");
    }
  }

  private void updateStock(Wastage wastage, WastageDetailsRequest wastageDetailsRequest) {
    Optional<ImportDetails> importDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(wastage.getProduct(),
            wastage.getShipment(), Warehouse.valueOf(wastageDetailsRequest.getWastageFrom().getValue()));
    if (importDetails.isPresent()) {
      importDetails.get().setCartoon(importDetails.get().getCartoon() - wastage.getCartoon());
      importDetails.get().setPiece(importDetails.get().getPiece() - wastage.getPiece());
      importDetails.get().setKgLt(importDetails.get().getKgLt() - wastage.getKgLt());
      if (importDetails.get().getUom().equals(UOM.KG)) {
        updateStockData(wastage.getProduct(), wastage.getKgLt());
      } else if (importDetails.get().getUom().equals(UOM.LT)) {
        updateStockData(wastage.getProduct(), wastage.getKgLt());
      } else if (importDetails.get().getUom().equals(UOM.PIECE)) {
        updateStockData(wastage.getProduct(), wastage.getPiece());
      } else {
        updateStockData(wastage.getProduct(), wastage.getCartoon());
      }
      importDetailsRepository.save(importDetails.get());
    }
  }

  private void updateStockData(Product product, Double quantity) {
    Optional<Stock> optionalStock = stockRepository.findByProduct(product);
    if (optionalStock.isPresent()) {
      optionalStock.get().setTotalBuy(optionalStock.get().getTotalBuy());
      optionalStock.get().setInStock(optionalStock.get().getInStock() - quantity);
      stockRepository.save(optionalStock.get());
    }
  }

  @Override
  public ReturnAddResponse addReturn(ReturnMasterRequest request) {
    request.validate();
    List<ReturnDetailsRequest> returnDetailsRequestList = request.getReturnDetailsRequestList();
    for (ReturnDetailsRequest detailsRequest : returnDetailsRequestList) {
      detailsRequest.validate();
    }
    Returns returns = new Returns();
    Optional<User> user = userRepository.findByEmail(request.getUser());
    if (user.isEmpty()) {
      throw new RequestValidationException("User not exist");
    }
    Customer customer = getCustomerById(request.getCustomer().getValue());
    Branch branch = getBranch(request.getBranch().getValue(), customer.getSupplier().getSupplierId());
    Long serialNo = getRequestSerialNo();
    List<ReturnResponse> returnResponses = new ArrayList<>();
    for (ReturnDetailsRequest detailsRequest : returnDetailsRequestList) {
      Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findByOrderAndProductAndWarehouse(
              getOrderId(Long.valueOf(detailsRequest.getOrder().getValue())),
              getProduct(detailsRequest.getProduct().getValue()), Warehouse.valueOf(detailsRequest.getReturnTo().getValue()));
      if (optionalOrderDetails.isPresent()) {
        returns.setProduct(getProduct(detailsRequest.getProduct().getValue()));
        returns.setOrder(getOrderId(Long.valueOf(detailsRequest.getOrder().getValue())));
        Optional<ImportDetails> importDetails = importDetailsRepository.findByProductAndImportMasterAndWarehouse(
                optionalOrderDetails.get().getProduct(), optionalOrderDetails.get().getShipment(),
                optionalOrderDetails.get().getWarehouse());
        if (importDetails.isPresent()) {
          if (detailsRequest.getUom().equals(UOM.LT)) {
            returns.setCartoon(detailsRequest.getQuantity() / importDetails.get().getUnitCartoon());
            returns.setPiece(detailsRequest.getQuantity() / importDetails.get().getUnitPiece());
            returns.setKgLt(detailsRequest.getQuantity());
          }
          if (detailsRequest.getUom().equals(UOM.KG)) {
            returns.setCartoon(detailsRequest.getQuantity() / importDetails.get().getUnitCartoon());
            returns.setPiece(detailsRequest.getQuantity() / importDetails.get().getUnitPiece());
            returns.setKgLt(detailsRequest.getQuantity());
          }
          if (detailsRequest.getUom().equals(UOM.PIECE)) {
            returns.setCartoon(detailsRequest.getQuantity());
            returns.setPiece((detailsRequest.getQuantity() * importDetails.get().getUnitPiece())
                    / importDetails.get().getUnitPiece());
            returns.setKgLt(detailsRequest.getQuantity() * importDetails.get().getUnitPiece());
          }
          if (detailsRequest.getUom().equals(UOM.CARTOON)) {
            returns.setCartoon((detailsRequest.getQuantity() * importDetails.get().getUnitPiece())
                    / importDetails.get().getUnitCartoon());
            returns.setPiece(detailsRequest.getQuantity());
            returns.setKgLt(detailsRequest.getQuantity() * importDetails.get().getUnitPiece());
          }
          returns.setCreatedBy(user.get());
          returns.setDeliveryMan(request.getDelivery());
          returns.setCause(Cause.fromName(detailsRequest.getCause().toString()));
          returns.setSerialNo(serialNo);
          returns.setDeliveryMan(request.getDelivery());
          returns.setWarehouse(request.getWarehouse());
          returns.setCustomer(customer);
          returns.setBranch(branch);
          returnsRepository.save(returns);
          returnResponses.add(ReturnResponse.from(returns));
        } else {
          throw new RequestValidationException("Product not found");
        }
      } else {
        throw new RequestValidationException("Order not found");
      }
    }
    return ReturnAddResponse.from("Returns add successfully", request.getWarehouse(), request.getDelivery()
            , customer.getCustomerName(), branch.getBranchName(), returnResponses);
  }

  private Customer getCustomerById(String customerId) {
    Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
    if(optionalCustomer.isPresent()){
      return optionalCustomer.get();
    }else{
      throw new RequestValidationException("Customer not found for this id: " + customerId);
    }
  }

  private Long getRequestSerialNo() {
    Optional<Returns> optionalReturns = returnsRepository.findFirstByOrderBySerialNoDesc();
    Date currentDate = new Date();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
    String currentYear = yearFormat.format(currentDate);
    if (optionalReturns.isPresent()) {
      String serialId = String.valueOf(optionalReturns.get().getSerialNo()).substring(0, 2);
      if (!serialId.equals(currentYear)) {
        return Long.parseLong(currentYear + "000001");
      } else {
        return optionalReturns.get().getSerialNo() + 1;
      }
    } else {
      return Long.parseLong(currentYear + "000001");
    }
  }

  @Override
  public AllProductRevenueResponse calculateRevenue() {
    DecimalFormat decimalFormat = new DecimalFormat("#.###");
    double total = 0.0;
    List<Product> products = (List<Product>) productRepository.findAll();
    List<SingleProductRevenueResponse> singleProductRevenueResponses = new ArrayList<>();
    for (Product product : products) {
      Double totalBuy;
      Double totalSell;
      double inStock;
      double averageBuyingPrice;
      double averageSellingPrice;
      Double totalBuyingPrice = 0.0;
      Double totalSellingPrice = 0.0;
      double revenue;
      List<ImportDetails> importDetailsList = importDetailsRepository.findByProduct(product);
      List<OrderDetails> orderDetailsList = orderDetailsRepository.findByProduct(product);
      Optional<Stock> optionalStock = stockRepository.findByProduct(product);
      if (optionalStock.isPresent() && optionalStock.get().getInStock() > 0.0) {
        totalBuy = optionalStock.get().getTotalBuy();
        totalSell = optionalStock.get().getTotalSell();
        for (ImportDetails importDetails : importDetailsList) {
          totalBuyingPrice = totalBuyingPrice + importDetails.getTotal();
        }
        for (OrderDetails orderDetails : orderDetailsList) {
          totalSellingPrice = totalSellingPrice + orderDetails.getTotalPrice();
        }
        averageBuyingPrice = totalBuy > 0 ? Double.parseDouble(decimalFormat.format(
                totalBuyingPrice / totalBuy)) : 0.0;
        averageSellingPrice = totalSell > 0 ? Double.parseDouble(decimalFormat.format(
                totalSellingPrice / totalSell)) : 0.0;
        revenue = Double.parseDouble(decimalFormat.format(averageBuyingPrice * optionalStock.get().getInStock()));
        total = total + revenue;
        singleProductRevenueResponses.add(SingleProductRevenueResponse.from(
                optionalStock.get().getProduct().getProductName(), totalBuy, totalSell, optionalStock.get().getInStock(),
                averageBuyingPrice, averageSellingPrice, totalBuyingPrice, totalSellingPrice, revenue));
      }
    }
    return AllProductRevenueResponse.from(singleProductRevenueResponses, total);
  }

  @Override
  public FinalResponse getAllProductAndArticleAndSale() {
    List<Product> products = (List<Product>) productRepository.findAll();
    List<AllProductAndArticleAndSaleResponse> allProductAndArticleAndSaleResponses = new ArrayList<>();
    int serialNo = 0;
    for (Product product : products) {
      serialNo += 1;
      List<Sale> sales = saleRepository.findByProduct(product);
      List<CompanyResponse> companyResponses = new ArrayList<>();
      for (Sale sale : sales) {
        companyResponses.add(CompanyResponse.from(sale));
      }
      allProductAndArticleAndSaleResponses.add(AllProductAndArticleAndSaleResponse.from(serialNo, product.getProductId(),
              product.getProductName(), companyResponses));
    }
    return FinalResponse.from(allProductAndArticleAndSaleResponses);
  }

  @Override
  public SuccessWastageResponseUsingSerial getAllWastage() {
    List<Wastage> wastageList = (List<Wastage>) wastageRepository.findAll();
    List<WastageResponseUsingSerial> wastageResponseUsingSerials = new ArrayList<>();
    HashMap<Long, Integer> commonIds = new HashMap<>();
    for (Wastage wastage : wastageList) {
      if(commonIds.isEmpty() || !commonIds.containsKey(wastage.getSerialNo())) {
        commonIds.put(wastage.getSerialNo(), 1);
        wastageResponseUsingSerials.add(WastageResponseUsingSerial.from(wastage));
      }
    }
    return SuccessWastageResponseUsingSerial.from(wastageResponseUsingSerials);
  }

  @Override
  public SuccessReturnResponseUsingSerial getAllReturn() {
    List<Returns> returnsList = (List<Returns>) returnsRepository.findAll();
    List<ReturnResponseUsingSerial> returnResponseUsingSerials = new ArrayList<>();
    HashMap<Long, Integer> commonIds = new HashMap<>();
    for (Returns returns : returnsList) {
      if(commonIds.isEmpty() || !commonIds.containsKey(returns.getSerialNo())) {
        commonIds.put(returns.getSerialNo(), 1);
        returnResponseUsingSerials.add(ReturnResponseUsingSerial.from(returns));
      }
    }
    return SuccessReturnResponseUsingSerial.from(returnResponseUsingSerials);
  }

  @Override
  public SuccessRequisitionResponseUsingSerial getAllRequisition() {
    List<Requisition> requisitionList = (List<Requisition>) requisitionRepository.findAll();
    List<RequisitionResponseUsingSerial> requisitionResponseUsingSerials = new ArrayList<>();
    HashMap<Long, Integer> commonIds = new HashMap<>();
    for (Requisition requisition : requisitionList) {
      if(commonIds.isEmpty() || !commonIds.containsKey(requisition.getSerialNo())) {
        commonIds.put(requisition.getSerialNo(), 1);
        requisitionResponseUsingSerials.add(RequisitionResponseUsingSerial.from(requisition));
      }
    }
    return SuccessRequisitionResponseUsingSerial.from(requisitionResponseUsingSerials);
  }

  private OrderMaster getOrderId(Long order) {
    Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findByOrderId(order);
    if (optionalOrderMaster.isPresent()) {
      return optionalOrderMaster.get();
    } else {
      throw new RequestValidationException("Order not exist");
    }
  }

  private ImportMaster getShipment(String shipment) {
    Optional<ImportMaster> optionalImportMaster = importMasterRepository.findByShipmentNo(shipment);
    if (optionalImportMaster.isPresent()) {
      return optionalImportMaster.get();
    } else {
      throw new RequestValidationException("Shipment not exist");
    }
  }
}