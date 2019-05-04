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
   
    private char q = '"';
    private char semi = ';';
    private char com = ',';
   
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private Appointment app;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateStartTime();
        updateEndTime();
        
    }
    
    private void formatDate(){
        //Get the Dates and format correctly
        //yyyy-mm-dd hh:mm:ss
        
        System.out.println("THE CURRENT DATE IS: " + startDate.getValue().toString());
    }
    
//    private setAppValues(){
//        app = new Appointment(dbDriver.getCarryCustomer().getCustomerId(), descField.getText(), locationField.getText(), contactField.getText(), urlField.getText(), String start, String end, String createdBy, String lastUpdate, String lastUpdateBy)
//    }
    
    
    private void getAllAppointments(){
        
        
    }
    
    @FXML
    private void addAppointment(){
        formatDate();
        
        
//       String addAppString = "INSET INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) " +
//               "VALUES (" +
//               ;
    }
    
    @FXML
    private void updateStartTime(){
        //updates the start time text value text on the screen to show the time the user has selected
//        startTimeVal.textProperty().set(Double.toString(startTimeSlider.valueProperty().doubleValue()));
        
         //updates the end time text value text on the screen to show the time the user has selected
        Double doubMinsVal = startTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        //System.out.println("min Value is:" + intMinsVal);
        String timeSring = Integer.toString(startTimeSlider.valueProperty().intValue()) + ":" + intMinsVal + ":00";
        
        startTimeVal.textProperty().set(timeSring);
    }
    
    @FXML
    private void updateEndTime(){
        //updates the end time text value text on the screen to show the time the user has selected
        Double doubMinsVal = endTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        //System.out.println("min Value is:" + intMinsVal);
        String timeSring = Integer.toString(endTimeSlider.valueProperty().intValue()) + ":" + intMinsVal + ":00";
        
        endTimeVal.textProperty().set(timeSring);
    }
    
    private void convertSliderToActualTime(){
        //Takes the double slider value and converts to a time that can be put into the database
        
    }
}
