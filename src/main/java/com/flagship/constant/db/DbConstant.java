package com.flagship.constant.db;

public class DbConstant {
  private DbConstant() {

  }

  public static class DbCommon {
    public static final String CREATED_BY = "created_by";
    public static final String CREATED_ON = "created_on";
    public static final String LAST_UPDATED_BY = "last_updated_by";
    public static final String LAST_UPDATED_ON = "last_updated_on";

    public static final String ID = "id";

    DbCommon() {
    }
  }

  public static class DbUser extends DbCommon {
    public static final String TABLE_NAME = "users";
    public static final String NAME = "name";

    public static final String EMAIL = "email";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String PASSWORD = "password";
    public static final String GENDER = "gender";
  }

  public static class DbStock extends DbCommon {
    public static final String TABLE_NAME = "stock";
    public static final String PRODUCT = "product";
    public static final String UOM = "uom";
    public static final String TOTAL_BUY = "total";
    public static final String TOTAL_SELL = "sell";
    public static final String IN_STOCK = "inStock";
  }

  public static class DbProduct extends DbCommon {
    public static final String TABLE_NAME = "product";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
  }

  public static class DbCountry extends DbCommon {
    public static final String TABLE_NAME = "country";
    public static final String COUNTRY_ID = "country_id";
    public static final String COUNTRY_NAME = "country_name";
  }

  public static class DbBrand extends DbCommon {
    public static final String TABLE_NAME = "brand";
    public static final String BRAND_ID = "brand_id";
    public static final String BRAND_NAME = "brand_name";
  }

  public static class DbCategories extends DbCommon {
    public static final String TABLE_NAME = "categories";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";
  }

  public static class DbImportDetails extends DbCommon {
    public static final String TABLE_NAME = "import_details";
    public static final String SHIPMENT_NO = "shipment_no";
    public static final String PRODUCT = "product";
    public static final String CATEGORY = "category";
    public static final String BRAND = "brand";
    public static final String PRODUCTION = "production";
    public static final String COUNTRY = "country";
    public static final String WAREHOUSE = "warehouse";
    public static final String EXPIRE = "expire";
    public static final String CARTOON = "cartoon";
    public static final String UNIT_CARTOON = "unit_cartoon";
    public static final String PIECE = "piece";
    public static final String UNIT_PIECE = "unit_piece";
    public static final String KG_LT = "kg_lt";
    public static final String UOM = "uom";
    public static final String UNIT_PRICE = "price";
    public static final String TOTAL_PRICE = "total";
  }

  public static class DbImportMaster extends DbCommon {
    public static final String TABLE_NAME = "import_master";
    public static final String SHIPMENT_NO = "shipment_no";
    public static final String COUNTRY = "country";
    public static final String DATE = "date";
  }

  public static class DbCustomer extends DbCommon {
    public static final String TABLE_NAME = "customer";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_NAME = "name";
    public static final String COMPANY = "company";
    public static final String PHONE_NUMBER = "contact";
    public static final String CUSTOMER_TYPE = "type";
    public static final String SUPPLIER = "supplier";
    public static final String ADDRESS = "address";
    public static final String BIN_NO = "bin_no";
    public static final String CREDIT_TERM = "credit";
  }

  public static class DbSupplier extends DbCommon {
    public static final String TABLE_NAME = "supplier";
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String SUPPLIER_NAME = "supplier_name";
  }

  public static class DbBranch extends DbCommon {
    public static final String TABLE_NAME = "branch";
    public static final String SUPPLIER = "supplier";
    public static final String BRANCH_NAME = "name";
    public static final String BRANCH_ADDRESS = "address";
  }

  public static class DbSale extends DbCommon {
    public static final String TABLE_NAME = "sale";
    public static final String SUPPLIER = "supplier";
    public static final String PRODUCT = "product";
    public static final String ARTICLE = "article";
    public static final String SALE_CODE = "sale_code";
  }

  public static class DbSalesPerson extends DbCommon {
    public static final String TABLE_NAME = "sales_person";
    public static final String SALES_PERSON_ID = "sales_person_id";
    public static final String SALES_PERSON_NAME = "sales_person_name";
    public static final String AREA = "area";
    public static final String PHONE_NUMBER = "phone_number";
  }

  public static class DbOrderMaster extends DbCommon {
    public static final String TABLE_NAME = "order_master";
    public static final String ORDER_ID = "order_id";
    public static final String CUSTOMER = "customer";
    public static final String COMPANY_NAME = "company_name";
    public static final String SUPPLIER = "supplier";
    public static final String BRANCH = "branch";
    public static final String CUSTOMER_TYPE = "customer_type";
    public static final String ORDER_DATE = "order_date";
    public static final String DELIVERY_DATE = "delivery_date";
    public static final String CREDIT_TERM = "credit_term";
    public static final String CHALLAN = "challan";
    public static final String ADDRESS = "address";
    public static final String ORDER_BY = "order_by";
  }

  public static class DbOrderDetails extends DbCommon {
    public static final String TABLE_NAME = "order_details";
    public static final String ORDER = "orders";
    public static final String PRODUCT = "product";
    public static final String SHIPMENT = "shipment";
    public static final String SALE = "sale";
    public static final String VAT = "vat";
    public static final String UOM = "uom";
    public static final String QUANTITY = "quantity";
    public static final String DISCOUNT = "discount";
    public static final String REMARKS = "remarks";
    public static final String PRICE = "price";
    public static final String TOTAL_PRICE = "total";
    public static final String ORDER_STATUS = "status";
    public static final String WAREHOUSE = "warehouse";
  }
  public static class DbOrderBills extends DbCommon {
    public static final String TABLE_NAME = "order_bills";
    public static final String ORDER = "orders";
    public static final String SALES = "sales";
    public static final String DUE = "due";
    public static final String PAYMENT = "payment";
  }

  public static class DbWastage extends DbCommon {
    public static final String TABLE_NAME = "wastage";
    public static final String PRODUCT = "product";
    public static final String SHIPMENT = "shipment";
    public static final String CARTOON = "cartoon";
    public static final String PIECE = "piece";
    public static final String KG_LT = "kg_lt";
    public static final String CAUSE = "cause";
    public static final String WAREHOUSE = "warehouse";
    public static final String SERIAL_NO = "serial_no";
  }

  public static class DbReturn extends DbCommon {
    public static final String TABLE_NAME = "return_order";
    public static final String PRODUCT = "product";
    public static final String CARTOON = "cartoon";
    public static final String PIECE = "piece";
    public static final String KG_LT = "kg_lt";
    public static final String ORDER = "orders";
    public static final String DELIVERY_MAN = "delivery_man";
    public static final String CAUSE = "cause";
    public static final String SERIAL_NO = "serial_no";
    public static final String WAREHOUSE = "warehouse";
    public static final String CUSTOMER = "customer";
    public static final String BRANCH = "branch";
  }

  public static class DbRequisition extends DbCommon {
    public static final String TABLE_NAME = "requisition";
    public static final String PRODUCT = "product";
    public static final String PIECE = "piece";
    public static final String QUANTITY = "quantity";
    public static final String DELIVERY_MAN = "delivery_man";
    public static final String SERIAL_NO = "serial_no";
    public static final String WAREHOUSE = "warehouse";
  }
}

