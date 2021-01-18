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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



/**
 *
 * @author stanb
 */
public class Favorieten {
    Database db;
    GridPane gpFav;
    Scene fScene;
    
    
    Label lFout;
    Label lMerk;
    Label lModel;
    Label lBouwjaar;
    Label lCilinderinhoud;
    Label lWielbasis;
    Label lBrandstof;
    
    
    TextField tMerk;
    TextField tModel;
    TextField tBouwjaar;
    TextField tCilinderinhoud;
    TextField tWielbasis;
    TextField tBrandstof;
    
    ComboBox cb;
    
    
    Button btnTerug;
    Button btnVerwijder;
   
   
    public Favorieten(Stage favStage, Scene sWelkom, Gebruiker gebruiker){
        try{
        db = new Database();
        JSONDatabase jdb = new JSONDatabase();
        gpFav = new GridPane();
        fScene = new Scene(gpFav, 800, 500);
        
        gpFav.setVgap(10);
        gpFav.setHgap(10);
        gpFav.setAlignment(Pos.CENTER);
        
        lFout = new Label("");
        
        cb = new ComboBox();
        cb.setItems(db.GeefFavorieten(gebruiker.getGebruikerId()));
        
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
        
        btnTerug = new Button("Terug");
        btnVerwijder = new Button("Verwijder Favoriet");
        
        
        cb.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String s = cb.getValue().toString();
               
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
        
        btnTerug.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Hoofdscherm hoofdscherm = new Hoofdscherm(favStage, sWelkom, gebruiker);
            }
        });
        
        btnVerwijder.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String s = cb.getValue().toString();
                cb.setValue("");
                db.VerwijderFavoriet(gebruiker.getGebruikerId(), s);
               
               tMerk.setText("");
               tModel.setText("");
               tBouwjaar.setText("");
               tCilinderinhoud.setText("");
               tWielbasis.setText("");
               tBrandstof.setText("");
               cb.setItems(db.GeefFavorieten(gebruiker.getGebruikerId()));
            }
            
        });
        
        
        gpFav.add(cb, 1, 0);
        
        gpFav.add(lMerk, 0, 2);
        gpFav.add(tMerk, 2, 2);
        
        gpFav.add(lModel, 0, 3);
        gpFav.add(tModel, 2, 3);
        
        gpFav.add(lBouwjaar, 0, 4);
        gpFav.add(tBouwjaar, 2, 4);
        
        gpFav.add(lCilinderinhoud, 0, 5);
        gpFav.add(tCilinderinhoud, 2, 5);
        
        gpFav.add(lWielbasis, 0, 6);
        gpFav.add(tWielbasis, 2, 6);
        
        gpFav.add(lBrandstof, 0 ,7);
        gpFav.add(tBrandstof, 2, 7);
        
        gpFav.add(btnTerug, 0, 8);
        
        gpFav.add(btnVerwijder, 2, 8);
        
        
        
        favStage.setScene(fScene);
        favStage.setTitle("Favorieten lijst");
        favStage.show();
        
        }
        catch (SQLException ex) {
            Logger.getLogger(BeroepsProduct2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
    }
    
}
