/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
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

   @FXML AnchorPane rootPane;
   
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
    
    // operation is implemented using lambda expressions 
    interface FuncInter1 
    { 
        String operation(String a, String b, String c); 
    } 
    
    private String operate(String a, String b, String c, FuncInter1 fobj) 
    { 
        return fobj.operation(a, b, c); 
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       try {
           getAllAppointments();
       } catch (SQLException ex) {
           Logger.getLogger(EditAppViewController.class.getName()).log(Level.SEVERE, null, ex);
       }
       initTimes();
       
    } 
    
    @FXML
    private void cancel(){
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/SelectToMakeApp.fxml"));
            rootPane.getChildren().setAll(pane);
            dbDriver.nullOutCarryCustomer();
            
        }catch(Exception ex){
        System.out.print(ex);
        }
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
        
        //System.out.println("THE FORMATTED DATE IS: " + appTime);
        return appTime;
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
        FuncInter1 add = (String x, String y, String z )-> x + y + z;
        
        Double doubMinsVal = startTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        String timeString;
        //System.out.println("min Value is:" + intMinsVal);
        if(startTimeSlider.valueProperty().intValue() < 10){
             
             timeString =  operate("0", Integer.toString(startTimeSlider.valueProperty().intValue()), "", add);
             
        }else{
             timeString = Integer.toString(startTimeSlider.valueProperty().intValue());
             
        }
        
        if(intMinsVal < 10){
            
            timeString = timeString + operate(":0",intMinsVal.toString(), ":00",add);
        }else{
             //timeString = timeString + ":" + intMinsVal + ":00";
             timeString = timeString + operate(":",intMinsVal.toString(), ":00",add);
        }
        
        startTimeVal.textProperty().set(timeString);
        
    }
    
    @FXML
    private void updateEndTime(){
        FuncInter1 add = (String x, String y, String z)-> x + y + z;
        //updates the end time text value text on the screen to show the time the user has selected, converts to a time value
        Double doubMinsVal = endTimeSlider.valueProperty().doubleValue() % 1;
        doubMinsVal = doubMinsVal * 60;
        Integer intMinsVal = doubMinsVal.intValue();
      
        String timeString;
        //System.out.println("min Value is:" + intMinsVal);
                
        if(endTimeSlider.valueProperty().intValue() < 10){
             
             timeString =  operate("0", Integer.toString(endTimeSlider.valueProperty().intValue()),"", add);
             
        }else{
             timeString = Integer.toString(endTimeSlider.valueProperty().intValue());
             
        }
        
        if(intMinsVal < 10){
            
            timeString = timeString + operate(":0",intMinsVal.toString(),":00", add);
        }else{
             //timeString = timeString + ":" + intMinsVal + ":00";
             timeString = timeString + operate(":",intMinsVal.toString(),":00",add);
        }
        
        endTimeVal.textProperty().set(timeString);
 
    }
    
    @FXML 
    private void save() throws SQLException, IOException{
        System.out.println("Updating Appointment Record");
        String saveAppQuery = "UPDATE appointment set "
                + "appointment.title = " + q + titleField.getText() + q + com +
                "appointment.appDesc = " + q +descField.getText() + q + com +
                "appointment.location = " + q +locationField.getText() + q + com +
                "appointment.contact = " + q +locationField.getText() + q + com +
                "appointment.url = " + q +locationField.getText() + q + com +
                "appointment.start = " + q + formatDate(startTimeVal.getText(), true) + q + com +
                "appointment.end = " + q + formatDate(endTimeVal.getText(), false) + q + com +
                "appointment.lastUpdate = now()," + 
                "appointment.lastUpdateBy = " + q +dbDriver.getCurrentAdmin()+ q + " WHERE appointmentId = " + selected.getAppId() + ";";
        
        System.out.println(saveAppQuery);
        dbDriver.queryNoReturn(saveAppQuery);
        
        AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/SelectToMakeApp.fxml"));
        rootPane.getChildren().setAll(pane);
        dbDriver.nullOutCarryCustomer();
       
        
    }
    
    @FXML 
    private void delete(){
        
       if(selected != null){
           String deleteQuery = "DELETE from appointment where appointment.appointmentId =" + selected.getAppId() + ";";
           dbDriver.queryNoReturn(deleteQuery);
           System.out.println("deleted app");
       }else{
           System.out.println("Null app selection");
       }
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
           System.out.println("Start is: " + selected.getStart());
           System.out.println("End is: " + selected.getEnd());
           setDate(selected.getStart(), true);
           setDate(selected.getEnd(), false);
           
        }
    }
    
    private void setTime(String dateTimeString, boolean isStart) throws ParseException{
        Date date;
        String hourString;
        String minString;
        
        
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeString);
        
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
      
        
    }
    
    private void setDate(String dateTimeString, boolean isStart) throws ParseException{
        Date date;
        String dateString;
        
        if(isStart){
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeString);
            dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            startDate.setValue(LocalDate.parse(dateString));
            setTime(dateTimeString, true);
        }else{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeString);
            dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            endDate.setValue(LocalDate.parse(dateString));
            setTime(dateTimeString, false);
        }
 
    }
    
    private void initTimes(){
        
        startTimeVal.textProperty().set(Double.toString(startTimeSlider.valueProperty().doubleValue()));
        endTimeVal.textProperty().set(Double.toString(endTimeSlider.valueProperty().doubleValue()));
        updateStartTime();
        updateEndTime();
    }
  
}
