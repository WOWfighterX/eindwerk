/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaron gevers
 */
public class GesprekDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet GesprekDetailServlet</title>");  
            out.println("<script src=\"JS/GesprekDetail.js\" type=\"text/javascript\"></script>");
            out.println("<link href=\"CSS/GesprekDetail.css\" rel=\"stylesheet\" type=\"text/css\"/>");          
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
        String generate = "";
        
        String param = request.getParameter("param");
        int hulp = param.indexOf(".");
        String voornaam = param.substring(0, hulp);
        String familienaam = param.substring(hulp+1);
        
        Gesprek gesprek = getGesprek(voornaam, familienaam);
        
        generate += "<p>Gesprekken van "+voornaam+" "+familienaam+"</p>"
                + "<div class=\"links\">"
                + "<div class=\"gesprek\">"
                + "<span>"+gesprek.getType()+"</span><br>"
                + "<span>"+gesprek.getStatus()+"</span><br>"
                + "<span>"+gesprek.getDatum()+"</span><br>"
                + "</div>"
                + "</div>"
                + "<div class=\"rechts\">"
                + "<span>Evaluatoren: "+gesprek.getEvaluator1().getVoornaam()+" "+gesprek.getEvaluator1().getFamilianaam()+" en "+gesprek.getEvaluator2().getVoornaam()+" "+gesprek.getEvaluator2().getFamilianaam()+"</span><br>"
                + "<span>"+gesprek.getType()+"</span><br>"
                + "<span>Status: "+gesprek.getStatus()+"</span><br>"
                + "<span>Later komt hier de link naar het word bestand.</span>"
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

    private Gesprek getGesprek(String vn, String fn){
        List gesprekken = Gesprekken();
        Gesprek hulp = null;
        
        for(int i=0;i<gesprekken.size();i++){
            Gesprek gesprek = (Gesprek) gesprekken.get(i);
            if(gesprek.getMedewerker().getVoornaam().equals(vn)){
                if(gesprek.getMedewerker().getFamilienaam().equals(fn)){
                    hulp = gesprek;
                }
            }
        }
        
        return hulp;
    }
    
    private List Gesprekken(){
        Adres schooladres = new Adres("Kerkstraat 24", "Menen", 8930);
        Adres jana = new Adres("Vredestraat 17", "Lauwe", 8930);
        Adres mariea = new Adres("Kerkstraat 7", "Rekkem", 8930);
        Adres pieta = new Adres("Stationstraat 38", "Menen", 8930);
        Adres ev1a = new Adres("Ooststraat 625", "Menen", 8930);
        Adres ev2a = new Adres("Kortrijkstraat 22", "Menen", 8930);
        
        Functie janf = new Functie("leerkracht 2B");
        Functie marief = new Functie("leerkracht 4A");
        Functie pietf = new Functie("leerkracht 3A");
        Functie pietf2 = new Functie("ICT coordinator");
        
        Date datum = new Date(1992,11,26);
        
        School school = new School(125635,"Kleine Prins",schooladres);
        Medewerker jan = new Medewerker(51236, "jan", "janssens", datum, "voorbeeld@hotmail.com", jana, janf);
        Medewerker marie = new Medewerker(42365, "Marie", "Marieke", datum, "voorbeeld@hotmail.com", mariea, marief);
        Medewerker piet = new Medewerker(45896, "Piet", "Pieters", datum, "voorbeeld@hotmail.com", pieta, pietf);
        piet.AddFunctie(pietf2);
        
        Evaluator ev1 = new Evaluator(74125, "tom", "tomson", datum, "voorbeeld@hotmail.com", ev1a, pietf2);
        Evaluator ev2 = new Evaluator(53241, "Paul", "paulson", datum, "voorbeeld@hotmail.com", ev2a, pietf2);
        
        Gesprek jangesprek = new Gesprek(jan, school, datum, "ondertekend", "functioneeringsgesprek", ev1, ev2);
        Gesprek mariegesprek = new Gesprek(marie, school, datum, "ondertekend", "functioneeringsgesprek", ev1, ev2);
        Gesprek pietgesprek = new Gesprek(piet, school, datum, "ondertekend", "functioneeringsgesprek", ev1, ev2);
        
        List gesprekken = new ArrayList();
        gesprekken.add(jangesprek);
        gesprekken.add(mariegesprek);
        gesprekken.add(pietgesprek);
        
        return gesprekken;
    }
    
}
