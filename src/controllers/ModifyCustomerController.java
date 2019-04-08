/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import softwaretwo.Customer;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class ModifyCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML AnchorPane root;
    @FXML TextField customerName;
    @FXML TextField addressOne;
    @FXML TextField addressTwo;
    @FXML TextField city;
    @FXML TextField postalCode;
    @FXML TextField country;
    @FXML TextField phoneNumber;
    
    @FXML Button saveButton;
    @FXML Button cancelButton;
    
    @FXML RadioButton isActive;
    private ToggleGroup group;

    private Driver dbDriver;
    private Customer currentCust;
    private boolean isExisitingCity;
    private boolean isExisitingCountry;
    
    private char q = '"';
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        group = new ToggleGroup();
        currentCust = dbDriver.getCarryCustomer();
        try {
            populateInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ModifyCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @FXML
    public void onSaveButton() throws SQLException{
        System.out.println("Modify Customer Save Button");
        determineActivity();
        setNewInfo();
        checkCityAndCountry();
    }
    
    @FXML
    public void onCancelButton(){
        System.out.println("Modify Customer Cancel Button - Returning to All Customers View");
        currentCust = null;
        try{
             AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AllCustomersView.fxml"));
            root.getChildren().setAll(pane);
        }catch(Exception ex){
        System.out.print(ex);
        }
        
    }
    
    private void populateInfo() throws SQLException{
        setAddress();
        customerName.setText(currentCust.getCustomerName());
        
    }
    
    private void setAddress() throws SQLException{
        //Get the Address
        String addressQuery = "select * from address where address.addressId = " + currentCust.getCustomerAddressId() + ";";
       
        ResultSet addressRs = dbDriver.queryAndReturn(addressQuery);
        addressRs.next();
        
        //Get the City
        String cityQuery = "select * from city where city.cityId =" + addressRs.getInt("cityId") + ";";
        ResultSet cityRs = dbDriver.queryAndReturn(cityQuery);
        cityRs.next();
        
        //Get the Country
        String countryQuery = "select * from country where country.countryId =" + cityRs.getInt("countryId") + ";";
        ResultSet countryRs = dbDriver.queryAndReturn(countryQuery);
        countryRs.next();
        
        System.out.println("country is: " + countryRs.getString("country"));
        
        //set the text
        addressOne.setText(addressRs.getString("address"));
        addressTwo.setText(addressRs.getString("address2"));
        postalCode.setText(addressRs.getString("postalCode"));
        phoneNumber.setText(addressRs.getString("phone"));
        city.setText(cityRs.getString("city"));
        country.setText(countryRs.getString("country"));
          
        //set the current customer object data
        currentCust.setAddressOne(addressRs.getString("address"));
        currentCust.setAddressOne(addressRs.getString("address2"));
        currentCust.setPostalCode(addressRs.getString("postalCode"));
        currentCust.setPhone(addressRs.getString("phone"));
        currentCust.setCity(cityRs.getString("city"));
        currentCust.setCountry(countryRs.getString("country"));
        
        
    }
    
    private void setNewInfo(){
        
        
        //set the customer refs
        currentCust.setCustomerName(customerName.getText());
        

        //set the Address Refs
        currentCust.setAddressOne(addressOne.getText());
        currentCust.setAddressTwo( addressTwo.getText());
        currentCust.setPostalCode(postalCode.getText());
        currentCust.setPhone(phoneNumber.getText());
        currentCust.setCity(city.getText());
        currentCust.setCountry(country.getText());
        
        //Set the isActive ref
        //currentCust.setIsActive(determineActivity());
        
    }
    
    private void checkCityAndCountry() throws SQLException{
        
       //Check the City
       String cityQuery = "select * from city where city.city= " + q + currentCust.getCity() + q + ";";
       isExisitingCity = dbDriver.queryCheckIfExists(cityQuery, currentCust.getCity(), "city");
      
       //Check the Country
       String countryQuery = "select * from country where country.country= " + q + currentCust.getCountry() + q + ";";
       isExisitingCountry = dbDriver.queryCheckIfExists(countryQuery, currentCust.getCountry(), "country");
       
       System.out.println("Does City exisit? " + currentCust.getCity() + " : " + isExisitingCity);
       System.out.println("Does City exisit? " + currentCust.getCountry() + " : " + isExisitingCountry);

    }
    
    
    private int determineActivity(){
        //Set the toggle group to get the radio button value
        isActive.setToggleGroup(group);
        
        if(isActive.getToggleGroup().selectedToggleProperty().getValue() != null){
            System.out.println("Activity is True");
           return 1;
       }else{
            System.out.println("Activity is False");
           return 0;
       }
    }
    
    
}
