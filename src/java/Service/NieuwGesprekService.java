/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ReadDAO;
import DAO.WriteDAO;
import Model.Adres;
import Model.Evaluator;
import Model.Functie;
import Model.Gesprek;
import Model.Medewerker;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron gevers
 */
public class NieuwGesprekService {

    private ReadDAO rdao;
    private WriteDAO wdao;
    private List evaluatoren;

    public NieuwGesprekService() {
        rdao = new ReadDAO();
        wdao = new WriteDAO();
    }
    
    public void addGesprek(String medewerker, String functie, String type, String datum, String uploadPath) {
        
        int gid = rdao.getGesprekID();
        
        int hulp = medewerker.indexOf(" ");
        String mvn = medewerker.substring(0,hulp);
        String mfn = medewerker.substring(hulp+1);
        
        int sfid = rdao.getSchoolFunctie(mvn,mfn,functie);
        int mid = rdao.getMedewerkerID(mvn, mfn);
        
        int jaar = Integer.parseInt(datum.substring(0, 3));
        int maand = Integer.parseInt(datum.substring(5, 6));
        int dag = Integer.parseInt(datum.substring(8, 9));
        Date d = new Date(jaar, maand, dag);
        
        genEvaluatoren();
        Evaluator ev1 = (Evaluator) evaluatoren.get(0);
        Evaluator ev2 = (Evaluator) evaluatoren.get(1);
        
        Medewerker m = new Medewerker(mid, mvn, mfn);
        Gesprek gesprek = new Gesprek(m, d, "nog niet klaar", type, ev1, ev2,uploadPath);
        
        try {
            wdao.addGesprek(gesprek, gid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NieuwGesprekService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NieuwGesprekService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(NieuwGesprekService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NieuwGesprekService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void genEvaluatoren() {
        List list = rdao.getEvaluatoren();

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

            int actief = Integer.parseInt(persoon);

            if (actief == 1) {

                Functie f = new Functie(functie);
                Adres a = new Adres(straat, stad, postcode);
                Medewerker medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);

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
    
}
