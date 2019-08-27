package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import model.Brand;
import model.Employee;
//import model.Category;
//import service.BrandManagerService;
//import service.CategoryManagerService;
import service.EmployeeManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    TableView<Employee> employeeTable;

    @FXML
    TableColumn empIdColumn;

    @FXML
    TableColumn nameColumn;

    @FXML
    TableColumn departmentColumn;

    @FXML
    TableColumn levelColumn;

    @FXML
    TableColumn contactColumn;

    @FXML
    TableColumn genderColumn;

    @FXML
    TableColumn AddressColumn;

    @FXML
    TableColumn recdateColumn;


    @FXML
    TextField eempId;


    @FXML
    TextField ename;

    @FXML
    TextField edepartment;

    @FXML
    TextField elevel;

    @FXML
    TextField econtact;

    @FXML
    TextField egender;

    @FXML
    TextField eAddress;

    @FXML
    TextField erecdate;












    private EmployeeManagerService employeeManagerService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("empId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
//        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        recdateColumn.setCellValueFactory(new PropertyValueFactory<>("recdate"));


        employeeManagerService = new EmployeeManagerService();


        loadEmployees();


    }

    private void loadEmployees() {

        ObservableList<Employee> employees = employeeManagerService.getEmployeesList();

        if (employees == null) {
            System.out.println("No Employees");
        } else employeeTable.setItems(employees);
    }


/*
    public void openInventory(ActionEvent event) throws IOException {

        Parent invenetoryViewParent = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(invenetoryViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("Inventory Management");
        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
        window.getIcons().add(icon);
    }*/

    @FXML
    public void add() {

        if (true) {

            int empId = Integer.parseInt(eempId.getCharacters().toString());
            String name = ename.getCharacters().toString();
            String department = edepartment.getCharacters().toString();
            int level = Integer.parseInt(elevel.getCharacters().toString());
            int contact = Integer.parseInt(econtact.getCharacters().toString());
            String gender = egender.getCharacters().toString();
            String Address = eAddress.getCharacters().toString();
            String recdate = erecdate.getCharacters().toString();

            Employee employee = new Employee(empId, name , department, level, contact, gender, Address, recdate);

            employeeManagerService.addEmployee(employee);
            System.out.println(employee.getName());
            loadEmployees();

//            if (type.getSelectionModel().isSelected(1)) {
//
//                Employee employee = new Employee(name.getCharacters().toString(), status.getValue().toString());
//                employeeManagerService.addEmployee(employee);
//                loadEmployees();
//            }
//
          /* else if (type.getSelectionModel().isSelected(2)) {

                Category category = new Category(name.getCharacters().toString(), status.getValue().toString());
                categoryManagerService.addCategory(category);
                loadCategories();
            }
*/
        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }


    }


//    @FXML
//    public void update() {
//
//        if (valid()) {
//            if (type.getSelectionModel().isSelected(1)) {
//
//                Employee selected = employeeTable.getSelectionModel().getSelectedItem();
//                Employee employee = new Employee(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
//                employeeManagerService.updateEmployee(employee);
//                loadEmployees();
//
//            } /*else if (type.getSelectionModel().isSelected(2)) {
//
//                Category selected = categoryTable.getSelectionModel().getSelectedItem();
//                Category category = new Category(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
//                categoryManagerService.updateCategory(category);
//                loadCategories();
//            }
//*/
//        }else {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR,
//                    "Please Provide Valid Information !", ButtonType.OK);
//
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();
//
//
//        }
//
//        name.clear();
//        status.getSelectionModel().selectFirst();
//
//    }


    @FXML
    public void delete() {


    if (!employeeTable.getSelectionModel().isEmpty()) {

            Employee employee = employeeTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure You Want to delete this Employee from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                employeeManagerService.removeEmployee(employee.getEmpId());
                loadEmployees();
            }

        }
    }

   /* private boolean valid() {

        return !(name.getCharacters().length() == 0 || type.getSelectionModel().isSelected(0)
                || status.getSelectionModel().isSelected(0));
    }*/
}
