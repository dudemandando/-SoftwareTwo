/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import softwaretwo.Appointment;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Megatron
 */
public class EditAppViewController implements Initializable {

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
   private Appointment selected;
    
   private char q = '"';
    private char semi = ';';
    private char com = ',';
   
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private Appointment app;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       try {
           getAllAppointments();
       } catch (SQLException ex) {
           Logger.getLogger(EditAppViewController.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 
    
    @FXML
    private void cancel(){
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/SelectToMakeApp.fxml"));
            root.getChildren().setAll(pane);
            dbDriver.nullOutCarryCustomer();
            
        }catch(Exception ex){
        System.out.print(ex);
        }
    }
    
    private void getAllAppointments() throws SQLException{
        String allAppsString = "select * from appointment where customerId = " + dbDriver.getCarryCustomer().getCustomerId() + ";";
        
        System.out.println(allAppsString);
        ResultSet result = dbDriver.queryAndReturn(allAppsString);
        
        while(result.next()){
            
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
        
//        //populate the table
        locationCol.setCellValueFactory(new PropertyValueFactory  ("location"));
        startCol.setCellValueFactory(new PropertyValueFactory <Appointment, String> ("start"));
        endCol.setCellValueFactory(new PropertyValueFactory <Appointment, String> ("end"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory <Appointment, String> ("descrip"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory <Appointment, String> ("lastUpdate"));
        
        existingAppTable.setItems(allAppointments);
        existingAppTable.refresh();
        
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
    
    @FXML 
    private void save(){
        //String saveAppQuery = "UPDATE appointment set "
    }
    
    @FXML
    private void populateFieldWithSelected() throws ParseException{
        
        if(allAppointments.size() > 0){
           selected =  (Appointment) existingAppTable.getSelectionModel().getSelectedItem();
           titleField.setText(selected.getTitle());
           descField.setText(selected.getDescrip());
           locationField.setText(selected.getLocation());
           contactField.setText(selected.getContact());
           urlField.setText(selected.getUrl());
           
           setDate(true);
           setDate(false);
           
        }
    }
    
    private void setTime(boolean isStart) throws ParseException{
        Date date;
        String hourString;
        String minString;
        if(isStart){
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selected.getStart().toString());
        }else{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selected.getEnd().toString());
        }
        
        hourString = new SimpleDateFormat("HH").format(date);
        minString = new SimpleDateFormat("mm").format(date);
        
        String timeSring = hourString + ":" + minString + ":00";
        if(isStart){
            startTimeVal.textProperty().set(timeSring);
            Double hourInt = Double.parseDouble(hourString);
            Double minsDoub = Double.parseDouble(minString) / 60f;
            Double sliderVal = hourInt + minsDoub;
            startTimeSlider.setValue(sliderVal);
            
        }else{
            endTimeVal.textProperty().set(timeSring);
            Double hourInt = Double.parseDouble(hourString);
            Double minsDoub = Double.parseDouble(minString) / 60f;
            Double sliderVal = hourInt + minsDoub;
            endTimeSlider.setValue(sliderVal);
            
        }
        
        //System.out.println("THE TIME IS: " + hourString + " and mins "+ minString);
        
        
    }
    
    private void setDate(boolean isStart) throws ParseException{
        Date date;
        String dateString;
        if(isStart){
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selected.getStart().toString());
            dateString = new SimpleDateFormat("yyy-MM-dd").format(date);
            startDate.setValue(LocalDate.parse(dateString));
            setTime(true);
        }else{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selected.getEnd().toString());
            dateString = new SimpleDateFormat("yyy-MM-dd").format(date);
            endDate.setValue(LocalDate.parse(dateString));
            setTime(false);
        }
 
    }
  
}
