package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Sale;
import model.ShoppingCart;
import net.sf.jasperreports.engine.JRException;
import service.SaleManagerService;
import util.CommonUtil;
import util.PrintReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class CheckoutController implements Initializable {


    @FXML
    private TextField totalTF;

    @FXML
    private TextField receivedTF;

    @FXML
    private TextField balanceTF;




    @FXML
    private javafx.scene.control.Button closeButton;

    private ShoppingCart cart;

    public void checkout(ShoppingCart cart){

        this.cart = cart;
        totalTF.setText(String.valueOf(cart.getNetTotal()));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*|\\d+\\.\\d*")) {
                return change;
            }
            return null;
        };


        receivedTF.setTextFormatter(
                new TextFormatter<Double>(new DoubleStringConverter(), null, doubleFilter));
        totalTF.setDisable(true);



        receivedTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty() ){

                double balance =  (Double.parseDouble(newValue)) -cart.getNetTotal() ;

                balanceTF.setText(String.valueOf(balance));
            }
        });
    }

    @FXML
    public void print() throws JRException, FileNotFoundException {

        PrintReport printer =  new PrintReport();
        printer.Generate(cart);

    }

    @FXML
    public void finish() throws IOException, InterruptedException {
        SaleManagerService saleManagerService = new SaleManagerService();
        double payment = Double.parseDouble(receivedTF.getText().toString());
        Sale sale = new Sale(0, cart.getSubTotal(), cart.getDiscount(), cart.getNetTotal(), payment, CommonUtil.getCurrentTimeStamp());
        boolean stat = saleManagerService.addSale(cart,sale);



        if (stat){
            cancel();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Checkout Complete !", ButtonType.OK);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.initOwner(Main.getPrimaryStage());
            alert.showAndWait();



        }





    }

    @FXML
    public void cancel() throws IOException, InterruptedException {


        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();



    }


}
