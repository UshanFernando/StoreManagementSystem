module StoreManagementSystem {
    requires javafx.controls;
    requires  javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;
    exports  model;

    opens model to jakvafx.graphics;

    opens controller;

}