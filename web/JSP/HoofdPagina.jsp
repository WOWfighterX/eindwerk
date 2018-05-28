<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HoofdPagina</title>
        <script src=JS/MainPage.js type=text/javascript></script>
        <link href=CSS/MainPage.css rel=stylesheet type=text/css>
        <link href="CSS/achtergrond.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            Object ob = request.getAttribute("gen");
            out.println(ob);
        %>
    </body>
</html>
