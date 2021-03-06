package Servlets;

import Model.*;
import Services.AdminWriteService;
import java.io.IOException;
import java.io.PrintWriter;
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
public class PersToev extends HttpServlet {

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
            out.println("<title>Servlet PersToev</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersToev at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        
        HttpSession session = request.getSession();
        Gebruiker gebruiker = (Gebruiker) session.getAttribute("gebruiker");
        School school = gebruiker.getSchool();
        int sid = school.getInstellingsnr();
        
        String stamnr = request.getParameter("stamboeknr");
        String vn = request.getParameter("voornaam");
        String fn = request.getParameter("familienaam");
        String functienaam = request.getParameter("functie");
        String email = request.getParameter("email");
        String ww = request.getParameter("wachtwoord");
        String account = request.getParameter("account");
        String stad = request.getParameter("stad");
        int postcode = Integer.parseInt(request.getParameter("postcode"));
        String straat = request.getParameter("straat");
        
        Adres a = new Adres(straat,stad,postcode);
        
        Functie f = new Functie(functienaam);
        
        int nr = Integer.parseInt(stamnr);
        Medewerker medewerker = new Medewerker(nr, vn, fn, email, f);
        
        AdminWriteService service = new AdminWriteService();
        service.addMedewerker(medewerker, ww,account, sid, a);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin");
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

}