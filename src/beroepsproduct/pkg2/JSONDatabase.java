/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct.pkg2;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author stanb
 */
public class JSONDatabase {
    
  
    private String kenteken;

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public JSONDatabase() throws SQLException{
        
        }
    private URI uri;
    private URI uri2;
    
    public String GetMerk(){
            
                 String Merk = "";
                 try{
                    uri = new URI("https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken="+kenteken);
                    JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
                    JSONArray root = new JSONArray(tokener);

                    for (int i = 0; i < root.length(); ++i) {
                        JSONObject Json = root.getJSONObject(i);
                        Merk = Json.getString("merk");

                                                
                    }

                } catch (Exception e) {
                    System.out.println("kapot");
                }
                 return Merk;
             }
    
    public String GetModel(){
            
                 String model = "";
                 try{
                    URI uri = new URI("https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken="+kenteken);
                    JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
                    JSONArray root = new JSONArray(tokener);

                    for (int i = 0; i < root.length(); ++i) {
                        JSONObject Json = root.getJSONObject(i);
                        model = Json.getString("handelsbenaming");
                                                
                                               
                    }

                } catch (Exception e) {
                }
                 return model;
             }
    
    
    public String GetBouwjaar(){
            
                 String bouwjaar = "";
                 try{
                    URI uri = new URI("https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken="+kenteken);
                    JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
                    JSONArray root = new JSONArray(tokener);

                    for (int i = 0; i < root.length(); ++i) {
                        JSONObject Json = root.getJSONObject(i);
                        bouwjaar = Json.getString("datum_eerste_toelating");
                                                
                                               
                    }

                } catch (Exception e) {
                }
                 return bouwjaar;
             }
    
    public String GetWielbasis(){
            
                 String Wielbasis = "";
                 try{
                    URI uri = new URI("https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken="+kenteken);
                    JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
                    JSONArray root = new JSONArray(tokener);

                    for (int i = 0; i < root.length(); ++i) {
                        JSONObject Json = root.getJSONObject(i);
                        Wielbasis = Json.getString("wielbasis")+" cm";
                                                
                                               
                    }

                } catch (Exception e) {
                }
                 return Wielbasis;
             }
    
    public String GetCilinderinhoud(){
            
                 String Cilinderinhoud = "";
                 try{
                    URI uri = new URI("https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken="+kenteken);
                    JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
                    JSONArray root = new JSONArray(tokener);

                    for (int i = 0; i < root.length(); ++i) {
                        JSONObject Json = root.getJSONObject(i);
                        Cilinderinhoud = Json.getString("cilinderinhoud")+" cm³";
                                                
                                               
                    }

                } catch (Exception e) {
                }
                 return Cilinderinhoud;
             }
    
    public String GetBrandstof(){
        String brandstof = "";
                 try{
                    URI uri = new URI("https://opendata.rdw.nl/resource/8ys7-d773.json?kenteken="+kenteken);
                    JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
                    JSONArray root = new JSONArray(tokener);

                    for (int i = 0; i < root.length(); ++i) {
                        JSONObject Json = root.getJSONObject(i);
                        brandstof = Json.getString("brandstof_omschrijving");
                                                
                                               
                    }

                } catch (Exception e) {
                }
                 return brandstof;
    }
    
}
