package service;


import javafx.collections.ObservableList;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;
import model.Attendance;



public interface AttendanceManagerServiceInterface {

    public ObservableList<Attendance> getAttendanceList();

    public Attendance getAttendanceById(int pid);

    public void updateAttendance(Attendance user);

    public boolean addAttendance(Attendance user);

    public void removeAttendance(int pid);
}

