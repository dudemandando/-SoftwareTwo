/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class ModifyCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML AnchorPane anchorPane;
    @FXML TextField customerName;
    @FXML TextField addressOne;
    @FXML TextField addressTwo;
    @FXML TextField city;
    @FXML TextField postalCode;
    @FXML TextField country;
    @FXML TextField phoneNumber;
    @FXML RadioButton isActive;
    @FXML Button saveButton;
    @FXML Button cancelButton;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void onSaveButton(){
        System.out.println("Modify Customer Save Button");
        
    }
    
    @FXML
    public void onCancelButton(){
        System.out.println("Modify Customer Cancel Button");
    }
    
    private void populateInfo(){
        
    }
    
}
