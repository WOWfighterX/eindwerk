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
public class NieuwGesprekServlet extends HttpServlet {

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
            out.println("<title>Servlet NieuwGesprekServlet</title>");  
            out.println("<link href=\"CSS/NieuwGesprek.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"JS/NieuwGesprek.js\" type=\"text/javascript\"></script>");          
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
                + "<span>Evaluator: ik</span><br>"
                + "<span>Medewerker: </span>"
                + "<input id=\"medewerkerLijst\" type=\"text\" list=\"medewerkerList\"><br>"
                + "<datalist id=\"medewerkerList\">"
                + generateMedewerkers()
                + "<span>Functie: </span>"
                + "<input id=\"functieLijst\"  type=\"text\" list=\"FunctieList\">"
                + "<datalist id=\"FunctieList\">"
                + generateFuncties()
                + "<input type=\"text\" name=\"example\" list=\"TypeList\">"
                + "<datalist id=\"TypeList\">"
                + "    <option value=\"Functioneeringsgesprek\">"
                + "    <option value=\"Evaluatiegesprek\">"
                + "    <option value=\"Informeel gesprek\">"
                + "</datalist><br>"
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
        processRequest(request, response, "");
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
        
        String vorige = "";
        int k = 1;
        
        for(int i =0;i<(data.size()/2);i++){
            String medewerker = (String) data.get(i*2);
            
            if(!medewerker.equals(vorige)){
                hulp += "<option onclick=\"selectMedewerker()\" id=\"medewerker"+k+"\" value=\""+ medewerker +"\">\n";
                vorige = medewerker;
                k++;
            }
            
        }
        hulp += "</datalist>";
        
        return hulp;
    }
    
    private String generateFuncties(){
        String hulp = "";
        List data = getStrings();
        
        String vorige = "";
        int k = 0;
        
        for(int i =0;i<(data.size()/2);i++){
            String medewerker = (String) data.get(i*2);
            
            if(!medewerker.equals(vorige)){
                k++;
                vorige = medewerker;
                hulp += "<option id=\"functie"+k+"\" value=\""+ data.get((i*2)+1) +"\">\n";
            }else{
                hulp += "<option id=\"functie"+k+"\" value=\""+ data.get((i*2)+1) +"\">\n";
            }
        }
        hulp += "</datalist>";
        
        return hulp;
    }
    
    private List getStrings(){
        List strings = new ArrayList();
        
        //dummy data
        
        strings.add("Jan Janssens");
        strings.add("Leerkracht 4A");
        
        strings.add("Marie marieke");
        strings.add("Leerkracht 2B");
        
        strings.add("Marie marieke");
        strings.add("Leerkracht 5C");
        
        strings.add("Piet Pieters");
        strings.add("Leerkracht 3A");
        
        return strings;
    }

}
