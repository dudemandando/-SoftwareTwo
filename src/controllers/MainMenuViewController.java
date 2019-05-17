/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class MainMenuViewController implements Initializable {

    @FXML AnchorPane rootPane;
    @FXML Button createNewCustomerViewButton;
    @FXML Button AllCustomersViewButton;
    @FXML Button createAppointmentButton;
    @FXML Button appsByMonth;
    private Driver dbDriver;
    
    
    
    @FXML public void AllCustomersView(ActionEvent event) throws IOException {
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AllCustomersView.fxml"));
            rootPane.getChildren().setAll(pane);
        }catch(Exception ex){
        System.out.print(ex);
        }
    }
    
    @FXML public void newCustomersView(ActionEvent event) throws IOException {
        
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/NewCustomerView.fxml"));
            rootPane.getChildren().setAll(pane);
        }catch(Exception ex){
        System.out.print(ex);
        }
       
    }
    
    @FXML public void CreateAppointmentView(ActionEvent event) throws IOException {
        
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/SelectToMakeApp.fxml"));
            rootPane.getChildren().setAll(pane);
        }catch(Exception ex){
        System.out.print(ex);
        }
       
    }
    
    @FXML
    public void viewAppointmentsCal(){
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/appCalendarView.fxml"));
            rootPane.getChildren().setAll(pane);
        }catch(Exception ex){
        System.out.print(ex);
        }
    }
    
    @FXML 
    public void generateAppsByMonth(){
        System.out.println("Genreate Apps By Month Button Click");
    }
    
    @FXML
    public void generateSchedulePerConsultant() throws IOException, SQLException{
        System.out.println("Genreate Schedule per Consultant Button Click");
        String consultantScheduleQuery = "select contact, title, appDesc, location, contact, start "
                + "from appointment group by contact order by start;";
        
        GenerateReport(consultantScheduleQuery, "ConsultantSchedule.csv");
    }
    
    @FXML
    public void generateAppsByLocation() throws IOException, SQLException{
        System.out.println("Generate Apps ordered by Location Button Click");
        String locationQuery = "select * from appointment order by location;";
        GenerateReport(locationQuery, "AppsByLocation.csv");
        
        
        
    }
    
    private void GenerateReport(String query, String fileName) throws IOException, SQLException{
        
        System.out.println("Generate Report Method");
        System.out.println(query);
        try{
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f, true);
            ResultSet result = dbDriver.queryAndReturn(query);
            ResultSetMetaData rsmd = result.getMetaData();

            String headerString = "";
            
            for(int i = 1; i <= rsmd.getColumnCount(); i++){
                headerString += rsmd.getColumnName(i) + ",";
                
            }
            headerString += "\n";
            fw.write(headerString);
            while(result.next()){
                String reportString = "";
                
                for(int i = 1; i<= rsmd.getColumnCount(); i++){
                    if(rsmd.getColumnName(i) == "start"){
                        System.out.println(" start is"+result.getString(i));
                    }
                 reportString += result.getString(i) + ",";
                }
                reportString += "\n";
                fw.write(reportString);
            }
            
            fw.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
