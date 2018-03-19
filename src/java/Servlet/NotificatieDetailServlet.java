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
public class NotificatieDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet NotificatieDetailServlet</title>");      
            out.println("<link href=\"CSS/NotificatieDetail.css\" rel=\"stylesheet\" type=\"text/css\"/>");        
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
        
        String param = request.getParameter("param");
        int hulp = param.indexOf(".");
        String medewerker = param.substring(0, hulp);
        
        param = param.substring(hulp+1);
        hulp = param.indexOf(".");
        String functie = param.substring(0, hulp);
        
        param = param.substring(hulp+1);
        hulp = param.indexOf(".");
        String datum = param.substring(0, hulp);
        
        param = param.substring(hulp+1);
        String extra = param;
        
        String generate = "<form method=\"post\" action=\"Hoofdpagina\">"
                + "<span>Medewerker: </span>"
                + "<input id=\"medewerkerLijst\" type=\"text\" list=\"medewerkerList\" value=\""+medewerker+"\">\n<br>"
                + "<span>Functie: </span>"
                + "<input id=\"medewerkerLijst\" type=\"text\" list=\"medewerkerList\" value=\""+functie+"\">\n<br>"
                + "<span>datum: </span>"
                + "<input type=\"date\" name=\"datum\" value=\""+datum+"\"><br>"
                + "<textarea rows=\"4\" cols=\"50\">"+extra+"</textarea><br>"
                + "<input type=\"submit\" value=\"Veranderen\">"
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
}
