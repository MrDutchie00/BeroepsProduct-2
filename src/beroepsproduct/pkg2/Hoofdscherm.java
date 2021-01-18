/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct.pkg2;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author stanb
 */
public class Hoofdscherm {
    
    Database db;
    JSONDatabase jdb;
    GridPane gpHoofd;
    Scene hScene;
    
    Label lWelkom;
    Label lExtra;
    Label lKenteken;
    Label lFout;
    Label lMerk;
    Label lModel;
    Label lBouwjaar;
    Label lCilinderinhoud;
    Label lWielbasis;
    Label lBrandstof;
    
    TextField tKenteken;
    TextField tMerk;
    TextField tModel;
    TextField tBouwjaar;
    TextField tCilinderinhoud;
    TextField tWielbasis;
    TextField tBrandstof;
    
    Button btnKenteken;
    Button btnOpslaan;
    Button btnLogUit;
    Button btnFavorieten;
    
   
    Kenteken kenteken;
    
    
    
    public Hoofdscherm(Stage hoofdStage, Scene sWelkom, Gebruiker gebruiker){
        
        try{
        db = new Database();
        JSONDatabase jdb = new JSONDatabase();
        gpHoofd = new GridPane();
        hScene = new Scene(gpHoofd, 800, 500);
        
        gpHoofd.setVgap(10);
        gpHoofd.setHgap(10);
        gpHoofd.setAlignment(Pos.CENTER);
        
        lWelkom = new Label("Welkom "+ db.getName(gebruiker.getGebruikerId()) + "! U kunt nu beginnen!");
        lFout = new Label("");
        
        lKenteken = new Label("Vul kenteken in:");
        lExtra = new Label("(Zonder streepjes)");
        tKenteken = new TextField();
        
        btnKenteken = new Button("Zoek");
        btnOpslaan = new Button("Opslaan");
        btnLogUit = new Button("Log Uit");
        btnFavorieten = new Button("Favorieten");
        
        lMerk = new Label("Merk");
        tMerk = new TextField();
        tMerk.setEditable(false);
        
        lModel = new Label("Model");
        tModel = new TextField();
        tModel.setEditable(false);
        
        lBouwjaar = new Label("Bouwjaar");
        tBouwjaar = new TextField();
        tBouwjaar.setEditable(false);
        
        lCilinderinhoud = new Label("Cilinderinhoud");
        tCilinderinhoud = new TextField();
        tCilinderinhoud.setEditable(false);
        
        lWielbasis = new Label("Wielbasis");
        tWielbasis = new TextField();
        tWielbasis.setEditable(false);
        
        lBrandstof = new Label("Brandstof");
        tBrandstof = new TextField();
        tBrandstof.setEditable(false);
        
        btnLogUit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                hoofdStage.setScene(sWelkom);
            }
        
    });
        
        btnKenteken.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               String s = tKenteken.getText();
               
               if(s.isEmpty() == false){
                   jdb.setKenteken(s);
                   tMerk.setText(jdb.GetMerk());
                   tModel.setText(jdb.GetModel());
                   tBouwjaar.setText(jdb.GetBouwjaar());
                   tCilinderinhoud.setText(jdb.GetCilinderinhoud());
                   tWielbasis.setText(jdb.GetWielbasis());
                   tBrandstof.setText(jdb.GetBrandstof());
               }
               else{
                   lFout.setText("Vul een kenteken in!");
               }
            }
            
        });
        
        
        btnOpslaan.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String s = tKenteken.getText();
                int i = db.CheckFavorite(gebruiker.getGebruikerId(), s);
                
                if(s.isEmpty() == false){
                    if(i == 0){
                        db.AddFavorite(gebruiker.getGebruikerId(), s);
                        System.out.println("Gelukt");
                    }else{
                        lFout.setText("Deze auto staat al in je lijst");
                    }
                    
                }
                else{
                   lFout.setText("Vul een kenteken in!");
               }
            }
            
        });
        
        btnFavorieten.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Favorieten favorieten = new Favorieten(hoofdStage, sWelkom, gebruiker);
            }
        });
        
 
        
        
        
        
        
        
        gpHoofd.add(lWelkom, 1, 0);
        
        gpHoofd.add(lKenteken, 0, 1);
        gpHoofd.add(tKenteken, 2, 1);
        gpHoofd.add(lExtra, 0, 2);
        gpHoofd.add(lFout, 2, 2);
        gpHoofd.add(btnKenteken, 2, 3);
        
        gpHoofd.add(lMerk, 0, 4);
        gpHoofd.add(tMerk, 2, 4);
        
        gpHoofd.add(lModel, 0, 5);
        gpHoofd.add(tModel, 2, 5);
        
        gpHoofd.add(lBouwjaar, 0, 6);
        gpHoofd.add(tBouwjaar, 2, 6);
        
        gpHoofd.add(lCilinderinhoud, 0, 7);
        gpHoofd.add(tCilinderinhoud, 2, 7);
        
        gpHoofd.add(lWielbasis, 0, 8);
        gpHoofd.add(tWielbasis, 2, 8);
        
        gpHoofd.add(lBrandstof, 0 ,9);
        gpHoofd.add(tBrandstof, 2, 9);
        
        gpHoofd.add(btnLogUit, 0, 10);
        gpHoofd.add(btnOpslaan, 1, 10);
        gpHoofd.add(btnFavorieten, 2, 10);
        
        
        
        
        hoofdStage.setScene(hScene);
        hoofdStage.setTitle("Kenteken Applicatie");
        hoofdStage.show();
    
    }
         catch (SQLException ex) {
            Logger.getLogger(BeroepsProduct2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
