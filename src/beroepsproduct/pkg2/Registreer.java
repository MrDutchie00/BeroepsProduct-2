/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct.pkg2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
public class Registreer {
    
    Database db = new Database();
    
    Label lUsername;
    Label lPassword;
    Label lName;
    Label lAlert = new Label();
    
    TextField tUsername;
    PasswordField tPassword;
    TextField tName;
    
    Button btnRegistreer;
    Button btnLogin;
    
    public Registreer(Stage registreerStage, Scene sWelkom){
         registreerStage.setTitle("Kenteken Applicatie");
        
        GridPane gpRegistreer = new GridPane();
        
        Scene sRegistreer = new Scene(gpRegistreer, 800, 500);
        
        gpRegistreer.setAlignment(Pos.CENTER);
        
        gpRegistreer.setPadding(new Insets(10, 10, 10, 10));
        
        gpRegistreer.setVgap(10);
        gpRegistreer.setHgap(10);
        
        lUsername = new Label("Username: ");
        lPassword = new Label("Password: ");
        lName = new Label("Naam: ");
        
        tUsername = new TextField();
        tPassword = new PasswordField();
        tName = new TextField();
        
        btnRegistreer = new Button("Registreer");
        btnLogin = new Button("Login");
        
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
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
               
               if(tName.getText().isEmpty()){
                   tName.setText("Vul een naam in!");
                   return;
                }
               
               if(db.checkUser(tUsername.getText()) == 0){
                   String sUsername = tUsername.getText();
                   String sPassword = tPassword.getText();
                   String sName = tName.getText();
                   int i = db.getSize();
                   try{
                   db.createUser(i, sUsername, sPassword, sName);
                   }
                   catch (Exception e){
                       e.getMessage();
                   }
                   registreerStage.setScene(sWelkom);
               }
               else{
                   lAlert.setText("Username is al bezet, kies een andere");
               }
               
               tUsername.setText("");
               tPassword.setText("");
               tName.setText("");
               
            }
            
        });
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login login = new Login (registreerStage, sWelkom);
            }
            
        });
        
        
        gpRegistreer.add(lUsername, 0, 0);
        gpRegistreer.add(tUsername, 1, 0);
        
        gpRegistreer.add(lPassword, 0, 1);
        gpRegistreer.add(tPassword, 1, 1);
        
        gpRegistreer.add(lName, 0, 2);
        gpRegistreer.add(tName, 1, 2);
        
        gpRegistreer.add(btnRegistreer, 0, 3);
        gpRegistreer.add(btnLogin, 1, 3);
        
        gpRegistreer.add(lAlert, 0, 4);
        
        
        
        registreerStage.setScene(sRegistreer);
        
        registreerStage.show();
    }
    
}
