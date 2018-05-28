/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoofdserlvets;

import Services.MainPageService;
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
        doPost(request, response);
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
                    + "<a href=\"/Evaluatie-war/Login\"><button type=\"button\">Log Out</button></a>"
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
        "<div class=\"right\" style=\"overflow-y:scroll; height:400px;\">\n" +
            Generate() +
"        </div>";
        
        request.setAttribute("gen", generated);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/HoofdPagina.jsp");
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
    
    private String Generate(){
        MainPageService service = new MainPageService();
        return service.GetPage();
    }
}
