/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
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
    
    ReadDAO dao;

    public WriteDAO() {
        dao = new ReadDAO();
    }
    
    private void addMelding(Melding m) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List meldingen = dao.getMeldingList();
        int ID = meldingen.size() + 1;
        List sf = dao.getSchoolFunctie();
        int sfid = sf.size() + 1;
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("INSERT INTO Melding(MeldingID, SchoolfunctieID, Type, Datum, Extra)"
                + "VALUES("+ID+","+sfid+","+m.getType()+","+m.getDatum()+","+m.getInfo()+");");
        ResultSet rs = st.executeQuery(sql);
        
        con.close();
    }
    
    private void addMedewerker(Medewerker m){
        
    }
    
    private void addFunctie(Functie f, Medewerker m){
        
    }
    
    private void removeFunctie(Medewerker m, Functie f){
        
    }
    
    private void evaluatorVeranderen(Medewerker evaluator, Medewerker medewerker){
        
    }
    
    private void rechtenVeranderen(Medewerker m, boolean recht){
        
    }
    
    private void schoolToevoegen(School s){
        
    }
    
    private void schoolVerwijderen(School s){
        
    }
    
    private void addGesprek(Gesprek g){
        
    }
}
