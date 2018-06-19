/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.ReadDAO;
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
    private ReadDAO dao;

    public MainPageService() {
        dao = new ReadDAO();
    }

    public String GetPage() {
        return GeneratePage();
    }

    private String GeneratePage() {

        String gen = "";
        GenerateMeldingen();
        GenerateMedewerkers();

        for (int i = 0; i < medewerkers.size() + meldingen.size(); i++) {
            gen += "<a href=\"/";

            //link word later aangepast aan de schooldomain
            if (i < medewerkers.size()) {
                Medewerker medewerker = (Medewerker) medewerkers.get(i);

                gen += "Evaluatie-war/Gesprekken";
                gen += "?param=";
                gen += medewerker.getStamboeknr();

            } else {
                Melding melding = (Melding) meldingen.get(i - medewerkers.size());
                Medewerker medewerker = (Medewerker) melding.getMedewerker();
                Functie functie = melding.getFunctie();

                gen += "Evaluatie-war/Melding";
                gen += "?param=";
                gen += melding.getMeldingID();
            }

            gen += "\">";

            //divs met de data en juiste klassen voor css
            gen += "<div class=\"";
            if (i < medewerkers.size()) {
                gen += "werknemer";
            } else {
                gen += "notificatie";
            }
            gen += "\">\n";

            if (i < medewerkers.size()) {
                Medewerker medewerker = (Medewerker) medewerkers.get(i);
                List functies = medewerker.getFuncties();

                gen += "<span>" + medewerker.getVoornaam() + " " + medewerker.getFamilienaam() + "</span>\n";
                for (int j = 0; j < functies.size(); j++) {
                    Functie f = (Functie) functies.get(j);
                    gen += "<span>" + f.getFunctie() + "</span>\n";
                }
            } else {
                Melding melding = (Melding) meldingen.get(i - medewerkers.size());
                Medewerker medewerker = (Medewerker) melding.getMedewerker();

                gen += "<span>" + medewerker.getVoornaam() + " " + medewerker.getFamilienaam() + "</span>\n";
                gen += "<span>" + melding.getType() + "</span>\n";
                gen += "<span>" + melding.getDatum() + "</span>\n";
            }

            gen += "</div></a>\n";
        }

        return gen;
    }

    public void GenerateMeldingen() {

        List list = dao.getMeldingList();

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

            hulp = persoon.indexOf("|");
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0, 3));
            int maand = Integer.parseInt(g.substring(5, 6));
            int dag = Integer.parseInt(g.substring(8, 9));
            Date datum = new Date(jaar, maand, dag);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String t = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);
            
            hulp = persoon.indexOf("|");
            String extra = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);
            
            int id = Integer.parseInt(persoon);

            Functie f = new Functie(functie);
            Adres a = new Adres(straat, stad, postcode);
            Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, email, a, f);
            Melding melding = new Melding(datum, extra, medewerker, f, t,id);

            meldingen.add(melding);
        }

    }

    public List getMedewerkers() {
        return medewerkers;
    }

    public List getMeldingen() {
        return meldingen;
    }

    private void GenerateMedewerkers() {

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

}
