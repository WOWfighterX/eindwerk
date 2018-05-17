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
import Model.Melding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class NotificatieDetailService {

    private ReadDAO dao;

    public NotificatieDetailService() {
        this.dao = new ReadDAO();
    }

    public Melding getMelding(int MeldingID) {

        String melding = dao.getMelding(MeldingID);

        int hulp;

        hulp = melding.indexOf("|");
        int nr = Integer.parseInt(melding.substring(0, hulp));
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String voornaam = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String familienaam = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String g = melding.substring(0, hulp);
        int jaar = Integer.parseInt(g.substring(0, 3));
        int maand = Integer.parseInt(g.substring(5, 6));
        int dag = Integer.parseInt(g.substring(8, 9));
        Date geboorte = new Date(jaar, maand, dag);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String email = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String straat = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        int postcode = Integer.parseInt(melding.substring(0, hulp));
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String stad = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String functie = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String d = melding.substring(0, hulp);
        jaar = Integer.parseInt(g.substring(0, 3));
        maand = Integer.parseInt(g.substring(5, 6));
        dag = Integer.parseInt(g.substring(8, 9));
        Date datum = new Date(jaar, maand, dag);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String t = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        hulp = melding.indexOf("|");
        String extra = melding.substring(0, hulp);
        melding = melding.substring(hulp + 1);

        int id = Integer.parseInt(melding);

        Functie f = new Functie(functie);
        Adres a = new Adres(straat, stad, postcode);
        Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);
        Melding m = new Melding(datum, extra, medewerker, f, t, id);
        
        return m;
    }

}
