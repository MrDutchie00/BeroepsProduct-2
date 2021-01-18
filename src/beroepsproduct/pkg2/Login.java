/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct.pkg2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author stanb
 */
public class Login {
    Database db = new Database();
    
   
    Label lUsername;
    Label lPassword;
    Label lAlert = new Label();
    
    TextField tUsername;
    PasswordField tPassword;
    
    Button btnLogin;
    Button btnRegistreer;
    
    public Login(Stage stageLogin, Scene sWelkom){
        stageLogin.setTitle("Kenteken Applicatie");
        
        GridPane gpLogin = new GridPane();
        
        Scene sLogin = new Scene(gpLogin, 800, 500);
        
        gpLogin.setAlignment(Pos.CENTER);
        
        gpLogin.setPadding(new Insets(10, 10, 10, 10));
        
        gpLogin.setVgap(10);
        gpLogin.setHgap(10);
        
        lUsername = new Label("Username: ");
        lPassword = new Label("Password: ");
        
        tUsername = new TextField();
        tPassword = new PasswordField();
        
        btnLogin = new Button("Login");
        btnRegistreer = new Button("Registreer");
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               if(tUsername.getText().isEmpty()){
                   tUsername.setText("Vul een gebruikersnaam in!");
                   return;
               }
               if(tPassword.getText().isEmpty()){
                   tPassword.setText("Vul een wachtwoord in!");
                   return;
               }
               
               String sPassword = tPassword.getText();
               String sUsername = tUsername.getText();
               if(sUsername.equals(db.GetUsername(tUsername.getText(), tPassword.getText())) && sPassword.equals(db.GetPassword(tUsername.getText(), tPassword.getText()))){
                   Gebruiker gebruiker = new Gebruiker();
                   gebruiker.setGebruikerId(db.GetUserId(tUsername.getText(), tPassword.getText()));
                   System.out.println(gebruiker.getGebruikerId());
                   Hoofdscherm hoofdscherm = new Hoofdscherm(stageLogin, sWelkom, gebruiker);
               }
               else{
                   lAlert.setText("Verkeerde inloggegevens");
               }
               tUsername.setText("");
               tPassword.setText("");
            }
            
        });
       
        
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Registreer registreer = new Registreer (stageLogin, sWelkom);
            }
            
        });
        
        gpLogin.add(lUsername, 0, 0);
        gpLogin.add(tUsername, 1, 0);
        gpLogin.add(lPassword, 0, 1);
        gpLogin.add(tPassword, 1, 1);
        gpLogin.add(btnLogin, 0, 2);
        gpLogin.add(btnRegistreer, 1, 2);
        gpLogin.add(lAlert, 0, 3);
        GridPane.setHalignment(btnLogin, HPos.CENTER);
        
        stageLogin.setScene(sLogin);
        
        stageLogin.show();
    }
}
