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
import service.LeaveManagerService;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import model.Leave;

public class LeaveController implements Initializable {


    @FXML
    TextField namefield;

    @FXML
    TextField idfield;

    @FXML
    TextField leavefield;

    @FXML
    TextField emailfield;


    @FXML
    TextField dfield;

    @FXML
    Button submit;


    private LeaveManagerService leaveManagerService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       /* idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        EnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        monColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        yColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("year"));
        atColumn.setCellValueFactory(new PropertyValueFactory<>("noOfAttendance"));
        testColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("test"));

        attendanceManagerService = new AttendanceManagerService();

        loadAttendance();



   */

        leaveManagerService = new LeaveManagerService();
    }
//    private void loadLeaves() {
//
//        ObservableList<Leave> employees = leaveManagerService.getLeavesList();
//
//        if (leave == null) {
//            System.out.println("No Leave");
//        } else leaverecord.setItems(employees);
//    }

    @FXML
    public void add() {

        if (true) {


            String fullname = namefield.getCharacters().toString();
            int eid = Integer.parseInt(idfield.getCharacters().toString());
            String ltype = leavefield.getCharacters().toString();
            String email = emailfield.getCharacters().toString();
            String date = dfield.getCharacters().toString();


            Leave leave = new Leave(fullname, eid, ltype, email, date);


            leaveManagerService.addLeave(leave);
            //System.out.println(attendance.getStatus());
            // loadAttendance();


        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }


    }
}
