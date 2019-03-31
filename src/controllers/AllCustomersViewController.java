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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import softwaretwo.Driver;
import softwaretwo.Customer;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class AllCustomersViewController implements Initializable {

    
    private Driver dbDriver;
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    @FXML AnchorPane root;
    @FXML TableView<Customer> customersTable;
    @FXML TableColumn <Customer, Integer> colCustomerId;
    @FXML TableColumn <Customer, String> colCustomerName;
    @FXML TableColumn <Customer, Integer>colCustomerActive;
    @FXML TableColumn <Customer, String> colCustomerLastUpdate;
    @FXML Button editUser;
    @FXML Button cancel;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            populateAllCustomers();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    @FXML 
    private void editSelected() throws IOException{
        System.out.println("Modify Selected Button");
        modifySelectedCustomer();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/modifyCustomerView.fxml"));
        root.getChildren().setAll(pane);
        
        
        
    }
    
    @FXML 
    private void returnToMain() throws IOException{
        System.out.println("Returning to Main Menu view");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/mainMenuView.fxml"));
        root.getChildren().setAll(pane);
    }
    
    private void populateAllCustomers() throws SQLException{
        String query = "select * from customer;";
        ResultSet result = dbDriver.queryAndReturn(query);

        if(result != null){
            while(result.next()){
                Customer entry = new Customer(
                    result.getInt("customerId"),
                    result.getString("customerName"),
                    Integer.parseInt(result.getString("addressId")),
                    Integer.parseInt(result.getString("active")),
                    result.getString("createDate"),
                    result.getString("createdBy"),
                    result.getString("lastUpdate"),
                    result.getString("lastUpdateBy")
                );
            
                allCustomers.add(entry);
            
                
                    
            }
            for(int i =0; i< allCustomers.size(); i++ ){
                    System.out.println("Name is: " + allCustomers.get(i).getCustomerName());
                }
            
            populateAllCustomersTable();
            
        }else{
            System.out.println("is null");
           
        }
        
    }
    
    private void populateAllCustomersTable(){
        
        colCustomerName.setCellValueFactory(new PropertyValueFactory  ("customerName"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory <Customer, Integer> ("customerId"));
        colCustomerActive.setCellValueFactory(new PropertyValueFactory <Customer, Integer> ("customerIsActive"));
        colCustomerLastUpdate.setCellValueFactory(new PropertyValueFactory <Customer, String> ("customerLastUpdate"));
        customersTable.setItems(allCustomers);
        customersTable.refresh();

        
    }
    
    private void modifySelectedCustomer(){
        if(allCustomers.size() >  0){
            
                Customer selected = customersTable.getSelectionModel().getSelectedItem();
                System.out.println(selected.getCustomerName());
                dbDriver.setCarryCustomer(selected);
            
        }
    }
    
}
