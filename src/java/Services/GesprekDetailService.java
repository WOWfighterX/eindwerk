/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.*;
import Model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class GesprekDetailService {

    private ReadDAO rdao;
    private WriteDAO wdao;
    private Medewerker medewerker;
    private int sid;
    private List gesprekken;

    public GesprekDetailService(int mid, int sid) {
        this.rdao = new ReadDAO();
        this.wdao = new WriteDAO();
        medewerker = genMedewerker(mid);
        this.sid = sid;
        genGesprekken(mid, sid);
    }

    public String getKoptekst() {

        String hulp = "";

        hulp += "<p>Gesprekken van " + medewerker.getVoornaam() + " " + medewerker.getFamilienaam() + "</p>";

        return hulp;

    }

    public String getGesprekken() {

        String hulp = "";

        for (int i = 0; i < gesprekken.size(); i++) {
            Gesprek gesprek = (Gesprek) gesprekken.get(i);
            hulp += "<div class=\"gesprek\" id=\"links" + gesprek.getGesprekID() + "\" onclick=\"Gesprek(\'" + gesprek.getGesprekID() + "\')\">"
                    + "<span>" + gesprek.getType() + "</span><br>"
                    + "<span>" + gesprek.getStatus() + "</span><br>"
                    + "<span>" + gesprek.getDatum() + "</span><br>"
                    + "</div>";
        }

        return hulp;

    }

    public String getGesprekkenDetail() {

        String hulp = "";

        for (int i = 0; i < gesprekken.size(); i++) {
            Gesprek gesprek = (Gesprek) gesprekken.get(i);

            hulp += "<div class=\"details\" id=\"rechts" + gesprek.getGesprekID() + "\">"
                    + "<span>Evaluatoren: " + gesprek.getEvaluator1().getVoornaam() + " " + gesprek.getEvaluator1().getFamilienaam() + " en " + gesprek.getEvaluator2().getVoornaam() + " " + gesprek.getEvaluator2().getFamilienaam() + "</span><br>"
                    + "<span>" + gesprek.getType() + "</span><br>"
                    + "<span>Status: " + gesprek.getStatus() + "</span><br><br><br>"
                    + genFiles(gesprek.getFilepath())
                    + "</div>";
        }

        return hulp;

    }

    private Medewerker genMedewerker(int mid) {

        List medewerkers = new ArrayList();
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
                Medewerker mede = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);

                //kijken of de medewerker al bestaat. al hij al bestaat word de functie toegevoegd aan zijn functielijst.
                int nummer = 0;
                for (int j = 0; j < medewerkers.size(); j++) {
                    Medewerker m = (Medewerker) medewerkers.get(j);
                    Boolean b = !m.IsAanwezig(mede, f);
                    if (b) {
                        nummer++;
                    }
                }

                if (nummer == medewerkers.size()) {
                    medewerkers.add(mede);
                }
            }
        }

        return (Medewerker) medewerkers.get(0);

    }

    private void genGesprekken(int mid, int sid) {

        gesprekken = new ArrayList();
        List list = rdao.getGesprekken(mid, sid);
        for (int i = 0; i < list.size(); i++) {

            String gesprekstring = (String) list.get(i);

            int hulp;

            hulp = gesprekstring.indexOf("|");
            int gid = Integer.parseInt(gesprekstring.substring(0, hulp));
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            int sfid = Integer.parseInt(gesprekstring.substring(0, hulp));
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            String g = gesprekstring.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0, 3));
            int maand = Integer.parseInt(g.substring(5, 6));
            int dag = Integer.parseInt(g.substring(8, 9));
            Date datum = new Date(jaar, maand, dag);
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            String status = gesprekstring.substring(0, hulp);
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            String type = gesprekstring.substring(0, hulp);
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            int ev1id = Integer.parseInt(gesprekstring.substring(0, hulp));
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            int ev2id = Integer.parseInt(gesprekstring.substring(0, hulp));
            gesprekstring = gesprekstring.substring(hulp + 1);

            hulp = gesprekstring.indexOf("|");
            String filepath = gesprekstring.substring(0, hulp);
            gesprekstring = gesprekstring.substring(hulp + 1);

            int fid = Integer.parseInt(gesprekstring);

            Medewerker ev1 = genMedewerker(ev1id);
            Medewerker ev2 = genMedewerker(ev2id);

            School s = genSchool(sid);

            Gesprek gesprek = new Gesprek(medewerker, s, datum, status, type, ev1, ev2, filepath);
            gesprek.setGesprekID(gid);
            gesprekken.add(gesprek);
        }

    }

    private School genSchool(int sid) {

        String school = rdao.getSchool(sid);
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

    private String genFiles(String filepath) {

        final File folder = new File(filepath);
        List files = listFilesForFolder(folder);

        String hulp = "";
        for (int i = 0; i < files.size(); i++) {

            String path = filepath + File.separator + files.get(i);
            
            String html = ".html";
            //check welke html files zijn
            if(path.toLowerCase().contains(html.toLowerCase())){
                //check op de dubbel
                if(!path.toLowerCase().contains(html.toLowerCase()+html.toLowerCase())){
                    
                    
                    
                    String htmlfile = path.replaceAll(" ", "%20");
                    String naam = (String) files.get(i);
                    naam = naam.replace(".html", "");
                    hulp +="<a target=\"_blank\" href=\""+htmlfile+"\">"+naam+"</a></br>";
                }
            }
            
        }
        return hulp;
    }

    private List listFilesForFolder(final File folder) {

        List list = new ArrayList();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry.getName());
            }
        }

        return list;
    }
}
