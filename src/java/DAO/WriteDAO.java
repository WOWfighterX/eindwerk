/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import Service.MainPageService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class WriteDAO {
    

    public WriteDAO() {
    }
    
    public void addMelding(Melding m, int sfid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("INSERT INTO Melding(MeldingID, SchoolfunctieID, Type, Datum, Extra)"
                + "VALUES("+m.getMeldingID()+","+sfid+","+m.getType()+","+m.getDatum()+","+m.getInfo()+");");
        ResultSet rs = st.executeQuery(sql);
        
        con.close();
    }
    
    public void addMedewerker(Medewerker m){
        
    }
    
    public void addFunctie(Functie f, Medewerker m){
        
    }
    
    public void removeFunctie(Medewerker m, Functie f){
        
    }
    
    public void evaluatorVeranderen(Medewerker evaluator, Medewerker medewerker){
        
    }
    
    public void rechtenVeranderen(Medewerker m, boolean recht){
        
    }
    
    public void schoolToevoegen(School s){
        
    }
    
    public void schoolVerwijderen(School s){
        
    }
    
    public void addGesprek(Gesprek g){
        
    }
}
