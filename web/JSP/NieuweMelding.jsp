<%-- 
    Document   : NieuweMelding
    Created on : Apr 18, 2018, 11:21:15 AM
    Author     : aaron gevers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nieuwe melding</title>
        <%
            out.println("<title>Servlet NieuwMeldingServlet</title>");    
            out.println("<link href=\"CSS/NieuwMelding.css\" rel=\"stylesheet\" type=\"text/css\"/>"); 
        %>
    </head>
    <body>
        <%
            Object ob = request.getAttribute("gen");
            out.println(ob);
            request.setAttribute("medewerker", medewerker);
            request.setAttribute("functie", functie);
            request.setAttribute("datum", datum);
            request.setAttribute("extra", extra);
        %>
    </body>
</html>
