/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoofdserlvets;

import Model.Functie;
import Model.Medewerker;
import Model.Melding;
import Services.NotificatieDetailService;
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
        
        int MeldingID = Integer.parseInt(request.getParameter("param"));
        
        Melding melding = getMelding(MeldingID);
        
        
        String generate = "<form method=\"post\" action=\"Hoofdpagina\">"
                + "<span>Medewerker: "+melding.getMedewerker().getVoornaam()+" "+melding.getMedewerker().getFamilienaam()+"</span><br><br>"
                + "<span>Functie: "+melding.getFunctie().getFunctie()+"</span><br><br>"
                + "<span>datum: "+melding.getDatum()+"</span><br><br>"
                + "<span>"+melding.getInfo()+"</span><br><br>"
                + "</form>";
        
        request.setAttribute("gen", generate);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/MeldingDetail.jsp");
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

    private Melding getMelding(int MeldingID) {
        
        NotificatieDetailService service = new NotificatieDetailService();
        Melding melding = service.getMelding(MeldingID);
        return melding;
        
    }

    

    
}
