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
public class MainDAO {
    
    List medewerkers;
    List meldingen;
    
    public MainDAO(){
        medewerkers = new ArrayList();
        meldingen = new ArrayList();
    }
    
    private void getMedewerkers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Stamboeknr");
            str +=" " + rs.getString("Voornaam");
            str +=" " + rs.getString("Familienaam");
            str +=" " + rs.getString("Geboorte");
            str +=" " + rs.getString("Email");
            str +=" " + rs.getString("Straat");
            str +=" " + rs.getString("Postcode");
            str +=" " + rs.getString("Stad");
            str += " " + rs.getString("Functienaam");
            medewerkers.add(str);
        }
        con.close();
    }
    
    private void getMeldingen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, me.datum, me.type, me.extra "
                + "from medewerker m, functie f, schoolfunctie sf, adres a, melding me "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.SchoolfunctieID = me.SchoolfunctieID;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Stamboeknr");
            str +=" " + rs.getString("Voornaam");
            str +=" " + rs.getString("Familienaam");
            str +=" " + rs.getString("Geboorte");
            str +=" " + rs.getString("Email");
            str +=" " + rs.getString("Straat");
            str +=" " + rs.getString("Postcode");
            str +=" " + rs.getString("Stad");
            str += " " + rs.getString("Functienaam");
            str += " " + rs.getString("Datum");
            str += " " + rs.getString("Type");
            str += " " + rs.getString("Extra");
            meldingen.add(str);
        }
        con.close();
    }
    
    public List getMedewerkerList() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        getMedewerkers();
        return medewerkers;
    }
    
    public List getMeldingList() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        getMeldingen();
        return meldingen;
    }
}
