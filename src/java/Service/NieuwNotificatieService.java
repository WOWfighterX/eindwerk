/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.*;
import Model.Adres;
import Model.Functie;
import Model.Medewerker;
import Model.Melding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron gevers
 */
public class NieuwNotificatieService {
    
    private List medewerkers;
    private ReadDAO rdao;
    private WriteDAO wdao;
    
    public NieuwNotificatieService(){
        rdao = new ReadDAO();
        wdao = new WriteDAO();
        GenerateMedewerkers();
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
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0, 3));
            int maand = Integer.parseInt(g.substring(5, 6));
            int dag = Integer.parseInt(g.substring(8, 9));
            Date geboorte = new Date(jaar, maand, dag);
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
                Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);

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

    public void addMelding(int mid, String fnaam, String type, String extra, Date d) {
        
        int meid = rdao.getMeldingID();
        
        
        Medewerker m = GenerateMedewerker(mid);
        Functie f = new Functie(fnaam);
        
        Melding melding = new Melding(d, extra, m, f, type, meid);
        
        int sfid = rdao.getSchoolFunctie(m.getVoornaam(), m.getFamilienaam(), f.getFunctie());
        
        try {
            wdao.addMelding(melding, sfid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(NieuwNotificatieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private Medewerker GenerateMedewerker(int mid){
        List werknemer = new ArrayList();
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
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0, 3));
            int maand = Integer.parseInt(g.substring(5, 6));
            int dag = Integer.parseInt(g.substring(8, 9));
            Date geboorte = new Date(jaar, maand, dag);
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
                Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);

                //kijken of de medewerker al bestaat. al hij al bestaat word de functie toegevoegd aan zijn functielijst.
                int nummer = 0;
                for (int j = 0; j < werknemer.size(); j++) {
                    Medewerker m = (Medewerker) werknemer.get(j);
                    Boolean b = !m.IsAanwezig(medewerker, f);
                    if (b) {
                        nummer++;
                    }
                }

                if (nummer == werknemer.size()) {
                    werknemer.add(medewerker);
                }
            }
        }
        
        return (Medewerker) werknemer.get(0);
    }
    
    
}
