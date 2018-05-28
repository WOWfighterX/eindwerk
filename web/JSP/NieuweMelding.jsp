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
        <script src="JS/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="JS/functieVeranderen.js" type="text/javascript"></script>
        <link href="CSS/achtergrond.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <a href="Hoofdpagina" id="hoofdpaginaknop"><button type="button">Hoofdpagina</button></a>
        <form method="post" action="MeldingToevoegen">
            <div id="divLinks">
                <span>Medewerker: </span><br><br>
                <span>Functie: </span><br><br>
                <span>Type Gesprek: </span><br><br>
                <span>datum: </span><br><br>
            </div>
            <div id="divRechts">
                <%
                    out.println(request.getAttribute("medewerkers"));
                    out.println("<br><br>");
                    out.println(request.getAttribute("functies"));
                %><br><br>
                <select name="type">
                    <option>Functioneringsgesprek</option>
                    <option>Evalueringsgesprek</option>
                    <option>Informeel gesprek</option>
                </select><br><br>
                <input type=date name=datum name=datum><br><br>
            </div>
                <textarea rows=4 cols=50 name=extra></textarea><br><br>
            <input id="submit" type="submit" value="Opslaan">
        </form>
    </body>
</html>
