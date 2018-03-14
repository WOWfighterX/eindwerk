/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class MainPageDAO {
    
    List list;
    
    public MainPageDAO(){
        list = new ArrayList();
    }
    
    private void getMedewerkers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.voornaam, m.familienaam, f.functienaam "
                
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Voornaam");
            str +=" " + rs.getString("Familienaam");
            list.add(str);
            str = rs.getString("Functienaam");
            list.add(str);
        }
        con.close();
    }
    
    public List getList() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        getMedewerkers();
        return list;
    }
}
