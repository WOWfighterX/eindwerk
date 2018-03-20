/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.MainDAO;
import Model.*;
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
public class MainPageService {
    
    private List medewerkers = new ArrayList();
    private List meldingen = new ArrayList();
    private MainDAO dao;
    
    public MainPageService(){
        dao = new MainDAO();
    }
    
    public String GetPage(){
        return GeneratePage();
    }
    
    private String GeneratePage(){
        
        String gen = "";
        try {
            GenerateMeldingen();
            GenerateMedewerkers();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainPageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainPageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainPageService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        for(int i = 0; i < medewerkers.size()+meldingen.size();i++){
            gen += "<a href=\"/";
            
            //link word later aangepast aan de schooldomain
            if(i<medewerkers.size()){
                Medewerker medewerker = (Medewerker) medewerkers.get(i);
                
                gen+="Evaluatie-war/Gesprekken";
                gen += "?param=";
                gen += medewerker.getVoornaam()+" "+medewerker.getFamilienaam();
                
            }else{
                Melding melding = (Melding) meldingen.get(i-medewerkers.size());
                Medewerker medewerker = (Medewerker) melding.getMedewerker();
                Functie functie = melding.getFunctie();
                
                gen+="Evaluatie-war/Melding";
                gen += "?param=";
                gen += medewerker.getVoornaam()+" "+medewerker.getFamilienaam()+"."+functie.getFunctie()+"."+melding.getDatum()+"."+melding.getInfo();
            }
            
            gen +="\">";
            
            //divs met de data en juiste klassen voor css
            gen += "<div class=\"";
            if(i<medewerkers.size()){
                gen+="werknemer";
            }else{
                gen+="notificatie";
            }
            gen += "\">\n";
            
            if(i<medewerkers.size()){
                Medewerker medewerker = (Medewerker) medewerkers.get(i);
                List functies = medewerker.getFuncties();
                
                gen+="<span>"+ medewerker.getVoornaam() + " " + medewerker.getFamilienaam() +"</span>\n";
                for(int j = 0; j<functies.size(); j++){
                    Functie f = (Functie) functies.get(j);
                    gen+="<span>"+f.getFunctie()+"</span>\n";
                }
            }else{
                Melding melding = (Melding) meldingen.get(i-medewerkers.size());
                Medewerker medewerker = (Medewerker) melding.getMedewerker();
                
                gen+="<span>"+ medewerker.getVoornaam() + " " + medewerker.getFamilienaam() +"</span>\n";
                gen+="<span>"+ melding.getType() +"</span>\n";
                gen+="<span>"+ melding.getDatum() +"</span>\n";
            }
            
            gen +="</div></a>\n";
        }
        
        return gen;
    }
    
    private void GenerateMeldingen() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List list = dao.getMeldingList();
        
        for(int i = 0; i < list.size(); i++){
            
            String persoon = (String) list.get(i);
            int hulp;
            
            hulp = persoon.indexOf('.');
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0,3));
            int maand = Integer.parseInt(g.substring(5,6));
            int dag = Integer.parseInt(g.substring(8,9));
            Date geboorte = new Date(jaar, maand, dag);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String stad = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String functie = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String d = persoon.substring(0, hulp);
            jaar = Integer.parseInt(g.substring(0,3));
            maand = Integer.parseInt(g.substring(5,6));
            dag = Integer.parseInt(g.substring(8,9));
            Date datum = new Date(jaar, maand, dag);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String t = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            String extra = persoon;
            
            Functie f = new Functie(functie);
            Adres a = new Adres(straat, stad, postcode);
            Persoon medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);
            Melding melding = new Melding(datum, extra, medewerker, f, t);
            
            meldingen.add(melding);
        }
        
    }
    
    private void GenerateMedewerkers()throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List list = dao.getMedewerkerList();
        
        for(int i = 0; i < list.size(); i++){
            
            String persoon = (String) list.get(i);
            int hulp;
            
            hulp = persoon.indexOf('.');
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0,3));
            int maand = Integer.parseInt(g.substring(5,6));
            int dag = Integer.parseInt(g.substring(8,9));
            Date geboorte = new Date(jaar, maand, dag);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf('.');
            String stad = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);

            String functie = persoon;
            
            Functie f = new Functie(functie);
            Adres a = new Adres(straat, stad, postcode);
            Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);
            
            int nummer = 0;
            for(int j=0;j<medewerkers.size();j++){
                Medewerker m = (Medewerker) medewerkers.get(j);
                Boolean b = !m.IsAanwezig(medewerker, f);
                if(b){
                    nummer++;
                }
            }
            
            if(nummer == medewerkers.size()){
                medewerkers.add(medewerker);
            }
        }
        
    }
    
}
