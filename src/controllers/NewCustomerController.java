/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.oracle.jrockit.jfr.FlightRecorder.isActive;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class NewCustomerController implements Initializable {

    private Driver dbDriver;
    private char q = '"';
    private int cityId;
    private int addressId;
    private int countryId;
    private ToggleGroup group = new ToggleGroup();
    
    @FXML AnchorPane anchorPane;
    @FXML TextField customerNameField;
    @FXML TextField addressOne;
    @FXML TextField addressTwo;
    @FXML TextField city;
    @FXML TextField postalCode;
    @FXML TextField phone;
    @FXML TextField country;
    @FXML RadioButton isActive;
    @FXML Button save;
    @FXML Button cancel;
    
    
    
    
    
    @FXML public void saveCustomer() throws SQLException{
        
        
        String cityQuery  = "select * from city where city = " + q + city.getText().toString() + q;
        String countryQuery = "select * from country where country = " + q + country.getText().toString() + q;
        boolean isExistingCity = dbDriver.queryCheckIfExists(cityQuery, city.getText().toString(), "city");
        boolean isExistingCountry = dbDriver.queryCheckIfExists(countryQuery, country.getText().toString(), "country");
        
        if(isExistingCountry == true){
            
            //Get the County Id
            System.out.println("Country exists");
            cityId = dbDriver.getIdOfValue(countryQuery, "countryId");
            System.out.println("Country Id is: " + countryId);
               
        }else{
            //Insert city into DB
            String insertQuery = "insert into country ( country, createDate, createdBy, lastUpdate,lastUpdateby) values("+ 
                    q + country.getText().toString()+ q
                    + ",CURRENT_TIMESTAMP, " 
                    + q + dbDriver.getCurrentAdmin()+ q 
                    + ",CURRENT_TIMESTAMP, "
                    + q + dbDriver.getCurrentAdmin()+ q
                    + ");";
            
            dbDriver.queryNoReturn(insertQuery);
            
            //Set the Id
            countryId = dbDriver.getIdOfValue(cityQuery, "countryId");
        }
        
        if(isExistingCity == true){
            
            //Get the City Id
            System.out.println("City exists");
            cityId = dbDriver.getIdOfValue(cityQuery, "cityId");
            System.out.println("City Id is: " + cityId);
               
        }else{
            //Insert city into DB
            String insertQuery = "insert into city ( city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) values("+ 
                    q + city.getText().toString()+ q + ","
                    + countryId + ","
                    + "CURRENT_TIMESTAMP, " 
                    + q + dbDriver.getCurrentAdmin()+ q + ","
                    + "CURRENT_TIMESTAMP, " 
                    + q + dbDriver.getCurrentAdmin()+ q
                    + ");";
            
            dbDriver.queryNoReturn(insertQuery);
            
            //Set the Id
            cityId = dbDriver.getIdOfValue(cityQuery, "cityId");
        }
        
        
        
        
        
        String insertAddressQuery = "insert into address"
                + "("
                + "address,"
                + "address2,"
                + "cityId,"
                + "postalCode,"
                + "phone,"
                + "createDate,"
                + "createdby,"
                + "lastUpdateBy,"
                + "lastUpdate"
                + ")";
        
        String insertAddressVals = "VALUES"
                + "("
                //addressOne
                + q + addressOne.getText().toString() + q + ","
                //AddressTwo
                + q + addressTwo.getText().toString() + q + ","
                //City
                + cityId + ","
                //PostalCode
                + q +postalCode.getText().toString()+ q +","
                //phone
                + q + phone.getText().toString()+ q +","
                //Create Date
                + "CURRENT_TIMESTAMP" +","
                //Created by
                + q + dbDriver.getCurrentAdmin()+ q + ","
                //lastUpdateBy
                + q + dbDriver.getCurrentAdmin()+ q + ","
                //lastUpdate
                + "CURRENT_TIMESTAMP"
                + ");";   
        
        dbDriver.queryNoReturn(insertAddressQuery + insertAddressVals);
        String addressQuery = "select * from address where address = " + q + addressOne.getText().toString() + q+ ";";
        addressId = dbDriver.getIdOfValue(addressQuery, "addressId");
        System.out.println("The address Id is " + addressId);
        
        
        String insertCustomerQuery = "insert into customer"
                + "("
                + "customerName,"
                + "addressId,"
                + "active,"
                + "createDate,"
                + "createdBy,"
                + "lastUpdate,"
                + "lastUpdateBy"
                + ")";        
        
        String insertCustomerVals = "VALUES ("
                +q + customerNameField.getText().toString() + q + ","
                + addressId + ","
                + getActiveVal() + ","
                + "CURRENT_TIMESTAMP,"
                + q + dbDriver.getCurrentAdmin()+ q + ","
                + "CURRENT_TIMESTAMP,"
                + q + dbDriver.getCurrentAdmin()+ q 
                + ");";
        
        dbDriver.queryNoReturn(insertCustomerQuery + insertCustomerVals);
        getActiveVal();
    }
    
    
    private int getActiveVal(){
        
       if(isActive.getToggleGroup().selectedToggleProperty().getValue() != null){
           return 1;
       }else{
           return 0;
       }
        
    }
    
    @FXML public void cancel() throws IOException{
         try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/mainMenuView.fxml"));
             anchorPane.getChildren().setAll(pane);
        }catch(Exception ex){
             System.out.print("EXCEPTION: " + ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        isActive.setToggleGroup(group);
        
    }    
    
}
