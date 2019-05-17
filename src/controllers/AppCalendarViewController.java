/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import softwaretwo.Appointment;
import softwaretwo.Customer;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Megatron
 */
public class AppCalendarViewController implements Initializable {

    
    @FXML AnchorPane root;
    @FXML TableView<Appointment> allAppsTable;
    @FXML TableColumn <Appointment, String> nameCol;
    @FXML TableColumn <Appointment, String> titleCol;
    @FXML TableColumn <Appointment, String> descCol;
    @FXML TableColumn <Appointment, String> locationCol;
    @FXML TableColumn <Appointment, String> contactcol;
    
    @FXML DatePicker startDate;
    @FXML DatePicker endDate;
    @FXML Button makeAppButton;
    @FXML Button returnToMain;
    
    
    private Driver dbDriver;
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private char q =  '"';

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    
    @FXML
    private void showApps() throws SQLException{
        System.out.println("Show Appointments Button Clicked with dates" + startDate.getValue() + " "+ endDate.getValue());
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        String appQuery;
        if(start.compareTo(end) <0){
            System.out.println("Dates Validate");
            appQuery = "select customerName, appointmentId,  title, appDesc, location, contact, url, start, end, appointment.createDate, appointment.createdBy, appointment.lastUpdate, appointment.lastUpdateBy "
                    + "from customer join appointment on appointment.customerId = customer.customerId "
                    + "where appointment.start >" + q+ start+ q+ " and appointment.end < "+ q+end+ q+";";
            System.out.println(appQuery);
            populateAppTable(dbDriver.queryAndReturn(appQuery));
        }

    }
    private void populateAppTable(ResultSet result) throws SQLException{
        
        while(result.next()){
            Appointment entry = new Appointment(
                    result.getInt("appointmentId"), 
                    result.getString("customerName"), 
                    result.getString("title"), 
                    result.getString("appDesc"), 
                    result.getString("location"), 
                    result.getString("contact"), 
                    result.getString("url"), 
                    result.getString("start"), 
                    result.getString("end"), 
                    result.getString("createdBy"), 
                    result.getString("createDate"), 
                    result.getString("lastUpdate"),
                    result.getString("lastUpdateBy")
                );
            
            allAppointments.add(entry);
        }
        if(allAppointments.size() > 0){
            populateCols(allAppointments);
        }else{
            System.out.println("no appointments to show");
        }
    }
    
    private void populateCols(ObservableList<Appointment> apps){
        nameCol.setCellValueFactory(new PropertyValueFactory("customerName"));
        titleCol.setCellValueFactory(new PropertyValueFactory("title"));
        descCol.setCellValueFactory(new PropertyValueFactory("appDesc"));
        locationCol.setCellValueFactory(new PropertyValueFactory("location"));
        contactcol.setCellValueFactory(new PropertyValueFactory("contact"));
        allAppsTable.setItems(apps);
        allAppsTable.refresh();
    }
    
    @FXML
    private void returnMain(){
        try{
                AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/mainMenuView.fxml"));
                root.getChildren().setAll(pane);
             }catch(Exception ex){
                System.out.print(ex);
            }
    }
}
