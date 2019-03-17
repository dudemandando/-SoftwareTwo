/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dan Burke
 */
public class AllCustomersViewController implements Initializable {

    @FXML AnchorPane root;
    @FXML TableView customersTable;
    @FXML Button editUser;
    @FXML Button cancel;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML 
    private void editSelected() throws IOException{
        System.out.println("Edit Selected Button");
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/ModifyCusotmerView.fxml"));
        root.getChildren().setAll(pane);
        }catch(Exception ex){
            System.out.println();
        }
        
    }
    
    @FXML 
    private void returnToMain() throws IOException{
        System.out.println("Returning to Main Menu view");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/mainMenuView.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
