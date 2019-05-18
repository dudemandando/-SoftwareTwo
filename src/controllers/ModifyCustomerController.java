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
import softwaretwo.AlertBox;
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
    private char semi = ';';
    private char com = ',';
    
    
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
    
    private boolean  validateFields(){
       
        if(customerName.getText().toString().trim().isEmpty() || addressOne.getText().toString().trim().isEmpty() || city.getText().toString().trim().isEmpty() || postalCode.getText().toString().trim().isEmpty() || phoneNumber.getText().toString().trim().isEmpty() ||  country.getText().toString().trim().isEmpty()){
            
             return false;
        }else{
            return true;
        }
        
    }
    
    @FXML
    public void onSaveButton() throws SQLException{
        if(validateFields()){
            System.out.println("Modify Customer Save Button");
            determineActivity();
            setNewInfo();
            checkCountry();
            checkCity();
            updateAll();
            returnToAllCustomers();
        }else{
            AlertBox.display("Empty Fields", "All fields must be completed");
        }
        
    }
    
    @FXML
    public void onCancelButton(){
        System.out.println("Modify Customer Cancel Button - Returning to All Customers View");
        currentCust = null;
        returnToAllCustomers();
        
    }
    
    private void returnToAllCustomers(){
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
        currentCust.setIsActive(determineActivity());
        
    }
    
    private void checkCountry() throws SQLException{
       //Check the Country
       String countryQuery = "select * from country where country = " + q + currentCust.getCountry() + q + ";";
       isExisitingCountry = dbDriver.queryCheckIfExists(countryQuery, currentCust.getCountry(), "country");

       System.out.println("Does Country exisit? " + currentCust.getCountry() + " : " + isExisitingCountry);
       
       if(!isExisitingCountry){
           
           String addCountryQuery = "insert into country (country, createDate, createdBy, lastUpdate, lastUpdateby)";
           String addCountryValuesQuery = " values ("
                   + q + currentCust.getCountry() + q + com
                   + "NOW()" + com
                   + q +dbDriver.getCurrentAdmin() + q + com
                   + "NOW()" + com
                   + q +dbDriver.getCurrentAdmin() + q 
                   +");"
                   //End of String
                   ;
           
            
           //Add the new Country
           dbDriver.queryNoReturn(addCountryQuery + addCountryValuesQuery);
           
           
       }
       //Retrieve the CoutnryId and set it      
       currentCust.setCountryId(dbDriver.getIdOfValue(countryQuery, "countryId"));
       System.out.println("The Country Id set is: " + currentCust.getCountryId());
    }
    
    private void checkCity() throws SQLException{
        
        
        
        //Check the City
       String cityQuery = "select * from city where city= " + q + currentCust.getCity() + q + ";";
       isExisitingCity = dbDriver.queryCheckIfExists(cityQuery, currentCust.getCity(), "city");
       
       System.out.println("Does City exisit " + currentCust.getCity() + "? : " + isExisitingCity);
       
       
         //Check and get Id's as needed
       if(!isExisitingCity){
                
           String addCityQuery = "insert into city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateby)";
           String valuesQuery = "values ("
                   + q + currentCust.getCity() + q + com
                   + currentCust.getCountryId() + com
                   + "CURRENT_TIMESTAMP()" + com
                   + q +dbDriver.getCurrentAdmin() + q + com
                   + "CURRENT_TIMESTAMP()" + com
                   + q +dbDriver.getCurrentAdmin() + q  
                   +");"
                   //End of String
                   ;
           //Add the new City
           System.out.println("Adding the city");
           dbDriver.queryNoReturn(addCityQuery + valuesQuery);
           
       }
       
       //Retrieve the CityId
       currentCust.setCityId(dbDriver.getIdOfValue(cityQuery, "cityId"));
       System.out.println("The new city Id is: " + currentCust.getCityId());
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
    
    private void updateAll() throws SQLException{
        
        String updateCustomerString =
                "update customer set customerName="+ q +  currentCust.getCustomerName() + q + com
                +"active=" + q + currentCust.getIsActive() + q 
                +" where customerId=" + currentCust.getCustomerId() + semi
                //End of String
                ;

            String updateAddressString =
                "update address set address=" + q + currentCust.getAddressOne() + q + com
                + "address2=" + q + currentCust.getAddressTwo() + q + com
                +"cityId=" + q + currentCust.getCityId() + q + com
                +"postalCode=" + q + currentCust.getPostalCode() + q + com
                +"phone=" + q + currentCust.getPhone() + q + com
                +"lastUpdate= NOW() " + com
                +"lastUpdateBy=" + q + dbDriver.getCurrentAdmin() + q + semi
                    
                //End of string    
                ;
                    
        
            System.out.println("THE STRING I USED IS: "+ updateCustomerString);
            dbDriver.queryNoReturn(updateCustomerString);
            dbDriver.queryNoReturn(updateAddressString);
        }
}
