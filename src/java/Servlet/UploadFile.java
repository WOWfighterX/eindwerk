/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Gebruiker;
import Service.NieuwGesprekService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Convert;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;

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
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 1024;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 1024;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1024;

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

        Gebruiker gebruiker = (Gebruiker) request.getSession().getAttribute("gebruiker");

        String medewerker = "";
        String functie = "";
        String type = "";
        String datum = "";
        String uploadPath = "";
        String status = "";

        Enumeration paramNames = request.getParameterNames();

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

            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();

            //de parameters ophalen
            int i = 1;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    switch (i) {
                        case 1:
                            medewerker = item.getString();
                            break;
                        case 2:
                            functie = item.getString();
                            break;
                        case 3:
                            datum = item.getString();
                            break;
                        case 4:
                            type = item.getString();
                            break;
                        case 5:
                            status = item.getString();
                            break;
                    }
                    i++;
                }
            }

            //file locatie
            uploadPath = "d:\\Users\\aaron gevers\\Documents\\NetBeansProjects\\Evaluatie\\Evaluatie-war\\Files"+File.separator+ medewerker;

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
            iter = formItems.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);

                    item.write(storeFile);

                    makeHTML(uploadPath);

                    //Convert(uploadPath);
                }
            }

            request.setAttribute("message", "Upload has been done successfully!");
        } catch (FileUploadException ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        uploadPath = uploadPath.replaceAll("\\\\", "\\\\\\\\");

        NieuwGesprekService service = new NieuwGesprekService();
        service.addGesprek(medewerker, functie, type, datum, uploadPath, gebruiker, status);

        getServletContext().getRequestDispatcher("/Hoofdpagina").forward(request, response);
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

    private void Convert(String path) {

        final File folder = new File(path);
        List files = listFilesForFolder(folder);

        for (int i = 0; i < files.size(); i++) {

            String docname = (String) files.get(i);
            String docx = path + File.separator + docname;

            docname = docname.replaceAll(".docx", ".pdf");
            ConvertToPDF(docx, path + File.separator + docname);

        }
    }

    private List listFilesForFolder(final File folder) {

        List list = new ArrayList();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry.getName());
            }
        }

        return list;
    }

    private void ConvertToPDF(String docPath, String pdfPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfConverter.getInstance().convert(document, out, options);
            System.out.println("Done");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    private void makeHTML(String uploadPath) {

        final File folder = new File(uploadPath);
        List files = listFilesForFolder(folder);

        for (int i = 0; i < files.size(); i++) {

            BufferedWriter writer = null;
            try {
                
                String docname = (String) files.get(i);
                String docx = uploadPath + File.separator + docname;
                String path = docx.replaceAll(" ", "%20");
                
                String html = "<html>\n"
                        + "<body style=\"background-color: rgb(38,38,38); height: 100%; width: 100%; overflow: hidden; margin: 0\">\n"
                        + "        <embed width=\"100%\" height=\"100%\" name=\"plugin\" id=\"plugin\" src=\"file:///"+path+"\" type=\"application/pdf\" internalinstanceid=\"5\">\n"
                        + "</body>\n"
                        + "</html>";
                
                String filename = docname.replace(".pdf", "");
                String htmlfile = uploadPath + File.separator + filename +".html";
                
                writer = new BufferedWriter(new FileWriter(htmlfile));
                writer.write(html);
                writer.close();
                
            } catch (IOException ex) {
                Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
