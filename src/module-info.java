module StoreManagementSystem {
    requires javafx.controls;
    requires  javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    exports  model;

    opens model to javafx.graphics;

    opens controller;

}