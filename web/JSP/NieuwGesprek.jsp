<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nieuw Gesprek</title>
        <%
            out.println("<link href=\"CSS/NieuwGesprek.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<script src=\"JS/NieuwGesprek.js\" type=\"text/javascript\"></script>");
        %>
        <script src="JS/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="JS/functieVeranderen.js" type="text/javascript"></script>
        <link href="CSS/achtergrond.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <a href="Hoofdpagina" id="hoofdpaginaknop"><button type="button">Hoofdpagina</button></a>
        <form method=post action=UploadFile enctype="multipart/form-data" id="uploadForm">
            <div id="divLinks">
                <span>Medewerker: </span><br><br>
                <span>Functie: </span><br><br>
                <span>datum: </span><br><br>
                <span>Type: </span><br><br>
                <span>Status: </span><br><br>
                <span>Bijlagen: </span><br><br>
            </div>
            <div id="divRechts">
                <%
                    out.println(request.getAttribute("medewerkers"));
                    out.println("<br><br>");
                    out.println(request.getAttribute("functies"));
                %>
                <br><br>
                <input type=date name=datum><br><br>
                <select name=type>
                    <option value="Functioneeringsgesprek">Functioneeringsgesprek</option>
                    <option value="Evaluatiegesprek">Evaluatiegesprek</option>
                    <option value="Informeel gesprek">Informeel gesprek</option>
                </select><br><br>
                <select name=status>
                    <option value="niet volledig">Niet volledig</option>
                    <option value="volledig">Volledig</option>
                </select><br><br>
                <input type="file" name="bijlagen" multiple><br><br>
            </div>
            <input id="submit" type=submit value=Opslaan>
        </form>
    </body>
</html>
