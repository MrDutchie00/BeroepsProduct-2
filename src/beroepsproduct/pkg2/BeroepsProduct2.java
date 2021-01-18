/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct.pkg2;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author stanb
 */
public class BeroepsProduct2 extends Application {
    
    Database db = new Database();
    
    Label lWelkom;
    Button btnLogin;
    Button btnRegistreer;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Kenteken Applicatie");
        
        GridPane gpWelkom = new GridPane();
        
        Scene sWelkom = new Scene(gpWelkom, 800, 500);
        
        gpWelkom.setAlignment(Pos.CENTER);
        
        gpWelkom.setPadding(new Insets(10, 10, 10, 10));
        
        gpWelkom.setVgap(10);
        gpWelkom.setHgap(10);
        
        
        lWelkom = new Label("Welkom bij de kenteken applicatie, kies voor Inloggen of Registreren");
        btnLogin = new Button("Login");
        btnRegistreer = new Button("Registreer");
        
        gpWelkom.add(lWelkom, 0, 0);
        gpWelkom.add(btnLogin, 0, 1);
        gpWelkom.add(btnRegistreer, 0, 2);
        GridPane.setHalignment(btnLogin, HPos.CENTER);
        GridPane.setHalignment(btnRegistreer, HPos.CENTER);
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login login = new Login (primaryStage, sWelkom);
            }
            
        });
        
        btnRegistreer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Registreer registreer = new Registreer (primaryStage, sWelkom);
            }
            
        });
        
        
        primaryStage.setScene(sWelkom);
        
        primaryStage.show();
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
