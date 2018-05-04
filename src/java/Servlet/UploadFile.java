/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author aaron gevers
 */
public class UploadFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 1024;  
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 1024; 
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 1024; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadFile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadFile at " + request.getContextPath() + "</h1>");
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
        
        String medewerker = (String) request.getSession().getAttribute("medewerker");
        String functie =   (String) request.getSession().getAttribute("functie");
        String type =  (String) request.getSession().getAttribute("type");
        String datum =  (String) request.getSession().getAttribute("datum");
        
        Enumeration paramNames = request.getParameterNames();
        String hulp = "";
        
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            hulp += "<tr><td>" + paramName + "</td>\n<td>";
            String[] paramValues = request.getParameterValues(paramName);

            // Read single valued data
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                hulp += "<i>No Value</i>";
                else
                hulp += paramValue;
            } else {
                // Read multiple valued data
                hulp += "<ul>";

                for(int i = 0; i < paramValues.length; i++) {
                    hulp += "<li>" + paramValues[i];
                }
                hulp += "</ul>";
            }
        }
        hulp += "</tr>\n</table>\n</body></html>";
   
        try {
            
        
            if (!ServletFileUpload.isMultipartContent(request)) {
                PrintWriter writer = response.getWriter();
                writer.flush();
                return;
            }
            
            
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            
            //file locatie
            String uploadPath = "d:\\Users\\aaron gevers\\Documents\\test" + "\\" + medewerker;
            
            //dir maken als het niet bestaat
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            
            uploadPath += "\\" + datum;
            
            uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
             
            
            //files doorlopen en opslaan
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
            
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    
                    item.write(storeFile);
                }
            }
            
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (FileUploadException ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        
        request.setAttribute("hulp", hulp);
        
        getServletContext().getRequestDispatcher("/JSP/message.jsp").forward(request, response);
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
