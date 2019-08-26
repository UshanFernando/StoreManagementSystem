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

    *Constants for Suppliers

    */

    public static final String QUERY_ID_CREATE_SUPPLIER_TABLE = "create_supplier_table";

    public static final String QUERY_ID_GET_SUPPLIERS = "get_suppliers";

    public static final String QUERY_ID_GET_SUPPLIER_BY_ID = "supplier_by_id";

    /**
    * Constants for Supplier Contacts
    * */

    public static final String QUERY_ID_CREATE_SUPPLIER_CONTACT_TABLE = "create_supplier_contact_table";

    public static final String QUERY_ID_GET_SUPPLIER_CONTACTS_BY_ID = "get_supplier_contacts_by_id";

    /**
     * Constant for Supplier Mail
     */

    public static final String QUERY_ID_CREATE_SUPPLIER_EMAIL_TABLE = "create_supplier_email_table";

    public static final String QUERY_ID_GET_SUPPLIER_EMAIL_BY_ID = "get_supplier_emails_by_id";

    /**
     * Constant for Orders
     *
     * */

    public static final  String QUERY_ID_CREATE_ORDERS_TABLE = "create_order_table";

    public static final  String QUERY_ID_GET_ALL_ORDERS = "get_orders";

    public static final  String QUERY_ID_GET_ORDER_BY_ID = "get_orders_by_id";


}
