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
public class MainPageServlet extends HttpServlet {

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
            out.println("<title>Servlet MainPageServlet</title>");  
            out.println("<link href=\"CSS/MainPage.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"JS/MainPage.js\" type=\"text/javascript\"></script>");
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
        processRequest(request, response, "");
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
        
        String generated;
        
        generated =  "<div id=\"buttons\">"
                    + "<a href=\"Opties\"><button type=\"button\">Opties</button></a>"
                    + "<a href=\"login.html\"><button type=\"button\">Log Out</button></a>"
                //later aanpassen met rechten
                    + "<a href=\"admin\"><button type=\"button\">Admin</button></a>"
                    + "</div>";
        
        generated += 
        "<div class=\"left\">\n" +
"            <div id=\"werknemer\" onclick=\"Menu(\'werknemer\')\">\n" +
"                    <p>Werknemers</p>\n" +
"            </div>\n" +
"            <div id=\"notificatie\" onclick=\"Menu(\'notificatie\')\">\n" +
"                    <p>Notificaties</p>\n" +
"            </div>\n" +
             "<a href=\"GesprekAanmaken\">"+
"            <div>\n" +
"                    <p>+ Nieuw Gesprek</p>\n" +
"            </div></a>\n" +
             "<a href=\"MeldingAanmaken\">"+
"            <div>\n" +
"                    <p>+ Nieuwe Notificatie</p>\n" +
"            </div></a>" +
"        </div>";
        
        generated +=
        "<div class=\"right\">\n" +
            GenerateRight() +
"        </div>";
        
        
        
        processRequest(request, response, generated);
        
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

    private String GenerateRight(){
        String hulp = "";
        int aantalM = 4; //aantal medewerkers, word later dynamisch
        int aantalN = 3; //aantal notificaties, word later dynamisch
        
        String vorigeMedewerker = "";
            
        for(int k=0;k<aantalM+aantalN;k++){
            //filter voor duplicates
            String medewerker = (String) GetStrings().get(k*3);
            
            if(!medewerker.equals(vorigeMedewerker)){
                if(k<aantalM){
                    vorigeMedewerker = medewerker;
                }else{
                    //dit zorgt er gewoon voor dat ze verschillen
                    vorigeMedewerker = "1";
                    medewerker = "2";
                }
                
                //href naar servlets
                hulp += "<a href=\"/";
            
                //link word later aangepast aan de schooldomain
                if(k<aantalM){
                    hulp+="Evaluatie-war/Gesprekken";
                }else{
                    hulp+="Evaluatie-war/Melding";
                }
            
                hulp += "?param=";
            
                for(int j=0;j<2;j++){
                    hulp += GetStrings().get((k*3)+j)+".";
                }
            
                hulp +="\">";
            
                //divs met de data en juiste klassen voor css
                hulp += "<div class=\"";
                if(k<aantalM){
                    hulp+="werknemer";
                }else{
                    hulp+="notificatie";
                }
                hulp += "\">\n";
        
                for(int j=0;j<3;j++){
                    hulp+="<span>"+ GetStrings().get((k*3)+j) +"</span>\n";
                }
                
                hulp +="</div></a>\n";
            }
        }
        
        return hulp;
    }
    
    private List GetStrings(){
        List strings = new ArrayList();
        //dummy data
        
        strings.add("Jan Janssens");
        strings.add("Leerkracht 4A");
        strings.add("34 jaar");
        
        strings.add("Marie marieke");
        strings.add("Leerkracht 2B");
        strings.add("42 jaar");
        
        strings.add("Marie marieke");
        strings.add("Leerkracht 5C");
        strings.add("42 jaar");
        
        strings.add("Piet Pieters");
        strings.add("Leerkracht 3A");
        strings.add("29 jaar");
        
        strings.add("Jan Janssens");
        strings.add("FunctioneeringsGesprek");
        strings.add("2018-02-22");
        
        strings.add("Jan Janssens");
        strings.add("Informeel Gesprek");
        strings.add("2018-03-16");
        
        strings.add("Piet Pieters");
        strings.add("EvaluatieGesprek");
        strings.add("2018-03-03");
        
        return strings;
    }
}
