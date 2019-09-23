package controller;



import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AttendanceController implements Initializable {

    @FXML
    TableView<Attendance> attendanceTable;


    @FXML
    TableColumn<Attendance,Integer> idColumn;

    @FXML
    TableColumn EnameColumn;

    @FXML
    TableColumn monColumn;

    @FXML
    TableColumn yColumn;


    @FXML
    TableColumn atColumn;

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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        EnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        monColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        yColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("year"));
        atColumn.setCellValueFactory(new PropertyValueFactory<>("noOfAttendance"));
        // testColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("test"));

        attendanceManagerService = new AttendanceManagerService();

        loadAttendance();

        attendanceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Attendance attendance = attendanceTable.getSelectionModel().getSelectedItem();


                IDField.setText(String.valueOf(attendance.getEmployeeId()));
                NameField.setText(attendance.getName());
                MonthField.setText(attendance.getMonth());
                YearField.setText(String.valueOf(attendance.getYear()));
                AtField.setText(String.valueOf(attendance.getNoOfAttendance()));

            }
        });


    }

    private void loadAttendance() {

        ObservableList<Attendance> attendance = attendanceManagerService.getAttendanceList();

//          ObservableList<Attendance> attendance = FXCollections.observableArrayList();
//         Attendance atn = new Attendance(111, "Nu","March",3,5,12);
//         attendance.add(atn);

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
            int year = Integer.parseInt(YearField.getCharacters().toString());
            int No = Integer.parseInt(AtField.getCharacters().toString());

            Attendance attendance = new Attendance(eid, Name, month, year, No);


            attendanceManagerService.addAttendance(attendance);
            //System.out.println(attendance.getStatus());
            loadAttendance();


        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }


    }


    @FXML
    public void update() {

        if (valid()) {
            //if (type.getSelectionModel().isSelected( 1)) {

            int eid = Integer.parseInt(IDField.getCharacters().toString());
            String Name = NameField.getCharacters().toString();
            String month = MonthField.getCharacters().toString();
            int year = Integer.parseInt(YearField.getCharacters().toString());
            int No = Integer.parseInt(AtField.getCharacters().toString());


            Attendance selected = attendanceTable.getSelectionModel().getSelectedItem();
            Attendance attendance = new Attendance(selected.getEmployeeId(), Name, month, year, No);
            attendanceManagerService.updateAttendance(attendance);
            loadAttendance();
        }
        //  } else if (type.getSelectionModel().isSelected(2)) {

        //     Category selected = categoryTable.getSelectionModel().getSelectedItem();
        //      Category category = new Category(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
        //      categoryManagerService.updateCategory(category);
        //      loadCategories();


        else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }
        //name.clear();
        //status.getSelectionModel().selectFirst();

    }


    @FXML
    public void delete() {

        if (!attendanceTable.getSelectionModel().isEmpty()) {

            Attendance attendance = attendanceTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure You Want to delete this Attendance from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                attendanceManagerService.removeAttendance(attendance.getEmployeeId());
                loadAttendance();
            }

        }
    }

    @FXML
    public void openLeave() throws IOException {

        SceneManager.manage().opeLeaveScene();

    }

    @FXML
    public void back() throws IOException {

        SceneManager.manage().openHomeScene();

    }


    private boolean valid() {

        return !(IDField.getCharacters().length() == 0
                || NameField.getCharacters().length() == 0
                || MonthField.getCharacters().length() == 0
                || YearField.getCharacters().length() == 0
                || AtField.getCharacters().length() == 0

        );

    }
}
