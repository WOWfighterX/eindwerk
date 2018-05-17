<%-- 
    Document   : Login
    Created on : May 12, 2018, 11:39:32 AM
    Author     : aaron gevers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>
            <img src="Images/barthelLogo.jpg" alt="Logo"/>
            <form method="post" action="Login">
                <%
                    Object ob = request.getAttribute("scholen");
                    out.println(ob);
                %>
                <br>
                <span>Gebruikersnaam:</span>
                <input type="text" name="Gebruikersnaam" value="">
                <br>
                <span>Wachtwoord:</span>
                <input type="password" name="Wachtwoord" value="">
                <br><br>
                <input type="submit" value="Log in" id="Login">
            </form>
        </div>
    </body>
</html>