/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoofdserlvets;

import Model.Gebruiker;
import Services.LoginService;
import Services.PasswordService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aaron gevers
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

        String scholen = getScholen();
        request.setAttribute("scholen", scholen);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/Login.jsp");
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

        String scholen = getScholen();
        request.setAttribute("scholen", scholen);
        
        HttpSession session = request.getSession();
        int loginAttempts;
        String url = "";

        if (session.getAttribute("loginAttempts") == null) {
            loginAttempts = 0;
        } else {
            loginAttempts = (int) session.getAttribute("loginAttempts");
        }

        //teveel pogingen
        if (loginAttempts > 2) {
            
            String errorMessage = "Te veel inlog pogingen. probeer later opnieuw.";
            request.setAttribute("errormessage", errorMessage);
            url = "/JSP/Login.jsp";

        } else {

            int schoolnr = Integer.parseInt(request.getParameter("School"));
            String Gebruikersnaam = request.getParameter("Gebruikersnaam");
            String Wachtwoord = request.getParameter("Wachtwoord");

            PasswordService pws = new PasswordService();
            String encryptedPass = pws.encrypt(Wachtwoord);

            Gebruiker gebruiker = getGebruiker(schoolnr, Gebruikersnaam, encryptedPass);

            if (checkLogin(gebruiker)) {

                session.invalidate();
                session = request.getSession(true);
                session.setAttribute("gebruiker", gebruiker);
                url = "/Hoofdpagina";

            } else {
                
                loginAttempts++;
                String errorMessage = "gebruikersnaam en/of wachtwoord verkeerd.\n"
                        + "nog " + (3 - loginAttempts) + " pogingen.";
                request.setAttribute("errormessage", errorMessage);
                session.setAttribute("loginAttempts", loginAttempts);
                url = "/JSP/Login.jsp";
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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

    private String getScholen() {
        LoginService service = new LoginService();
        return service.getScholen();
    }

    private Gebruiker getGebruiker(int nr, String naam, String ww) {
        LoginService service = new LoginService();
        return service.getGebruiker(nr, naam, ww);
    }

    private boolean checkLogin(Gebruiker gebruiker) {
        LoginService service = new LoginService();
        return service.checkGebruiker(gebruiker);
    }
}
