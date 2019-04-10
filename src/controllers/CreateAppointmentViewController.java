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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import softwaretwo.Customer;
import softwaretwo.Driver;

/**
 * FXML Controller class
 *
 * @author Megatron
 */
public class CreateAppointmentViewController implements Initializable {

    private Driver dbDriver;
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    @FXML TableView<Customer> allCustTable;
    @FXML TableColumn <Customer, Integer> colCustomerId;
    @FXML TableColumn <Customer, String> colCustomerName;
    @FXML TableColumn <Customer, String> colCustomerLastUpdate;
    @FXML Button makeAppButton;
    @FXML Button returnToMain;
    
    
    
    @Override   
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(" Create New Appointment View");
        try{
            populateAllCustomersTable();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
   private void populateAllCustomersTable() throws SQLException{
       
       String query = "select * from customer where active=1;";
        ResultSet result = dbDriver.queryAndReturn(query);

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

        colCustomerName.setCellValueFactory(new PropertyValueFactory  ("customerName"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory <Customer, Integer> ("customerId"));
        colCustomerLastUpdate.setCellValueFactory(new PropertyValueFactory <Customer, String> ("customerLastUpdate"));
        allCustTable.setItems(allCustomers);
        allCustTable.refresh();

        
    }
   
    private void moveToCreateAppointment(){
        if(allCustomers.size() >  0){
            
                Customer selected = allCustTable.getSelectionModel().getSelectedItem();
                System.out.println(selected.getCustomerName());
                dbDriver.setCarryCustomer(selected);
            
        }
    }
   
 
    
}
