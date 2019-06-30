/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaretwo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Megatron
 */
public class TimeHandler {

    private static Driver dbDriver;
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static void main(String[] args) {
        dbDriver = new Driver();
    }

    private static void populateAllAppointments() throws SQLException {
        String allAppsString = "select * from appointment where customerId = " + dbDriver.getCarryCustomer().getCustomerId() + ";";

        System.out.println(allAppsString);
        ResultSet result = dbDriver.queryAndReturn(allAppsString);

        while (result.next()) {

            Appointment entry;
            entry = new Appointment(result.getInt("appointmentId"),
                    result.getInt("customerId"),
                    result.getString("title"),
                    result.getString("appDesc"),
                    result.getString("location"),
                    result.getString("Contact"),
                    result.getString("url"),
                    result.getString("start"),
                    result.getString("end"),
                    result.getString("createDate"),
                    result.getString("createdBy"),
                    result.getString("lastUpdate"),
                    result.getString("lastUpdateBy")
            );
            allAppointments.add(entry);
        }
    }

    public static boolean checkConflictsAppointmentNew(String start, String end) throws SQLException, ParseException {
        populateAllAppointments();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date startDateTime = sdf.parse(start);
        Date endDateTime = sdf.parse(end);

        if (allAppointments.size() > 0) {
            for (int i = 0; i < allAppointments.size(); i++) {
                Date cStartDateTime = sdf.parse(allAppointments.get(i).getStart());
                Date cEndDateTime = sdf.parse(allAppointments.get(i).getEnd());

                if (startDateTime.equals(cStartDateTime) && endDateTime.equals(cEndDateTime)) {
                    return true;
                }
                if(startDateTime.after(cStartDateTime) &&  startDateTime.before(cEndDateTime)){
                    return true;
                }
                
                if(startDateTime.before(cEndDateTime) &&  startDateTime.after(cStartDateTime)){
                    return true;
                }

                if (startDateTime.after(cStartDateTime) && startDateTime.before(cEndDateTime)) {
                    return true;
                }

                if (endDateTime.after(cStartDateTime) && endDateTime.before(cEndDateTime)) {
                    return true;
                }
            }

        } else {
            return false;
        }

        return false;

    }
    
    public static boolean checkConflictsAppointmentModify(String start, String end, String appId) throws SQLException, ParseException {
        populateAllAppointments();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date startDateTime = sdf.parse(start);
        Date endDateTime = sdf.parse(end);

        if (allAppointments.size() > 0) {
            for (int i = 0; i < allAppointments.size(); i++) {
                Date cStartDateTime = sdf.parse(allAppointments.get(i).getStart());
                Date cEndDateTime = sdf.parse(allAppointments.get(i).getEnd());

                if (startDateTime.equals(cStartDateTime) && endDateTime.equals(cEndDateTime) && appId != allAppointments.get(i).getAppId().toString() ) {
                    return true;
                }
                if(startDateTime.after(cStartDateTime) &&  startDateTime.before(cEndDateTime) && appId != allAppointments.get(i).getAppId().toString()){
                    return true;
                }
                
                if(startDateTime.before(cEndDateTime) &&  startDateTime.after(cStartDateTime) && appId != allAppointments.get(i).getAppId().toString()){
                    return true;
                }

                if (startDateTime.after(cStartDateTime) && startDateTime.before(cEndDateTime) && appId != allAppointments.get(i).getAppId().toString()) {
                    return true;
                }

                if (endDateTime.after(cStartDateTime) && endDateTime.before(cEndDateTime) && appId != allAppointments.get(i).getAppId().toString()) {
                    return true;
                }
            }

        } else {
            return false;
        }

        return false;

    }

}
