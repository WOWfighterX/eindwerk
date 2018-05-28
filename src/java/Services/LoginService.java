/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.ReadDAO;
import Model.Adres;
import Model.Gebruiker;
import Model.School;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class LoginService {

    private List scholen = new ArrayList();
    private ReadDAO dao;

    public LoginService() {
        dao = new ReadDAO();
    }

    public Gebruiker getGebruiker(int nr, String naam, String ww) {

        School school = generateSchool(nr);
        Gebruiker gebruiker = new Gebruiker(school, naam, ww);
        return gebruiker;

    }

    public boolean checkGebruiker(Gebruiker gebruiker) {

        List gebruikers = dao.getGebruiker(gebruiker.getSchool().getInstellingsnr());
        int hulp;
        boolean login = false;
        
        gebruiker.setAdmin(false);
        gebruiker.setEvaluator(false);

        for (int i = 0; i < gebruikers.size(); i++) {
            String user = (String) gebruikers.get(i);
            
            hulp = user.indexOf("|");
            String naam = user.substring(0, hulp);
            user = user.substring(hulp + 1);
            
            hulp = user.indexOf("|");
            String ww = user.substring(0, hulp);
            user = user.substring(hulp + 1);
            
            hulp = user.indexOf("|");
            String admin = user.substring(0, hulp);
            user = user.substring(hulp + 1);
            
            hulp = user.indexOf("|");
            String evaluator = user.substring(0, hulp);
            user = user.substring(hulp + 1);
            
            int stamboeknr = Integer.parseInt(user);
            gebruiker.setStamboeknr(stamboeknr);
            
            if(gebruiker.getGebruikersnaam().equals(naam)){
                if(gebruiker.getWachtwoord().equals(ww)){
                    login = true;
                    if(!admin.equals("null")){
                        gebruiker.setAdmin(true);
                    }
                    if(!evaluator.equals("null")){
                        gebruiker.setEvaluator(true);
                    }
                }
            }
            
        }
        
        return login;
    }

    public String getScholen() {

        generateScholen();

        String hulp = "<select id=\"school\" name=\"School\">";

        for (int i = 0; i < scholen.size(); i++) {

            School school = (School) scholen.get(i);
            hulp += "<option value=\"" + school.getInstellingsnr() + "\">" + school.getSchoolnaam() + "</option>";

        }

        hulp += "</select>";
        return hulp;
    }

    private void generateScholen() {
        List list = dao.getScholen();

        for (int i = 0; i < list.size(); i++) {

            String school = (String) list.get(i);
            int hulp;

            hulp = school.indexOf("|");
            int nr = Integer.parseInt(school.substring(0, hulp));
            school = school.substring(hulp + 1);

            hulp = school.indexOf("|");
            String naam = school.substring(0, hulp);
            school = school.substring(hulp + 1);

            hulp = school.indexOf("|");
            String straat = school.substring(0, hulp);
            school = school.substring(hulp + 1);

            hulp = school.indexOf("|");
            int postcode = Integer.parseInt(school.substring(0, hulp));
            school = school.substring(hulp + 1);

            String stad = school;

            Adres adres = new Adres(straat, stad, postcode);
            School s = new School(nr, naam, adres);
            scholen.add(s);
        }
    }

    private School generateSchool(int inr) {

        String school = dao.getSchool(inr);
        int hulp;

        hulp = school.indexOf("|");
        int nr = Integer.parseInt(school.substring(0, hulp));
        school = school.substring(hulp + 1);

        hulp = school.indexOf("|");
        String naam = school.substring(0, hulp);
        school = school.substring(hulp + 1);

        hulp = school.indexOf("|");
        String straat = school.substring(0, hulp);
        school = school.substring(hulp + 1);

        hulp = school.indexOf("|");
        int postcode = Integer.parseInt(school.substring(0, hulp));
        school = school.substring(hulp + 1);

        String stad = school;

        Adres adres = new Adres(straat, stad, postcode);
        School s = new School(nr, naam, adres);
        return s;

    }

}
