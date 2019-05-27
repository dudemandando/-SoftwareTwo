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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import softwaretwo.AlertBox;
import softwaretwo.Driver;


    


public class LoginViewController implements Initializable {

    @FXML private AnchorPane rootPane;
    
    //Exisiting User Login
    @FXML TextField userName;
    @FXML TextField password;
    @FXML Button loginButton;
    
    //New User Create and Login
    @FXML TextField createUserName;
    @FXML TextField createPassword;
    @FXML Button createUserLogin;
    
    char q = '"';
    private Driver dbDriver; 
    private boolean isValid;
    
    
    
    
    @FXML private void loginExisting(ActionEvent event) throws IOException{
            System.out.print("login existing");
    }
    

    
    @FXML private void checkUserLogin(ActionEvent event) throws IOException, SQLException {
        System.out.println("Existing Login");
        String dbCheckString = "select * from user where userName = " + q + userName.getText().toString() + q + " && password = " + q + password.getText().toString() + q + ";";
        System.out.println(dbCheckString);
        ResultSet myRs = dbDriver.queryAndReturn(dbCheckString);
        
        if(myRs != null ){
            if(myRs.next()){
                if(myRs.getString("userName").equals(userName.getText().toString()) && myRs.getString("password").equals(password.getText().toString())){
                    System.out.println("UserName and Password Match");
                    isValid = true;
                    
                    
                }
            }else{
//                if(myRs.getString("userName").equals(userName.getText().toString()) && myRs.getString("password").equals(password.getText().toString())){
//                    System.out.println("UserName and Password Match");
//                    isValid = true;
//                    
//                }else{
//                    
//                }
                AlertBox.display("Login Error", "User Name and Password Not Found \n"
                        + "Do not leave fields blank \n"
                        + "Ensure your User Name and Password are correct");
            }
        
        }
        
        if(isValid == true){
            //Set the current User in the database Driver Class
            dbDriver.setCurrentAdmin(userName.getText().toString());
            
            //Take the user into the App
             //changeToMainMenu();
            changeToMainMenu();
            
            
        }
    
    
    }
    
    
    private void changeToMainMenu() throws IOException{
        System.out.println(" Change to Main Menu view");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/mainMenuView.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            dbDriver.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
