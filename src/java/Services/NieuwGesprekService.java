/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.ReadDAO;
import DAO.WriteDAO;
import Model.Adres;
import Model.*;
import Servlets.PersToev;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron gevers
 */
public class NieuwGesprekService {

    private List medewerkers;
    private List evaluatoren;
    private ReadDAO rdao;
    private WriteDAO wdao;
    
    public NieuwGesprekService(){
        rdao = new ReadDAO();
        wdao = new WriteDAO();
        GenerateMedewerkers();
    }
    
    public void addGesprek(String medewerker, String functie, String type, String datum, String uploadPath, Gebruiker gebr, String status) {
        
        int mid = Integer.parseInt(medewerker);
        
        Medewerker m = getMedewerker(mid);
        
        genEvaluatoren(gebr.getSchool().getInstellingsnr());
        Medewerker evaluator1 = (Medewerker) evaluatoren.get(0);
        Medewerker evaluator2 = (Medewerker) evaluatoren.get(1);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(datum);
        } catch (ParseException ex) {
            Logger.getLogger(PersToev.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Gesprek g = new Gesprek(m,gebr.getSchool(), date, status, type, evaluator1, evaluator2, uploadPath);
        
        int gid = rdao.getGesprekID();
        int sfid = rdao.getSchoolFunctie(m.getVoornaam(), m.getFamilienaam(), functie);
        
        try {
            wdao.addGesprek(g,sfid, gid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(NieuwGesprekService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String generateSelectMedewerkers() {
        
        String hulp = "<select id=\"medewerkerSelect\" name=\"medewerker\">";
        hulp += "<option value=\"\">Kies een medewerker...</option>";
        
        for(int i = 0; i<medewerkers.size();i++){
            Medewerker medewerker =  (Medewerker) medewerkers.get(i);
            hulp += "<option value=\""+ medewerker.getStamboeknr() +"\">"+ medewerker.getVoornaam()+" "+medewerker.getFamilienaam() +"</option>";
        }
        hulp += "</select>";
        
        return hulp;
        
    }
    
    public String generateFuncties() {
        
        String hulp = "<select id=\"functies\" name=\"functie\">\n"
                + "<option value=\"\">Kies functie...</option>";
        
        for(int i=0;i<medewerkers.size();i++){
            Medewerker m = (Medewerker) medewerkers.get(i);
            List functies = m.getFuncties();
            
            for(int j=0;j<functies.size();j++){
                Functie f = (Functie) functies.get(j);
                hulp += "<option class=\""+m.getStamboeknr()+"\" value=\""+f.getFunctie()+"\">"+f.getFunctie()+"</option>";
            }
        }
        
        hulp += "</select>";
        return hulp;
    }
    
    private void GenerateMedewerkers(){
        medewerkers = new ArrayList();
        List list = rdao.getMedewerkerList();

        for (int i = 0; i < list.size(); i++) {

            String persoon = (String) list.get(i);
            int hulp;

            hulp = persoon.indexOf("|");
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String stad = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String functie = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            
            if (!persoon.equals("null")) {
                
                int actief = Integer.parseInt(persoon);
                Functie f = new Functie(functie);
                Adres a = new Adres(straat, stad, postcode);
                Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, email, a, f);

                //kijken of de medewerker al bestaat. al hij al bestaat word de functie toegevoegd aan zijn functielijst.
                int nummer = 0;
                for (int j = 0; j < medewerkers.size(); j++) {
                    Medewerker m = (Medewerker) medewerkers.get(j);
                    Boolean b = !m.IsAanwezig(medewerker, f);
                    if (b) {
                        nummer++;
                    }
                }

                if (nummer == medewerkers.size()) {
                    medewerkers.add(medewerker);
                }
            }
        }
    }
    
    private void genEvaluatoren(int sid) {
        evaluatoren = new ArrayList();
        List list = rdao.getEvaluatoren(sid);

        for (int i = 0; i < list.size(); i++) {

            String persoon = (String) list.get(i);
            int hulp;

            hulp = persoon.indexOf("|");
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String stad = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String functie = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            int actief = Integer.parseInt(persoon);

            if (actief == 1) {

                Functie f = new Functie(functie);
                Adres a = new Adres(straat, stad, postcode);
                Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, email, a, f);

                //kijken of de medewerker al bestaat. al hij al bestaat word de functie toegevoegd aan zijn functielijst.
                int nummer = 0;
                for (int j = 0; j < evaluatoren.size(); j++) {
                    Medewerker m = (Medewerker) evaluatoren.get(j);
                    Boolean b = !m.IsAanwezig(medewerker, f);
                    if (b) {
                        nummer++;
                    }
                }

                if (nummer == evaluatoren.size()) {
                    evaluatoren.add(medewerker);
                }
            }
        }
    }

    private Medewerker getMedewerker(int mid){
        List mw = new ArrayList();
        List list = rdao.getMedewerker(mid);

        for (int i = 0; i < list.size(); i++) {

            String persoon = (String) list.get(i);
            int hulp;

            hulp = persoon.indexOf("|");
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String stad = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String functie = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            
            if (!persoon.equals("null")) {
                
                int actief = Integer.parseInt(persoon);
                Functie f = new Functie(functie);
                Adres a = new Adres(straat, stad, postcode);
                Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, email, a, f);

                //kijken of de medewerker al bestaat. al hij al bestaat word de functie toegevoegd aan zijn functielijst.
                int nummer = 0;
                for (int j = 0; j < mw.size(); j++) {
                    Medewerker m = (Medewerker) mw.get(j);
                    Boolean b = !m.IsAanwezig(medewerker, f);
                    if (b) {
                        nummer++;
                    }
                }

                if (nummer == mw.size()) {
                    mw.add(medewerker);
                }
            }
        }
        
        return (Medewerker) mw.get(0);
    }
    
}
