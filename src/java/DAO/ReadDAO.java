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
    private List adres;
    private List gesprekken;

    public ReadDAO() {
    }

    private void GetMedewerker(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        medewerker = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, sf.SchoolfunctieID, sf.actief "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and m.Stamboeknr = " + id + ";");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("Stamboeknr");
            str += "|" + rs.getString("Voornaam");
            str += "|" + rs.getString("Familienaam");
            str += "|" + rs.getString("Geboorte");
            str += "|" + rs.getString("Email");
            str += "|" + rs.getString("Straat");
            str += "|" + rs.getString("Postcode");
            str += "|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("SchoolfunctieID");
            String actief = rs.getString("Actief");
            if (actief != null && !actief.isEmpty()) {
                medewerker.add(str);
            }
        }
        con.close();

    }

    private void GetGesprekken(int mid, int sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        gesprekken = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select g.GesprekID, g.SchoolfunctieID, g.Datum, g.Status, g.GesprekType, g.Evaluator1ID, g.Evaluator2ID, g.Filepath, sf.FunctieID, sf.actief "
                + "from gesprek g, schoolfunctie sf "
                + "where sf.SchoolfunctieID = g.SchoolfunctieID "
                + "and sf.MedewerkerID = " + mid + " "
                + "and sf.SchoolID = "+sid+";");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("GesprekID");
            str += "|" + rs.getString("SchoolfunctieID");
            str += "|" + rs.getString("Datum");
            str += "|" + rs.getString("Status");
            str += "|" + rs.getString("GesprekType");
            str += "|" + rs.getString("Evaluator1ID");
            str += "|" + rs.getString("Evaluator2ID");
            str += "|" + rs.getString("Filepath");
            str += "|" + rs.getString("FunctieID");
            String actief = rs.getString("actief");
            if (actief != null && !actief.isEmpty()) {
                gesprekken.add(str);
            }
        }
        con.close();

    }

    private int GetMedewerkerID(String vn, String fn) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        medewerker = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.Stamboeknr "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and m.Voornaam = '" + vn + "' "
                + "and m.Familienaam = '" + fn + "';");
        ResultSet rs = st.executeQuery(sql);

        int id = 0;

        while (rs.next()) {

            id = Integer.parseInt(rs.getString("Stamboeknr"));
        }
        con.close();
        return id;
    }

    private List GetAccountID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        medewerker = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from account;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("AccountID"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private List GetFunctieID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from functie;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("FunctieID"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private List GetGesprekID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from gesprek;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("GesprekID"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private List GetAdresID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from adres;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("AdresID"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private List GetMedewerkerID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from medewerker;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("Stamboeknr"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private List GetMeldingID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from melding;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("MeldingID"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private List GetSchoolfunctieID() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select * "
                + " from schoolfunctie;");
        ResultSet rs = st.executeQuery(sql);

        List hulp = new ArrayList();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("SchoolfunctieID"));
            hulp.add(id);
        }
        con.close();
        return hulp;
    }

    private void getMedewerkers() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        medewerkers = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, sf.actief "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.evaluator IS NULL;");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("Stamboeknr");
            str += "|" + rs.getString("Voornaam");
            str += "|" + rs.getString("Familienaam");
            str += "|" + rs.getString("Geboorte");
            str += "|" + rs.getString("Email");
            str += "|" + rs.getString("Straat");
            str += "|" + rs.getString("Postcode");
            str += "|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("Actief");
            medewerkers.add(str);
        }
        con.close();
    }

    private void GetEvaluatoren(int sid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        evaluatoren = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, sf.actief "
                + "from medewerker m, functie f, schoolfunctie sf, adres a "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.evaluator = 1 "
                + "and sf.SchoolID = " + sid + ";");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("Stamboeknr");
            str += "|" + rs.getString("Voornaam");
            str += "|" + rs.getString("Familienaam");
            str += "|" + rs.getString("Geboorte");
            str += "|" + rs.getString("Email");
            str += "|" + rs.getString("Straat");
            str += "|" + rs.getString("Postcode");
            str += "|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("Actief");
            evaluatoren.add(str);
        }
        con.close();
    }

    private void getMeldingen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

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
            str += "|" + rs.getString("Voornaam");
            str += "|" + rs.getString("Familienaam");
            str += "|" + rs.getString("Geboorte");
            str += "|" + rs.getString("Email");
            str += "|" + rs.getString("Straat");
            str += "|" + rs.getString("Postcode");
            str += "|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("Datum");
            str += "|" + rs.getString("Type");
            str += "|" + rs.getString("Extra");
            str += "|" + rs.getString("MeldingID");

            meldingen.add(str);
        }
        con.close();
    }

    private void GetMelding(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select m.stamboeknr, m.voornaam, m.familienaam, m.geboorte, m.email, a.straat, a.postcode, a.stad, f.functienaam, me.datum, me.type, me.extra, me.meldingID "
                + "from medewerker m, functie f, schoolfunctie sf, adres a, melding me "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and a.AdresID = m.AdresID "
                + "and sf.SchoolfunctieID = me.SchoolfunctieID "
                + "and me.meldingID=" + id + ";");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("Stamboeknr");
            str += "|" + rs.getString("Voornaam");
            str += "|" + rs.getString("Familienaam");
            str += "|" + rs.getString("Geboorte");
            str += "|" + rs.getString("Email");
            str += "|" + rs.getString("Straat");
            str += "|" + rs.getString("Postcode");
            str += "|" + rs.getString("Stad");
            str += "|" + rs.getString("Functienaam");
            str += "|" + rs.getString("Datum");
            str += "|" + rs.getString("Type");
            str += "|" + rs.getString("Extra");
            str += "|" + rs.getString("MeldingID");
            melding = str;
        }

        con.close();
    }

    private void GetScholen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        scholen = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select s.Instellingsnr, s.Schoolnaam, a.Straat, a.Postcode, a.Stad, s.actief\n"
                + "from school s, adres a\n"
                + "where a.AdresID=s.AdresID;");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String str = rs.getString("Instellingsnr");
            str += "|" + rs.getString("Schoolnaam");
            str += "|" + rs.getString("Straat");
            str += "|" + rs.getString("Postcode");
            str += "|" + rs.getString("Stad");

            String actief = rs.getString("actief");
            if (actief != null && !actief.isEmpty()) {
                scholen.add(str);
            }
        }
        con.close();
    }

    private void GetSchool(int nr) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select s.Instellingsnr, s.Schoolnaam, a.Straat, a.Postcode, a.Stad\n"
                + "from school s, adres a\n"
                + "where a.AdresID=s.AdresID\n"
                + "and s.Instellingsnr=" + nr + ";");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            school = rs.getString("Instellingsnr");
            school += "|" + rs.getString("Schoolnaam");
            school += "|" + rs.getString("Straat");
            school += "|" + rs.getString("Postcode");
            school += "|" + rs.getString("Stad");
        }
        con.close();
    }

    private void GetGebruiker(int nr) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        gebruikers = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select a.Accountnaam, a.Wachtwoord, m.admin, sf.evaluator, m.Stamboeknr, a.actief\n"
                + "from account a, medewerker m, schoolfunctie sf, school s\n"
                + "where a.AccountID = m.AccountID\n"
                + "and m.Stamboeknr = sf.MedewerkerID\n"
                + "and s.Instellingsnr = sf.SchoolID\n"
                + "and s.Instellingsnr = " + nr + ";");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String gebruiker = rs.getString("Accountnaam");
            gebruiker += "|" + rs.getString("Wachtwoord");
            gebruiker += "|" + rs.getString("admin");
            gebruiker += "|" + rs.getString("evaluator");
            gebruiker += "|" + rs.getString("Stamboeknr");
            String actief = rs.getString("actief");
            if (actief != null && !actief.isEmpty()) {
                gebruikers.add(gebruiker);
            }
        }
        con.close();
    }

    private void GetSchoolFunctie() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

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

    private int GetSchoolFunctie(String mvn, String mfn, String functie) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select sf.schoolfunctieid "
                + "from schoolfunctie sf, medewerker m, functie f "
                + "where m.stamboeknr = sf.MedewerkerID "
                + "and f.FunctieID = sf.FunctieID "
                + "and m.Voornaam = '" + mvn + "' "
                + "and m.Familienaam = '" + mfn + "' "
                + "and f.Functienaam = '" + functie + "';");
        ResultSet rs = st.executeQuery(sql);

        int hulp = 0;

        while (rs.next()) {

            String sf = rs.getString("SchoolFunctieID");
            hulp = Integer.parseInt(sf);
        }

        con.close();

        return hulp;
    }

    private void GetAdressen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        adres = new ArrayList();

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gesprekken_db", "admin", "admin123");

        Statement st = con.createStatement();
        String sql = ("select AdresID from Adres;");
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            String ad = rs.getString("AdresID");
            adres.add(ad);
        }
        con.close();
    }

    public List getMedewerkerList() {
        try {
            getMedewerkers();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medewerkers;
    }

    public List getMeldingList() {
        try {
            getMeldingen();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meldingen;
    }

    public List getMedewerker(int id) {
        try {
            GetMedewerker(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medewerker;
    }

    public List getScholen() {
        try {
            GetScholen();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scholen;
    }

    public String getSchool(int nr) {
        try {
            GetSchool(nr);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return school;
    }

    public List getGebruiker(int nr) {
        try {
            GetGebruiker(nr);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gebruikers;
    }

    public String getMelding(int id) {
        try {
            GetMelding(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return melding;
    }

    public List getEvaluatoren(int sid) {
        try {
            GetEvaluatoren(sid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evaluatoren;
    }

    public List getSchoolFunctie() {
        try {
            GetSchoolFunctie();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return schoolfunctie;
    }

    public int getMedewerkerID(String vn, String fn) {
        int id = 0;
        try {
            id = GetMedewerkerID(vn, fn);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public int getAccountID() {
        List hulp = new ArrayList();

        try {
            hulp = GetAccountID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int accid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (accid == test) {
                accid++;
                i = 0;
            }
        }

        return accid;
    }

    public int getFunctieID() {
        List hulp = new ArrayList();

        try {
            hulp = GetFunctieID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int fid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (fid == test) {
                fid++;
                i = 0;
            }
        }

        return fid;
    }

    public List getAdressen() {
        try {
            GetAdressen();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adres;
    }

    public int getGesprekID() {
        List hulp = new ArrayList();

        try {
            hulp = GetGesprekID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int gid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (gid == test) {
                gid++;
                i = 0;
            }
        }

        return gid;
    }

    public int getSchoolFunctie(String mvn, String mfn, String functie) {
        int hulp = 0;
        try {
            hulp = GetSchoolFunctie(mvn, mfn, functie);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hulp;
    }

    public int getAdresID() {
        List hulp = new ArrayList();

        try {
            hulp = GetAdresID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int aid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (aid == test) {
                aid++;
                i = 0;
            }
        }

        return aid;
    }

    public int getMedewerkerID() {
        List hulp = new ArrayList();

        try {
            hulp = GetMedewerkerID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int mid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (mid == test) {
                mid++;
                i = 0;
            }
        }

        return mid;
    }

    public int getMeldingID() {
        List hulp = new ArrayList();

        try {
            hulp = GetMeldingID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int meid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (meid == test) {
                meid++;
                i = 0;
            }
        }

        return meid;
    }

    public int getSchoolFunctieID() {
        List hulp = new ArrayList();

        try {
            hulp = GetSchoolfunctieID();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int sfid = 1;
        for (int i = 0; i < hulp.size(); i++) {
            int test = (int) hulp.get(i);
            if (sfid == test) {
                sfid++;
                i = 0;
            }
        }

        return sfid;
    }

    public List getGesprekken(int mid, int sid) {
        try {
            GetGesprekken(mid, sid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gesprekken;
    }
}
