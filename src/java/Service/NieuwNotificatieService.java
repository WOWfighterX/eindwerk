/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ReadDAO;
import Model.Adres;
import Model.Functie;
import Model.Medewerker;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class NieuwNotificatieService {
    
    private List medewerkers;
    private ReadDAO dao;
    
    public NieuwNotificatieService(){
        dao = new ReadDAO();
        GenerateMedewerkers();
    }
    
    public String genMedewerkers(){
        String hulp = "<datalist id=\"medewerkerList\">\n";
        
        for(int i =0;i<(medewerkers.size());i++){
            Medewerker medewerker =  (Medewerker) medewerkers.get(i);
            
            hulp += "<option value=\""+ medewerker.getVoornaam()+" "+ medewerker.getFamilienaam() +"\">\n";
            
        }
        hulp += "</datalist><br>";
        
        return hulp;
    }
    
    public String genFuncties(){
        String hulp = "";
        for(int i=0;i<medewerkers.size();i++){
            
            Medewerker m =  (Medewerker) medewerkers.get(i);
            List functies = m.getFuncties();
            hulp += "<select id=\""+ m.getVoornaam()+" "+m.getFamilienaam() +"\" name=\""+m.getVoornaam()+" "+m.getFamilienaam()+"\">\n";
            
            for(int j=0;j<functies.size();j++){
                
                Functie f = (Functie) functies.get(j);
                hulp += "<option value=\""+ f.getFunctie() +"\">"+f.getFunctie()+"</option>";
            }
            
            hulp += "</select>";
            
        }
        return hulp;
    }
    
    private void GenerateMedewerkers(){
        
        medewerkers = new ArrayList();
        List list = dao.getMedewerkerList();
        
        for(int i = 0; i < list.size(); i++){
            
            String persoon = (String) list.get(i);
            int hulp;
            
            hulp = persoon.indexOf(' ');
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0,3));
            int maand = Integer.parseInt(g.substring(5,6));
            int dag = Integer.parseInt(g.substring(8,9));
            Date geboorte = new Date(jaar, maand, dag);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            hulp = persoon.indexOf(' ');
            straat += " "+persoon.substring(0, hulp);
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp+1);
            
            hulp = persoon.indexOf(' ');
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
