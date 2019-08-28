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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Attendance;
import service.AttendanceManagerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {

    @FXML
    TableView<Attendance> attendanceTable;


    @FXML
    TableColumn idColumn;

    @FXML
    TableColumn EnameColumn;

    @FXML
    TableColumn monColumn;

    @FXML
    TableColumn yColumn;

    @FXML
    TableColumn AtColumn;

    @FXML
    TextField IDField;

    @FXML
    TextField NameField;

    @FXML
    TextField MonthField;

    @FXML
    TextField YearField;


    @FXML
    TextField AtField;


    private AttendanceManagerService attendanceManagerService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Employee ID"));
        EnameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        monColumn.setCellValueFactory(new PropertyValueFactory<>("Month"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));
        AtColumn.setCellValueFactory(new PropertyValueFactory<>("No of Attendance"));


        attendanceManagerService = new AttendanceManagerService();

        load();


    }

    private void load() {

        ObservableList<Attendance> attendance = attendanceManagerService.getAttendanceList();

        attendanceTable.setItems(attendance);
        if (attendance == null) {
            System.out.println("No attendance");
        }
    }


    @FXML
    public void add() {

        if (true) {

            int eid = Integer.parseInt(IDField.getCharacters().toString());
            String Name = NameField.getCharacters().toString();
            String month = MonthField.getCharacters().toString();
            int year   = Integer.parseInt(YearField.getCharacters().toString());
            int No = Integer.parseInt(AtField.getCharacters().toString());

           Attendance attendance  = new Attendance(eid, Name, month, year, No);


            attendanceManagerService.addAttendance(attendance);
           // System.out.println(attendance.getStatus());
            load();


        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }



    }

//
//    @FXML
//    public void update() {
//
//        if (valid()) {
//            if (type.getSelectionModel().isSelected(1)) {
//
//                Brand selected = brandTable.getSelectionModel().getSelectedItem();
//                Brand brand = new Brand(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
//                brandManagerService.updateBrand(brand);
//                loadBrands();
//
//            } else if (type.getSelectionModel().isSelected(2)) {
//
//                Category selected = categoryTable.getSelectionModel().getSelectedItem();
//                Category category = new Category(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
//                categoryManagerService.updateCategory(category);
//                loadCategories();
//            }
//
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


//    @FXML
//    public void delete() {
//
//        if (!attendanceTable.getSelectionModel().isEmpty()) {
//
//            Attendance attendance = attendanceTable.getSelectionModel().getSelectedItem();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                    "Are you sure You Want to delete this Category from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//                attendanceManagerService.removeAttendance(attendance.getEmpId());
//                load();
//            }

//        } else if (!brandTable.getSelectionModel().isEmpty()) {
//
//            Brand brand = brandTable.getSelectionModel().getSelectedItem();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                    "Are you sure You Want to delete this Brand from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();

//            if (alert.getResult() == ButtonType.YES) {
//                brandManagerService.removeBrand(brand.getId());
//                loadBrands();
//            }

        }
  //  }

//    private boolean valid() {
//
//        return !(name.getCharacters().length() == 0 || type.getSelectionModel().isSelected(0)
//                || status.getSelectionModel().isSelected(0));
//    }
//}
