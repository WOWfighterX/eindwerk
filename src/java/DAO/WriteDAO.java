/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
                + "VALUES("+m.getMeldingID()+","+sfid+",'"+m.getType()+"','"+convertDate(m.getDatum())+"','"+m.getInfo()+"');");
        st.executeUpdate(sql);
        
        con.close();
    }
    
    public void addMedewerker(Medewerker m, String ww,String actief, int sfid, int accid, int fid, int sid, Adres adres, int aid)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("INSERT INTO Functie(FunctieID, Functienaam) "
                + "VALUES("+fid+",'"+m.getFuncties().get(0).getFunctie()+"');");
        st.executeUpdate(sql);
        
        String a = "NULL";
        if(actief.equals("true")){
            a="1";
        }
        
        sql = ("INSERT INTO Account(AccountID, Accountnaam, wachtwoord, actief) "
                + "VALUES("+accid+",'"+m.getVoornaam()+" "+m.getFamilienaam()+"','"+ww+"',"+a+");");
        st.executeUpdate(sql);
        
        sql = ("INSERT INTO Adres(AdresID, stad, straat, postcode) "
                + "VALUES("+aid+",'"+adres.getStad()+"','"+adres.getStraat()+"',"+adres.getPostcode()+");");
        st.executeUpdate(sql);
        
        sql = ("INSERT INTO Medewerker(Stamboeknr, voornaam, familienaam, Geboorte, email, AccountID, AdresID) "
                + "VALUES("+m.getStamboeknr()+",'"+m.getVoornaam()+"','"+m.getFamilienaam()+"','"+convertDate(m.getGeboorte())+"','"+m.getEmail()+"',"+accid+","+aid+");");
        st.executeUpdate(sql);
        
        sql = ("INSERT INTO Schoolfunctie(SchoolfunctieID, FunctieID, SchoolID, MedewerkerID) "
                + "VALUES("+sfid+","+fid+","+sid+","+m.getStamboeknr()+");");
        st.executeUpdate(sql);
        
        con.close();
    }
    
    public void addFunctie(Functie f, Medewerker m, int sfid, int fid, int sid)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("INSERT INTO Functie(FunctieID, Functienaam) "
                + "VALUES("+fid+",'"+f.getFunctie()+"');");
        st.executeUpdate(sql);
        
        sql = ("INSERT INTO Schoolfunctie(SchoolfunctieID, FunctieID, SchoolID, MedewerkerID) "
                + "VALUES("+sfid+","+fid+","+sid+","+m.getStamboeknr()+");");
        st.executeUpdate(sql);
        
        con.close();
    }
    
    public void removeFunctie(int mid, String fnaam)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("UPDATE Schoolfunctie sf, Functie f, Medewerker m "
                + "SET sf.actief = NULL "
                + "WHERE f.FunctieID = sf.FunctieID "
                + "and m.Stamboeknr = sf.MedewerkerID "
                + "and m.Stamboeknr = "+mid+" "
                + "and f.Functienaam = '"+fnaam+"';");
        
        st.executeUpdate(sql);
        
        con.close();
    }
    
    public void evaluatorVeranderen(int eid, String mvn, String mfn)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("UPDATE Schoolfunctie sf, Medewerker m "
                + "SET sf.Evaluator = null "
                + "where m.Stamboeknr = sf.MedewerkerID "
                + "and m.Stamboeknr = "+eid+" "
                + "and sf.Evaluator = 1;");
        
        st.executeUpdate(sql);
        
        sql = ("UPDATE Schoolfunctie sf, Medewerker m "
                + "SET sf.Evaluator = 1 "
                + "where m.Stamboeknr = sf.MedewerkerID "
                + "and m.Voornaam = '"+mvn+"' "
                + "and m.Familienaam = '"+mfn+"' "
                + "and sf.Evaluator IS NULL;");
        
        st.executeUpdate(sql);
        
        con.close();
        
    }
    
    public void rechtenVeranderen(String vn, String fn, String r)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("UPDATE Medewerker "
                + " SET admin " + r 
                + " WHERE Voornaam = '"+vn+"' "
                + " and Familienaam = '"+fn+"';");
        
        st.executeUpdate(sql);
        
        con.close();
    }
    
    public void schoolToevoegen(School s, int aid)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("INSERT INTO Adres(AdresID, Straat, Postcode, Stad) "
                + "VALUES("+aid+",'"+s.getAdres().getStraat()+"',"+s.getAdres().getPostcode()+",'"+s.getAdres().getStad()+"');");
        st.executeUpdate(sql);
        
        sql = ("INSERT INTO School(Instellingsnr, Schoolnaam, AdresID) "
                + "VALUES("+s.getInstellingsnr()+",'"+s.getSchoolnaam()+"',"+aid+");");
        st.executeUpdate(sql);
        
        con.close();
        
    }
    
    public void schoolVerwijderen(int sid)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("UPDATE School "
                + " SET actief = NULL"  
                + " WHERE Instellingsnr = "+sid+";");
        
        st.executeUpdate(sql);
        
        con.close();
        
    }
    
    
    
    public void addGesprek(Gesprek g, int sfid, int gid)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("INSERT INTO Gesprek(GesprekID, SchoolfunctieID, Datum, Status, GesprekType, Evaluator1ID, Evaluator2ID, Filepath) "
                + "VALUES("+gid+","+sfid+",'"+convertDate(g.getDatum())+"','"+g.getStatus()+"','"+g.getType()+"',"+g.getEvaluator1().getStamboeknr()+","+g.getEvaluator2().getStamboeknr()+",'"+g.getFilepath()+"');");
        st.executeUpdate(sql);
        
        con.close();
        
    }
    
    private java.sql.Date convertDate(Date d){
        return new java.sql.Date(d.getTime());
    }

    public void VeranderAccountStatus(Medewerker m, String str) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("UPDATE account a, medewerker m  "
                + " SET a.actief " + str 
                + " WHERE a.AccountID = m.AccountID "
                + " and m.Stamboeknr = "+m.getStamboeknr()+";");
        
        st.executeUpdate(sql);
        
        con.close();
        
    }

    public void veranderWachtwoord(String nieuw, int nr) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");
        
        Statement st = con.createStatement();
        String sql = ("UPDATE account a, medewerker m  "
                + " SET a.wachtwoord = " + nieuw+" " 
                + " WHERE a.AccountID = m.AccountID "
                + " and m.Stamboeknr = "+nr+";");
        
        st.executeUpdate(sql);
        
        con.close();
    }

    
}
