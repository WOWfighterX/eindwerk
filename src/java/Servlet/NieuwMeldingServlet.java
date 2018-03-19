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
public class NieuwMeldingServlet extends HttpServlet {

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
            out.println("<title>Servlet NieuwMeldingServlet</title>");    
            out.println("<link href=\"CSS/NieuwMelding.css\" rel=\"stylesheet\" type=\"text/css\"/>");        
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
        
        String generate = "<form method=\"post\" action=\"Hoofdpagina\">"
                + "<span>Medewerker: </span>"
                + "<input id=\"medewerkerLijst\" type=\"text\" list=\"medewerkerList\">\n<br>"
                + "<datalist id=\"medewerkerList\">\n"
                + generateMedewerkers() + "<br>"
                + "<span>Functie: </span>"
                + generateFuncties() + "\n<br>"
                + "<span>datum: </span>"
                + "<input type=\"date\" name=\"datum\"><br>"
                + "<textarea rows=\"4\" cols=\"50\"></textarea><br>"
                + "<input type=\"submit\" value=\"Opslaan\">"
                + "</form>";
        
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
    
    private String generateFuncties(){
        
        String functies = "";
        List functie = getFuncties();
        functies += "<select>\n";
        for(int i=0;i<functie.size();i++){
            
            List hulp = (List) functie.get(i);
            List naam = getStrings();
            
            for(int j=0;j<hulp.size();j++){
                
                functies += "<option id=\""+ naam.get(i) +"\" value=\""+ hulp.get(j) +"\">"+hulp.get(j)+"</option>";
            }
            
        }
        functies += "</select>";
        return functies;
    }
    
    private String generateMedewerkers(){
        String hulp = "";
        List data = getStrings();
        
        int k = 1;
        
        for(int i =0;i<(data.size());i++){
            String medewerker = (String) data.get(i);
            
            hulp += "<option id=\"medewerker"+k+"\" value=\""+ medewerker +"\">\n";
            k++;
            
        }
        hulp += "</datalist>";
        
        return hulp;
    }
    
    private List getStrings(){
        List strings = new ArrayList();
        
        //dummy data
        
        strings.add("Jan Janssens");
        
        strings.add("Marie marieke");
        
        strings.add("Piet Pieters");
        
        return strings;
    }
    
    private List getFuncties(){
        List functies = new ArrayList();
        List jan = new ArrayList();
        List marie = new ArrayList();
        List piet = new ArrayList();
        
        jan.add("leerkracht 2B");
        marie.add("leerkracht 4A");
        piet.add("leerkracht 3A");
        piet.add("ICT coordinator");
        
        functies.add(jan);
        functies.add(marie);
        functies.add(piet);
        
        return functies;
    }

}
