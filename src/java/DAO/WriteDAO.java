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
    
    private void addMelding(String sfid, String t, String d, String e) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List meldingen = dao.getMeldingList();
        int ID = meldingen.size() + 1;
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("INSERT INTO Melding(MeldingID, SchoolfunctieID, Type, Datum, Extra)"
                + "VALUES("+ID+","+sfid+","+t+","+d+","+e+");");
        ResultSet rs = st.executeQuery(sql);
        
        con.close();
    }
}
