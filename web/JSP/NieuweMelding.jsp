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
        <form method=\"post\" action=\"Hoofdpagina\">
            <span>Medewerker: </span>
            <input id=\"medewerkerLijst\" type=\"text\" list=\"medewerkerList\" name=\"medewerker\"><br>
            <%
                System.out.println(request.getAttribute("medewerkers"));
            %>
            <span>Functie: </span>
            <%
                System.out.println(request.getAttribute("functies"));
            %>
            <span>datum: </span>
            <input type=\"date\" name=\"datum\" name=\"datum\"><br>
            <textarea rows=\"4\" cols=\"50\" name=\"extra\"></textarea><br>
            <input type=\"submit\" value=\"Opslaan\">
        </form>
        <%
            String medewerker = request.getParameter("medewerker");
            String functie = request.getParameter("functie");
            String datum = request.getParameter("datum");
            String extra = request.getParameter("extra");
            request.setAttribute("medewerker", medewerker);
            request.setAttribute("functie", functie);
            request.setAttribute("datum", datum);
            request.setAttribute("extra", extra);
        %>
    </body>
</html>
