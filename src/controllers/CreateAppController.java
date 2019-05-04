/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import softwaretwo.Appointment;
import softwaretwo.Customer;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class CreateAppController implements Initializable {

   @FXML AnchorPane root;
   
   @FXML TableView existingAppTable;
   @FXML TableColumn locationCol;
   @FXML TableColumn startCol;
   @FXML TableColumn endCol;
   @FXML TableColumn descriptionCol;
   @FXML TableColumn lastUpdateCol;
   
   @FXML TextField titleField;
   @FXML TextField descField;
   @FXML TextField locationField;
   @FXML TextField contactField;
   @FXML TextField urlField;
   
   @FXML DatePicker startDate;
   @FXML DatePicker endDate;
   
   @FXML Slider startTimeSlider;
   @FXML Slider endTimeSlider;
   
   @FXML Text startTimeVal;
   @FXML Text endTimeVal;
   
   
   
   @FXML Button createAppButton;
   @FXML Button cancelButton;
   
   private Driver dbDriver;
   
    private char q = '"';
    private char semi = ';';
    private char com = ',';
   
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private Appointment app;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTimes();
        
    }
    
    private String formatDate(String timeVal, boolean isStart){
        //Get the Dates and format correctly
        //yyyy-mm-dd hh:mm:ss
        
        String appTime; 
        if(isStart){
            appTime = startDate.valueProperty().getValue().toString() + " " + startTimeVal.getText();
        }else{
            appTime = endDate.valueProperty().getValue().toString() + " " + endTimeVal.getText();
        }
        
        System.out.println("THE FORMATTED DATE IS: " + appTime);
        return appTime;
    }
    
//    private setAppValues(){
//        app = new Appointment(dbDriver.getCarryCustomer().getCustomerId(), descField.getText(), locationField.getText(), contactField.getText(), urlField.getText(), String start, String end, String createdBy, String lastUpdate, String lastUpdateBy)
//    }
    
    
    private void getAllAppointments(){
        
        
    }
    
    @FXML
    private void addAppointment(){
  
       String addAppString = "INSERT INTO appointment (appointment.customerId, appointment.title, appointment.appDesc, appointment.location, appointment.contact, appointment.url, appointment.start, appointment.end, appointment.createDate, appointment.createdBy, appointment.lastUpdate, appointment.lastUpdateBy) " +
               "VALUES (" +
               dbDriver.getCarryCustomer().getCustomerId() + com +
               q+titleField.getText()+q + com +
               q+descField.getText()+q + com +
               q+locationField.getText() +q + com+
               q+contactField.getText()+q + com +
               q+urlField.getText()+q + com +
               q+ formatDate(startTimeVal.textProperty().toString(), true)+ q + com +
               q+ formatDate(endTimeVal.textProperty().toString(), false) +q + com +
               "now()" + com +
               q+dbDriver.getCurrentAdmin()+ q + com +
               "now()" + com +
               q+dbDriver.getCurrentAdmin() + q + ");";   
       
       System.out.println(addAppString);
               
    }
    
    @FXML
    private void updateStartTime(){
        //updates the start time text value text on the screen to show the time the user has selected, converts to a time value string
        
        Double doubMinsVal = startTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        //System.out.println("min Value is:" + intMinsVal);
        String timeSring = Integer.toString(startTimeSlider.valueProperty().intValue()) + ":" + intMinsVal + ":00";
        
        startTimeVal.textProperty().set(timeSring);
        
    }
    
    @FXML
    private void updateEndTime(){
        //updates the end time text value text on the screen to show the time the user has selected, converts to a time value
        Double doubMinsVal = endTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        //System.out.println("min Value is:" + intMinsVal);
        String timeSring = Integer.toString(endTimeSlider.valueProperty().intValue()) + ":" + intMinsVal + ":00";
        
        endTimeVal.textProperty().set(timeSring);
        
 
        
    }
    
    private void iniTimes(){
        //updates the end time text value text on the screen to show the time the user has selected, converts to a time value
        Double doubMinsVal = endTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        //System.out.println("min Value is:" + intMinsVal);
        String timeSring = Integer.toString(endTimeSlider.valueProperty().intValue()) + ":" + intMinsVal + ":00";
        
        endTimeVal.textProperty().set(timeSring);
    }
    
    private void initTimes(){
        
        startTimeVal.textProperty().set(Double.toString(startTimeSlider.valueProperty().doubleValue()));
        endTimeVal.textProperty().set(Double.toString(endTimeSlider.valueProperty().doubleValue()));
    }
    

}
