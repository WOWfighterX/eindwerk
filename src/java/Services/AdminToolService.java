/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.ReadDAO;
import Model.Adres;
import Model.Functie;
import Model.Medewerker;
import Model.School;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class AdminToolService {

    private ReadDAO dao;
    private List medewerkers;
    private List evaluatoren;
    private List scholen;

    public AdminToolService() {
        this.dao = new ReadDAO();
        this.medewerkers = new ArrayList();
        this.evaluatoren = new ArrayList();
        this.scholen = new ArrayList();
    }

    public List getMedewerkers() {
        genMedewerkers();
        return medewerkers;
    }

    public List getEvaluatoren(int sid) {
        genEvaluatoren(sid);
        return evaluatoren;
    }

    public List getScholen() {
        genScholen();
        return scholen;
    }

    private void genMedewerkers() {
        List list = dao.getMedewerkerList();

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
        List list = dao.getEvaluatoren(sid);

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

            int actief = 0;
            if (!persoon.equals("null")) {
                actief = Integer.parseInt(persoon);
            }

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

    private void genScholen() {
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
}
