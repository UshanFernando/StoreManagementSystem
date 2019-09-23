package util;

public class Constants {

    /**
     * Constant for file path of config.properties
     */
    public static final String PROPERTY_FILE = "config.properties";

    /**
     * Constant for url key of MySQL database in config.properties
     */
    public static final String URL = "url";

    /**
     * Constant for user name key of MySQL database in config.properties
     */
    public static final String USERNAME = "username";

    /**
     * Constant for password key of MySQL database in config.properties
     */
    public static final String PASSWORD = "password";

    /**
     * Constant for driver name key of MySQL database in config.properties
     */
    public static final String DRIVER_NAME = "driverName";

    /**
     * Constant for query id of get all products in UserQuery.xml
     */


    public static final String TAG_NAME = "query";

    public static final String ATTRIB_ID = "id";

    public static final String COMMA = ",";

    public static final int COLUMN_INDEX_ONE = 1;

    public static final int COLUMN_INDEX_TWO = 2;

    public static final int COLUMN_INDEX_THREE = 3;

    public static final int COLUMN_INDEX_FOUR = 4;

    public static final int COLUMN_INDEX_FIVE = 5;

    public static final int COLUMN_INDEX_SIX = 6;

    public static final int COLUMN_INDEX_SEVEN = 7;

    public static final int COLUMN_INDEX_EIGHT = 8;

       /*

    Constants for Product Queries

    */

    public static final String QUERY_ID_GET_PRODUCTS = "get_products";

    public static final String QUERY_ID_UPDATE_PRODUCT = "update_product";

    public static final String QUERY_ID_ADD_PRODUCT = "add_product";

    public static final String QUERY_ID_REMOVE_PRODUCT = "delete_product";

    public static final String QUERY_ID_GET_PRODUCT_BY_ID = "product_by_id";

    public static final String QUERY_ID_CREATE_PRODUCT_TABLE = "create_product_table";

    public static final String QUERY_ID_DROP_PRODUCTS_TABLE = "drop_table_products";

    /*

    Constants for Brand Queries

    */


    public static final String QUERY_ID_CREATE_BRAND_TABLE = "create_brands_table";

    public static final String QUERY_ID_DROP_BRAND_TABLE = "drop_table_brands";

    public static final String QUERY_ID_UPDATE_BRAND = "update_brand";

    public static final String QUERY_ID_ADD_BRAND = "add_brand";

    public static final String QUERY_ID_REMOVE_BRAND = "delete_brand";

    public static final String QUERY_ID_GET_BRAND_BY_ID = "brand_by_id";

    public static final String QUERY_ID_GET_BRANDS = "get_brands";


     /*

    Constants for Category Queries

    */

    public static final String QUERY_ID_CREATE_CATEGORIES_TABLE = "create_categories_table";

    public static final String QUERY_ID_DROP_CATEGORIES_TABLE = "drop_table_categories";

    public static final String QUERY_ID_UPDATE_CATEGORY = "update_category";

    public static final String QUERY_ID_ADD_CATEGORY = "add_category";

    public static final String QUERY_ID_REMOVE_CATEGORY = "delete_category";

    public static final String QUERY_ID_GET_CATEGORY_BY_ID = "category_by_id";

    public static final String QUERY_ID_GET_CATEGORIES = "get_categories";

    /**
     * Constants for Suppliers
     */

    public static final String QUERY_ID_CREATE_SUPPLIER_TABLE = "create_supplier_table";

    public static final String QUERY_ID_GET_SUPPLIERS = "get_suppliers";

    public static final String QUERY_ID_GET_SUPPLIER_BY_ID = "supplier_by_id";

    public static final String QUERY_ID_UPDATE_SUPPLIER = "update_supplier";

    public static final String QUERY_ID_ADD_SUPPLIER = "add_supplier";

    public static final String QUERY_ID_REMOVE_SUPPLIER = "delete_supplier";

    public static final String QUERY_ID_GET_LAST_SUPPLIER_ID = "last_id_of_supplier";

    /**
     * Constants for Supplier Contacts
     */

    public static final String QUERY_ID_CREATE_SUPPLIER_CONTACT_TABLE = "create_supplier_contact_table";

    public static final String QUERY_ID_GET_SUPPLIER_CONTACTS_BY_ID = "get_supplier_contacts_by_id";

    public static final String QUERY_ID_ADD_SUPPLIER_CONTACT = "add_supplier_contact";

    public static final String QUERY_ID_UPDATE_SUPPLIER_CONTACT = "update_supplier_contact";

    public static final String QUERY_ID_REMOVE_SUPPLIER_CONTACT = "delete_supplier_contact";

    /**
     * Constant for Supplier Mail
     */

    public static final String QUERY_ID_CREATE_SUPPLIER_EMAIL_TABLE = "create_supplier_email_table";

    public static final String QUERY_ID_GET_SUPPLIER_EMAIL_BY_ID = "get_supplier_emails_by_id";

    public static final String QUERY_ID_ADD_SUPPLIER_EMAIL ="add_supplier_email";

    public static final String QUERY_ID_UPDATE_SUPPLIER_EMAIL = "update_supplier_email";

    public static final String QUERY_ID_REMOVE_SUPPLIER_EMAIL = "delete_supplier_email";

    /**
     * Constant for Orders
     */

    public static final String QUERY_ID_CREATE_ORDERS_TABLE = "create_order_table";

    public static final String QUERY_ID_GET_ALL_ORDERS = "get_orders";

    public static final String QUERY_ID_GET_ORDER_BY_ID = "get_orders_by_id";

    public static final String QUERY_ID_UPDATE_ORDER = "update_order_by_id";

    public static final String QUERY_ID_ADD_ORDER = "add_orders";

    public static final String QUERY_ID_DELETE_ORDER = "delete_orders_by_id";


         /*

    Constants for finance Queries

    */

    public static final String QUERY_ID_CREATE_FINANCE_TABLE = "create_finance_table";

    public static final String QUERY_ID_DROP_FINANCE_TABLE = "drop_table_finance";

    public static final String QUERY_ID_FINANCE_CATEGORY = "update_finance";

    public static final String QUERY_ID_ADD_FINANCE = "add_finance";

    public static final String QUERY_ID_REMOVE_FINANCE = "delete_finance";

    public static final String QUERY_ID_GET_FINANCE_BY_ID = "finance_by_id";

    public static final String QUERY_ID_GET_FINANCE = "get_finance";


         /*

    Constants for SALES Queries

    */

    public static final String QUERY_ID_CREATE_SALES_TABLE = "create_sales_table";

    public static final String QUERY_ID_DROP_SALES_TABLE = "drop_table_finance";

    public static final String QUERY_ID_ADD_SALES = "add_sale";

    public static final String QUERY_ID_REMOVE_SALES = "delete_sale";

    public static final String QUERY_ID_GET_SALES = "get_sales";

           /*

    Constants for requests Queries

    */

    public static final String QUERY_ID_CREATE_REQUESTS_TABLE = "create_requests_table";

    public static final String QUERY_ID_DROP_REQUESTS_TABLE = "drop_table_requests";

    public static final String QUERY_ID_REQUESTS_CATEGORY = "update_requests";

    public static final String QUERY_ID_ADD_REQUESTS = "add_requests";

    public static final String QUERY_ID_REMOVE_REQUESTS = "delete_requests";

    public static final String QUERY_ID_GET_REQUESTS_BY_ID = "requests_by_id";

    public static final String QUERY_ID_GET_REQUESTS = "get_requests";

    /*
    constants for Salary Queries
    */

    public static final String QUERY_ID_CREATE_SALARY_TABLE = "create_salary_table";

    public static final String QUERY_ID_DROP_SALARY_TABLE = "drop_table_salary";

    public static final String QUERY_ID_UPDATE_SALARY = "update_salary";

    public static final String QUERY_ID_ADD_SALARY = "add_salary";

    public static final String QUERY_ID_REMOVE_SALARY = "delete_salary";

    public static final String QUERY_ID_GET_SALARY_BY_ID = "salary_by_id";

    public static final String QUERY_ID_GET_SALARY = "get_salary";


    /*

    Constants for Employee Queries

    */

    public static final String QUERY_ID_CREATE_EMPLOYEE_TABLE = "create_ employee_table";

    public static final String QUERY_ID_DROP_EMPLOYEE_TABLE = "drop_table_employee";

    public static final String QUERY_ID_UPDATE_EMPLOYEE = "update_employee";

    public static final String QUERY_ID_ADD_EMPLOYEE= "add_employee";

    public static final String QUERY_ID_REMOVE_EMPLOYEE = "delete_employee";

    public static final String QUERY_ID_GET_EMPLOYEE_BY_ID = "employee_by_id";

    public static final String QUERY_ID_GET_EMPLOYEE = "get_employee";

     /*

    Constants for attendance Queries

    */


    public static final String QUERY_ID_CREATE_ATTENDANCE_TABLE = "create_attendance_table";

    public static final String QUERY_ID_ADD_ATTENDANCE = "add_attendance";

    public static final String QUERY_ID_REMOVE_ATTENDANCE = "delete_attendance";

    public static final String QUERY_ID_GET_ATTENDANCE = "get_attendance";

    public static final String QUERY_ID_GET_ATTENDANCE_BY_ID = "attendance_by_id";

/*
Constants for Register Queries

        */

public static final String QUERY_ID_CREATE_REGISTER_TABLE = "create_ register_table";

public static final String QUERY_ID_DROP_REGISTER_TABLE = "drop_table_register";

//public static final String QUERY_ID_UPDATE_REGISTER = "update_register";

public static final String QUERY_ID_ADD_REGISTER= "add_register";

public static final String QUERY_ID_REMOVE_REGISTER = "delete_register";

public static final String QUERY_ID_GET_REGISTER_BY_ID = "register_by_id";

public static final String QUERY_ID_GET_REGISTER = "get_register";

/*
Constants for Login Queries

        */
public static final String QUERY_ID_MATCH_EMPLOYEE = "match_employee";



        /*

    Constants for notifications Queries

    */


    public static final String QUERY_ID_CREATE_NOTIFICATION_TABLE = "create_notifications_table";

    public static final String QUERY_ID_UPDATE_NOTIFICATION = "update_notification";

    public static final String QUERY_ID_GET_UNREAD_NOTIFICATION = "get_notifications_unread";

    public static final String QUERY_ID_GET_ALL_NOTIFICATION = "get_notifications";

    public static final String QUERY_ID_DELETE_NOTIFICATION = "delete_notification";

}

