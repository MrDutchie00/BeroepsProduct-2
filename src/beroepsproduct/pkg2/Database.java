/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beroepsproduct.pkg2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author stanb
 */
public class Database {
    Connection con;
    Statement stm;
    ResultSet rset;
    
    
    public Database(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/beroepsproductkenteken?user=root&password=");
            stm = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }   
 }
   
    public String GetUsername(String sUsername, String sPassword){
        
        String SQLQuery = "SELECT * FROM Gebruiker WHERE username = '"+sUsername+"' AND password = '"+sPassword+"'";;
        try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    String username = rset.getString("username");
                    
                    return username;
                }
            }

        } catch (Exception e){

        }
        return "Error";
    
}
    
    public String GetPassword(String sUsername, String sPassword){
        
        String SQLQuery = "SELECT * FROM Gebruiker WHERE username = '"+sUsername+"' AND password = '"+sPassword+"'";;
        try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    String password = rset.getString("password");
                    
                    return password;
                }
            }

        } catch (Exception e){

        }
        return "Error";
    
}
    
    public int GetUserId(String sUsername, String sPassword){
        String SQLQuery = "SELECT GebruikerId FROM Gebruiker WHERE username = '"+sUsername+"' AND password = '"+sPassword+"'";;
        try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    int Id = rset.getInt("GebruikerId");
                    
                    return Id;
                }
            }

        } catch (Exception e){
         
        }
        
        String po = "error";
        return Integer.parseInt(po);
    }
    
    
    
    public int checkUser(String sUsername){
        String SQLQuery = "SELECT COUNT(username) AS hoeveel FROM Gebruiker WHERE username = '"+sUsername+"'";
        
         try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    int username = rset.getInt("hoeveel");
                    
                    return username;
                }
            }

        } catch (Exception e){

        }
        return 0;
    }
    
    public void createUser(int id, String sUsername, String sPassword, String sName){
        String SQLQuery = "INSERT INTO Gebruiker (GebruikerId, username, password, Name) VALUES ("+id+", '"+sUsername+"', '"+sPassword+"', '"+sName+"')";
        try {
            stm = con.createStatement();
            stm.execute(SQLQuery);
            System.out.println("User aangemaakt");
            
            
        }
        catch (Exception e){
            System.out.println("mislukt");
        }
        
    }
    
    public String getName(int iGebruikerId){
        String SQLQuery = "SELECT name FROM Gebruiker WHERE GebruikerId = "+iGebruikerId;
        
         try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    String name = rset.getString("name");
                    
                    return name;
                }
            }

        } catch (Exception e){

        }
        return "error";
    }
    
    public int getSize(){
        String SQLQuery = "SELECT COUNT(GebruikerId) AS GebruikerId FROM Gebruiker";
        
         try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    int gebruikerId = rset.getInt("GebruikerId");
                    
                    return gebruikerId;
                }
            }

        } catch (Exception e){

        }
        return 0;
    }
    
    public void AddFavorite(int id, String skenteken){
        String SQLQuery = "INSERT INTO GebruikerHeeftFavoriet (GebruikerId, kenteken) VALUES ("+id+", '"+skenteken+"')";
        try {
            stm = con.createStatement();
            stm.execute(SQLQuery);
            System.out.println("favoriet toegevoegd");
            
            
        }
        catch (Exception e){
            System.out.println("mislukt");
        }
        
    }
    
    public int CheckFavorite(int id, String sKenteken){
        
        String SQLQuery = "SELECT COUNT(kenteken) AS aantal FROM GebruikerHeeftFavoriet WHERE GebruikerId =" + id + " AND kenteken = '" + sKenteken+"'";
        
        try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    int aantal = rset.getInt("aantal");
                    
                    return aantal;
                }
            }

        } catch (Exception e){

        }
        return Integer.parseInt("error");
    }
    
    public ObservableList GeefFavorieten(int id){
       ObservableList<String> lijst = FXCollections.observableArrayList();
       String SQLQuery = "SELECT * FROM GebruikerHeeftFavoriet WHERE GebruikerId = "+id;
     
       System.out.println("Query begonnen");
       try {
            stm = con.createStatement();
            
            if(stm.execute(SQLQuery)){
                rset = stm.getResultSet();
                
                while(rset.next()){
                    lijst.add(rset.getString("kenteken"));
                }
            }

        } catch (Exception e){
            System.out.println("Niet gelukt");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
       return lijst;
    }
    
    public void VerwijderFavoriet(int Id, String sKenteken){
        String SQLQuery = "DELETE FROM GebruikerHeeftFavoriet WHERE GebruikerId = "+ Id+ " AND kenteken = '" + sKenteken + "'";
        
        try {
            stm = con.createStatement();
            stm.execute(SQLQuery);
            System.out.println("favoriet verwijderd");
            
            
        }
        catch (Exception e){
            System.out.println("mislukt");
        }
    }
    
}
