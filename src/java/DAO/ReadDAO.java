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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron gevers
 */
public class ReadDAO {
    
    private List medewerkers;
    private List meldingen;
    private List medewerker;
    private List scholen;
    private String school;
    private List gebruikers;
    private String melding;
    private List evaluatoren;
    private List schoolfunctie;
    
    public ReadDAO(){
    }
    
    private void GetMedewerker(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        medewerker = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, sf.SchoolfunctieID "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID"
                + "and m.MedewerkerID = "+id+";");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Stamboeknr");
            str +="|" + rs.getString("Voornaam");
            str +="|" + rs.getString("Familienaam");
            str +="|" + rs.getString("Geboorte");
            str +="|" + rs.getString("Email");
            str +="|" + rs.getString("Straat");
            str +="|" + rs.getString("Postcode");
            str +="|" + rs.getString("Stad");
            str +="|" + rs.getString("Functienaam");
            str +="|" + rs.getString("SchoolfunctieID");
            medewerker.add(str);
        }
        con.close();
        
    }
    
    private int GetMedewerkerID(String vn, String fn) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        medewerker = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID"
                + "and m.Voornaam = "+vn+" "
                + "and m.Familianaam = "+fn+";");
        ResultSet rs = st.executeQuery(sql);
        
        int id = 0;
        
        while (rs.next()) {
            
            id = Integer.parseInt(rs.getString("Stamboeknr"));
        }
        con.close();
        return id;
    }
    
    private void getMedewerkers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        medewerkers = new ArrayList();
        
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
            str +="|" + rs.getString("Voornaam");
            str +="|" + rs.getString("Familienaam");
            str +="|" + rs.getString("Geboorte");
            str +="|" + rs.getString("Email");
            str +="|" + rs.getString("Straat");
            str +="|" + rs.getString("Postcode");
            str +="|" + rs.getString("Stad");
            str +="|" + rs.getString("Functienaam");
            medewerkers.add(str);
        }
        con.close();
    }
    
    private void GetEvaluatoren() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        evaluatoren = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.evaluator = 1;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Stamboeknr");
            str +="|" + rs.getString("Voornaam");
            str +="|" + rs.getString("Familienaam");
            str +="|" + rs.getString("Geboorte");
            str +="|" + rs.getString("Email");
            str +="|" + rs.getString("Straat");
            str +="|" + rs.getString("Postcode");
            str +="|" + rs.getString("Stad");
            str +="|" + rs.getString("Functienaam");
            evaluatoren.add(str);
        }
        con.close();
    }
    
    private void getMeldingen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        meldingen = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, me.datum, me.type, me.extra, me.meldingID "
                + "from medewerker m, functie f, schoolfunctie sf, adres a, melding me "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.SchoolfunctieID = me.SchoolfunctieID;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Stamboeknr");
            str +="|" + rs.getString("Voornaam");
            str +="|" + rs.getString("Familienaam");
            str +="|" + rs.getString("Geboorte");
            str +="|" + rs.getString("Email");
            str +="|" + rs.getString("Straat");
            str +="|" + rs.getString("Postcode");
            str +="|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("Datum");
            str += "|" + rs.getString("Type");
            str += "|" + rs.getString("Extra");
            str += "|" + rs.getString("MeldingID");
            meldingen.add(str);
        }
        con.close();
    }
    
    private void GetMelding(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, me.datum, me.type, me.extra, me.meldingID "
                + "from medewerker m, functie f, schoolfunctie sf, adres a, melding me "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.SchoolfunctieID = me.SchoolfunctieID "
                + "and me.meldingID="+id+";");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Stamboeknr");
            str +="|" + rs.getString("Voornaam");
            str +="|" + rs.getString("Familienaam");
            str +="|" + rs.getString("Geboorte");
            str +="|" + rs.getString("Email");
            str +="|" + rs.getString("Straat");
            str +="|" + rs.getString("Postcode");
            str +="|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("Datum");
            str += "|" + rs.getString("Type");
            str += "|" + rs.getString("Extra");
            str += "|" + rs.getString("MeldingID");
            melding = str;
        }
        
        con.close();
    }
    
    private void GetScholen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        scholen = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select s.Instellingsnr, s.Schoolnaam, a.Straat, a.Postcode, a.Stad\n"
                   + "from school s, adres a\n" 
                   + "where a.AdresID=s.AdresID;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String str = rs.getString("Instellingsnr");
            str +="|" + rs.getString("Schoolnaam");
            str +="|" + rs.getString("Straat");
            str +="|" + rs.getString("Postcode");
            str +="|" + rs.getString("Stad");
            scholen.add(str);
        }
        con.close();
    }
    
    private void GetSchool(int nr) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select s.Instellingsnr, s.Schoolnaam, a.Straat, a.Postcode, a.Stad\n"
                   + "from school s, adres a\n" 
                   + "where a.AdresID=s.AdresID\n"
                   + "and s.Instellingsnr="+nr+";");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            school = rs.getString("Instellingsnr");
            school +="|" + rs.getString("Schoolnaam");
            school +="|" + rs.getString("Straat");
            school +="|" + rs.getString("Postcode");
            school +="|" + rs.getString("Stad");
        }
        con.close();
    }
    
    private void GetGebruiker(int nr) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        gebruikers = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select a.Accountnaam, a.Wachtwoord\n"
                   + "from account a, medewerker m, schoolfunctie sf, school s\n"
                   + "where a.AccountID = m.AccountID\n" 
                   + "and m.Stamboeknr = sf.MedewerkerID\n" 
                   + "and s.Instellingsnr = sf.SchoolID\n"
                   + "and s.Instellingsnr = "+nr+";");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String gebruiker = rs.getString("Accountnaam");
            gebruiker +="|" + rs.getString("Wachtwoord");
            gebruikers.add(gebruiker);
        }
        con.close();
    }
    
    private void GetSchoolFunctie() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        schoolfunctie = new ArrayList();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select schoolfunctieid from schoolfunctie;");
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            
            String sf = rs.getString("SchoolFunctieID");
            schoolfunctie.add(sf);
        }
        con.close();
    }
    
    public List getMedewerkerList(){
        try {
            getMedewerkers();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medewerkers;
    }
    
    public List getMeldingList(){
        try {
            getMeldingen();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meldingen;
    }

    public List getMedewerker(int id) {
        try {
            GetMedewerker(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medewerker;
    }
    
    public List getScholen(){
        try {
            GetScholen();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scholen;
    }
    
    public String getSchool(int nr){
        try {
            GetSchool(nr);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return school;
    }
    
    public List getGebruiker(int nr){
        try {
            GetGebruiker(nr);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gebruikers;
    }
    
    public String getMelding(int id){
        try {
            GetMelding(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return melding;
    }
    
    public List getEvaluatoren(){
        try {
            GetEvaluatoren();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evaluatoren;
    }
    
    public List getSchoolFunctie(){
        try {
            GetSchoolFunctie();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evaluatoren;
    }

    public int getMedewerkerID(String vn, String fn) {
        int id = 0;
        try {
            id = GetMedewerkerID(vn,fn);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
}
