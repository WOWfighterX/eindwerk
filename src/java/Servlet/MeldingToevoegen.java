/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ReadDAO;
import DAO.WriteDAO;
import Model.Adres;
import Model.Functie;
import Model.Medewerker;
import Model.Melding;
import Service.MainPageService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aaron Gevers
 */
public class MeldingToevoegen extends HttpServlet {

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
            out.println("<title>Servlet MeldingToevoegen</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MeldingToevoegen at " + request.getContextPath() + "</h1>");
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
        
        ReadDAO dao = new ReadDAO();
        WriteDAO wdao = new WriteDAO();
        MainPageService service = new MainPageService();
        
        String medewerkernaam = request.getParameter("medewerker");
        
        int hulp = medewerkernaam.indexOf(" ");
        String vn = medewerkernaam.substring(0,hulp);
        String fn = medewerkernaam.substring(hulp+1);
                
        int id = dao.getMedewerkerID(vn,fn);
        
        Medewerker m = GenerateMedewerker(dao, id);
        
        String functie = request.getParameter(medewerkernaam);
        String type = request.getParameter("type");
        String datum = request.getParameter("datum");
        String extra = request.getParameter("extra");
        
        Functie f = new Functie(functie);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(datum);
        } catch (ParseException ex) {
            Logger.getLogger(MeldingToevoegen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        service.GenerateMeldingen();
        List meldingen = service.getMeldingen();
        int ID = 1;
        for(int i=0;i<meldingen.size();i++){
            Melding melding = (Melding) meldingen.get(i);
            hulp = melding.getMeldingID();
            if(ID==hulp){
                ID++;
            }
        }
        
        List sf = dao.getSchoolFunctie();
        int sfid = 1;
        for(int i=0;i<sf.size();i++){
            hulp = Integer.parseInt((String) sf.get(i));
            if(sfid==hulp){
                sfid++;
            }
        }
        
        Melding me = new Melding(date, extra, m, f, type, ID);
        
        try {
            wdao.addMelding(me, sfid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MeldingToevoegen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MeldingToevoegen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MeldingToevoegen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MeldingToevoegen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    private Medewerker GenerateMedewerker(ReadDAO dao, int id) {

        List list = dao.getMedewerker(id);
        Medewerker medewerker = null;

        for (int i = 0; i < list.size(); i++) {

            String persoon = (String) list.get(i);
            int hulp;

            hulp = persoon.indexOf("|");
            int nr = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String voornaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String familienaam = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String g = persoon.substring(0, hulp);
            int jaar = Integer.parseInt(g.substring(0, 3));
            int maand = Integer.parseInt(g.substring(5, 6));
            int dag = Integer.parseInt(g.substring(8, 9));
            Date geboorte = new Date(jaar, maand, dag);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String email = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String straat = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            int postcode = Integer.parseInt(persoon.substring(0, hulp));
            persoon = persoon.substring(hulp + 1);

            hulp = persoon.indexOf("|");
            String stad = persoon.substring(0, hulp);
            persoon = persoon.substring(hulp + 1);

            String functie = persoon;

            Functie f = new Functie(functie);
            Adres a = new Adres(straat, stad, postcode);
            medewerker = new Medewerker(nr, voornaam, familienaam, geboorte, email, a, f);

        }
        
        return medewerker;

    }
}
