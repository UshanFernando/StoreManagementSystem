package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ShoppingCart;
import net.sf.jasperreports.engine.JRException;
import util.PrintReport;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

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
        totalTF.setText(String.valueOf(cart.getSubTotal()));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @FXML
    public void print() throws JRException, FileNotFoundException {

        PrintReport printer =  new PrintReport();
        printer.Generate(cart);

    }

    @FXML
    public void finish(){
//        SaleManagerService saleManagerService = new SaleManagerService();
//        Sale sale = new Sale(0, shoppingCart.getSubTotal(), shoppingCart.getDiscount(), shoppingCart.getNetTotal(), 0, CommonUtil.getCurrentTimeStamp());
//        boolean stat= saleManagerService.addSale(sale);
//        if (stat){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION,
//                    "Checkout Complete !", ButtonType.OK);
//            alert.initStyle(StageStyle.TRANSPARENT);
//            alert.initOwner(Main.getPrimaryStage());
//            alert.showAndWait();
//            reset();
//        }



    }

    @FXML
    public void cancel(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


}
