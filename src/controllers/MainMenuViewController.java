/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class MainMenuViewController implements Initializable {

    @FXML AnchorPane rootPane;
    @FXML Button createNewCustomerViewButton;
    @FXML Button AllCustomersViewButton;
    
    
    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
