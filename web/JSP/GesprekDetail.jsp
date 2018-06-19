<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            out.println("<script src=\"JS/GesprekDetail.js\" type=\"text/javascript\"></script>");
            out.println("<link href=\"CSS/GesprekDetail.css\" rel=\"stylesheet\" type=\"text/css\"/>");
        %>
        <link href="CSS/achtergrond.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <a href="Hoofdpagina" id="hoofdpaginaknop"><button type="button">Hoofdpagina</button></a>
            <%
                out.print(request.getAttribute("koptekst"));
            %>
        <div class="links" style="overflow-y:scroll">
            <%
                out.println(request.getAttribute("gesprekken"));
            %>
        </div>
        <div class="rechts">
            <%
                out.println(request.getAttribute("gesprekkendetails"));
            %>
        </div>
    </body>
</html>
