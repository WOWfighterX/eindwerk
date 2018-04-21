<%-- 
    Document   : HoofdPagina
    Created on : Apr 17, 2018, 4:55:00 PM
    Author     : aaron gevers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HoofdPagina</title>
        <script src=JS/MainPage.js type=text/javascript></script>
        <link href=CSS/MainPage.css rel=stylesheet type=text/css>
    </head>
    <body>
        <%
            Object ob = request.getAttribute("gen");
            out.println(ob);
        %>
    </body>
</html>
