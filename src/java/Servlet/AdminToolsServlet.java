/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaron gevers
 */
public class AdminToolsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String gen)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminToolsServlet</title>");    
            out.println("<script src=\"JS/Admin.js\" type=\"text/javascript\"></script>");
            out.println("<link href=\"CSS/Admin.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println(gen);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String generate = "<div class=\"left\">"
                + "<div id=\"+pers\" onclick=\"Menu(\'+pers\')\">"
                + "<p>+ Personeel Toevoegen</p></div>"
                + "<div id=\"+func\" onclick=\"Menu(\'+func\')\">"
                + "<p>+ Functie Toevoegen</p></div>"
                + "<div id=\"+eval\" onclick=\"Menu(\'+eval\')\">"
                + "<p>+ Evaluator Veranderen</p></div>"
                + "<div id=\"+recht\" onclick=\"Menu(\'+recht\')\">"
                + "<p>+ Rechten Wijzigen</p></div>"
                + "<div id=\"+school\" onclick=\"Menu(\'+school\')\">"
                + "<p>+ School Toevoegen</p></div>"
                + "<div id=\"-school\" onclick=\"Menu(\'-school\')\">"
                + "<p>- School Verwijderen</p></div>"
                + "<div id=\"-account\" onclick=\"Menu(\'-account\')\">"
                + "<p>- Account In/Uitschakelen</p></div>"
                + "</div>";
        
        generate += "<div class=\"right\">"
                
                + "<div id=\"PersToev\">"
                + "<div class=\"divLinks\">"
                + "<p>Stamboeknr: </p>"
                + "<p>Voornaam: </p>"
                + "<p>Familienaam: </p>"
                + "<p>School: </p>"
                + "<p>Geboortedatum: </p>"
                + "<p>Functie: </p>"
                + "<p>Email: </p>"
                + "<p>Wachtwoord: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input type=\"text\" name=\"stamboeknr\">"
                + "<input type=\"text\" name=\"voornaam\">"
                + "<input type=\"text\" name=\"familienaam\">"
                + "<input type=\"text\" name=\"school\">"
                + "<input type=\"date\" name=\"datum\">"
                + "<input type=\"text\" name=\"functie\">"
                + "<input type=\"text\" name=\"email\">"
                + "<input type=\"text\" name=\"wachtwoord\">"
                + "</div>"
                + "<button type=\"button\">Toevoegen</button>"
                + "</div>"
                
                + "<div id=\"FuncToev\">"
                + "<div class=\"divLinks\">"
                + "<p>Medewerker: </p>"
                + "<p>Functie: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input id=\"medewerkerLijst\" type=\"text\" name=\"example\" list=\"medewerkerList\">"
                + "<datalist id=\"medewerkerList\">"
                + generateMedewerkers()
                + "<input type=\"text\" name=\"functie\">"
                + "</div>"
                + "<button type=\"button\">Veranderen</button>"
                + "</div>"
                
                + "<div id=\"EvalVer\">"
                + "<div class=\"divLinks\">"
                + "<p>Huidige Evaluator: </p>"
                + "<p>Nieuwe Evaluator: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input id=\"EvaluatorLijst\" type=\"text\"  list=\"evaluatorList\">"
                + "<datalist id=\"evaluatorList\">"
                + generateEvaluatoren()
                + "<input id=\"medewerkerLijst\" type=\"text\" name=\"example\" list=\"medewerkerList\">"
                + "<datalist id=\"medewerkerList\">"
                + generateMedewerkers()
                + "</div>"
                + "<button type=\"button\">Veranderen</button>"
                + "</div>"
                
                + "<div id=\"Rechten\">"
                + "<div class=\"divLinks\">"
                + "<p>Werknemer: </p>"
                + "<p>Admin recht: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input id=\"medewerkerLijst\" type=\"text\" name=\"example\" list=\"medewerkerList\">"
                + "<datalist id=\"medewerkerList\">"
                + generateMedewerkers()
                + "<input id=\"Adminrecht\" type=\"text\" list=\"adminrechtList\">"
                + "<datalist id=\"adminrechtList\">"
                + "<option value=\"Admin\">"
                + "<option value=\"Geen Admin\">"
                + "</datalist>"
                + "</div>"
                + "<button type=\"button\">Veranderen</button>"
                + "</div>"
                
                + "<div id=\"SchoolToev\">"
                + "<div class=\"divLinks\">"
                + "<p>Instellingsnr: </p>"
                + "<p>Schoolnaam: </p>"
                + "<p>Straat: </p>"
                + "<p>Postcode: </p>"
                + "<p>Stad: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input type=\"text\" name=\"instellingsnr\">"
                + "<input type=\"text\" name=\"schoolnaam\">"
                + "<input type=\"text\" name=\"straat\">"
                + "<input type=\"text\" name=\"postcode\">"
                + "<input type=\"text\" name=\"stad\">"
                + "</div>"
                + "<button type=\"button\">Toevoegen</button>"
                + "</div>"
                
                + "<div id=\"SchoolVerw\">"
                + "<div class=\"divLinks\">"
                + "<p>Schoolnaam: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input id=\"SchoolLijst\" type=\"text\"  list=\"schoolList\">"
                + "<datalist id=\"schoolList\">"
                + generateScholen()
                + "</div>"
                + "<button type=\"button\">Verwijderen</button>"
                + "</div>"
                
                + "<div id=\"Account\">"
                + "<div class=\"divLinks\">"
                + "<p>Werknemer: </p>"
                + "<p>Status: </p>"
                + "</div>"
                + "<div class=\"divRechts\">"
                + "<input id=\"medewerkerLijst\" type=\"text\" name=\"example\" list=\"medewerkerList\">"
                + "<datalist id=\"medewerkerList\">"
                + generateMedewerkers()
                + "<input id=\"Status\" type=\"text\" list=\"StatusList\">"
                + "<datalist id=\"StatusList\">"
                + "<option value=\"Actief\">"
                + "<option value=\"Inactief\">"
                + "</datalist>"
                + "</div>"
                + "<button type=\"button\">Aanpassen</button>"
                + "</div>"
                
                + "</div>";
        
        processRequest(request, response, generate);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String generate = "";
        processRequest(request, response, generate);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String generateMedewerkers() {
        
        String hulp = "";
        List data = getMedewerkers();
        
        for(int i = 0; i<data.size();i++){
            String medewerker = (String) data.get(i);
            hulp += "<option value=\""+ medewerker +"\">";
        }
        hulp += "</datalist>";
        
        return hulp;
        
    }
    
    private String generateEvaluatoren() {
        
        String hulp = "";
        List data = getEvaluatoren();
        
        for(int i = 0; i<data.size();i++){
            String evaluator = (String) data.get(i);
            hulp += "<option value=\""+ evaluator +"\">";
        }
        hulp += "</datalist>";
        
        return hulp;
        
    }
    
    private String generateScholen(){
        
        String hulp = "";
        List data = getScholen();
        
        for(int i = 0; i<data.size();i++){
            String school = (String) data.get(i);
            hulp += "<option value=\""+ school +"\">";
        }
        hulp += "</datalist>";
        
        return hulp;
        
    }
    
    private List getMedewerkers(){
        List strings = new ArrayList();
        
        //dummy data
        
        strings.add("Jan Janssens");
        
        strings.add("Marie marieke");
        
        strings.add("Piet Pieters");
        
        return strings;
    }

    private List getScholen() {
        List strings = new ArrayList();
        
        //dummy data
        
        strings.add("GBS Barthel");
        
        strings.add("VBS De jonge Prins");
        
        strings.add("GBS De Wonderwijzer");
        
        return strings;
    }
    
    private List getEvaluatoren(){
        List strings = new ArrayList();
        
        //dummy data
        
        strings.add("Jean janssens");
        
        strings.add("Bernard Claeys");
        
        return strings;
    }

}
