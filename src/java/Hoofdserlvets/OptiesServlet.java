/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoofdserlvets;

import Model.Gebruiker;
import Services.OptiesService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaron gevers
 */
public class OptiesServlet extends HttpServlet {

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
            out.println("<title>Servlet OptiesServlet</title>");    
            out.println("<link href=\"CSS/Opties.css\" rel=\"stylesheet\" type=\"text/css\"/>");    
            out.println("<link href=\"CSS/achtergrond.css\" rel=\"stylesheet\" type=\"text/css\"/>");       
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
        
        String generate = "<a href=\"Hoofdpagina\" id=\"hoofdpaginaknop\"><button type=\"button\">Hoofdpagina</button></a>"
                + "<form method=\"post\" action=\"Opties\">"
                + "<span>Stamnummer: "+gebruiker.getStamboeknr()+"</span><br><br>"
                + "<span>Naam: "+gebruiker.getGebruikersnaam()+"</span><br>"
                + "<span>Huidig Wachtwoord: </span>"
                + "<input type=\"password\" name=\"huidig\"><br>"
                + "<span>Nieuw Wachtwoord: </span>"
                + "<input type=\"password\" name=\"nieuw\"><br>"
                + "<span>Controle Wachtwoord: </span>"
                + "<input type=\"password\" name=\"controle\"><br>"
                + "<input type=\"submit\" value=\"Wachtwoord Veranderen\">"
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
        
        Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");
        String huidig = request.getParameter("huidig");
        String nieuw = request.getParameter("nieuw");
        String controle = request.getParameter("controle");
        
        if(gebruiker.getWachtwoord().equals(huidig)){
            if(nieuw.equals(controle)){
                veranderWachtwoord(nieuw, gebruiker.getStamboeknr());
            }
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Hoofdpagina");
        dispatcher.forward(request, response); 
        
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

    private void veranderWachtwoord(String nieuw, int nr) {
        OptiesService service = new OptiesService();
        service.veranderWachtwoord(nieuw, nr);
    }


}
