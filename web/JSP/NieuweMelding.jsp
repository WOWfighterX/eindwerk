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
        <form method=\"post\" action=\"MeldingToevoegen\">
            <span>Medewerker: </span>
            <input id=\"medewerkerLijst\" type=text list=medewerkerList name=medewerker><br>
            <%
                out.println(request.getAttribute("medewerkers"));
            %>
            <span>Functie: </span>
            <%
                out.println(request.getAttribute("functies"));
            %>
            <br>
            <span>datum: </span>
            <span>Type Gesprek: </span>
            <select name="type">
                <option>Functioneringsgesprek</option>
                <option>Evalueringsgesprek</option>
                <option>Informeel gesprek</option>
            </select>
            <input type=date name=datum name=datum><br>
            <textarea rows=4 cols=50 name=extra></textarea><br>
            <input type="submit" value="Opslaan">
        </form>
    </body>
</html>
