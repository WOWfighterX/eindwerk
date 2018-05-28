/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Functie;
import Model.Gebruiker;
import Model.Medewerker;
import Model.School;
import Service.AdminToolService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
        
        Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");
        
        String medewerkers = generateMedewerkers();
        String evaluatoren = generateEvaluatoren(gebruiker.getSchool().getInstellingsnr());
        String scholen = generateScholen();
        String functies = generateFuncties();
        String inputmedewerkers = generateSelectMedewerkers();
        
        request.setAttribute("inputmedewerkers", inputmedewerkers);
        request.setAttribute("medewerkers", medewerkers);
        request.setAttribute("evaluatoren", evaluatoren);
        request.setAttribute("scholen", scholen);
        request.setAttribute("functies", functies);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/Admintools.jsp");
        dispatcher.forward(request, response);
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
        doGet(request, response);
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
        
        String hulp = "<datalist id=\"medewerkerList\" name=\"medewerker\">";
        List medewerkers = getMedewerkers();
        
        for(int i = 0; i<medewerkers.size();i++){
            Medewerker medewerker =  (Medewerker) medewerkers.get(i);
            hulp += "<option value=\""+ medewerker.getVoornaam()+" "+medewerker.getFamilienaam() +"\">";
        }
        hulp += "</datalist>";
        
        return hulp;
        
    }
    
    private String generateSelectMedewerkers() {
        
        String hulp = "<select id=\"medewerkerSelect\" name=\"medewerker\">";
        hulp += "<option value=\"\">Kies een medewerker...</option>";
        List medewerkers = getMedewerkers();
        
        for(int i = 0; i<medewerkers.size();i++){
            Medewerker medewerker =  (Medewerker) medewerkers.get(i);
            hulp += "<option value=\""+ medewerker.getStamboeknr() +"\">"+ medewerker.getVoornaam()+" "+medewerker.getFamilienaam() +"</option>";
        }
        hulp += "</select>";
        
        return hulp;
        
    }
    
    private String generateEvaluatoren(int sid) {
        
        List evaluatoren = getEvaluatoren(sid);
        String hulp = "<select name=\"evaluator\">";

        for (int i = 0; i < evaluatoren.size(); i++) {

            Medewerker medewerker = (Medewerker) evaluatoren.get(i);
            hulp += "<option value=\"" + medewerker.getStamboeknr() + "\">" + medewerker.getVoornaam()+" "+ medewerker.getFamilienaam() + "</option>";

        }

        hulp += "</select>";
        return hulp;
        
    }
    
    private String generateScholen(){
        
        List scholen = getScholen();
        
        String hulp = "<select name=\"school\">";

        for (int i = 0; i < scholen.size(); i++) {

            School school = (School) scholen.get(i);
            hulp += "<option value=\"" + school.getInstellingsnr() + "\">" + school.getSchoolnaam() + "</option>";

        }

        hulp += "</select>";
        return hulp;
        
    }
    
    private List getMedewerkers(){
        
        AdminToolService service = new AdminToolService();
        return service.getMedewerkers();
        
    }

    private List getScholen() {
        
        AdminToolService service = new AdminToolService();
        return service.getScholen();
        
    }
    
    private List getEvaluatoren(int sid){
        
        AdminToolService service = new AdminToolService();
        return service.getEvaluatoren(sid);
        
    }

    private String generateFuncties() {
        
        String hulp = "<select id=\"functies\" name=\"functie\">\n"
                + "<option value=\"\">Kies functie...</option>";
        List medewerkers = getMedewerkers();
        
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

}
