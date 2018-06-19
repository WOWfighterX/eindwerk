<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/login.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/achtergrond.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>
            <img src="Images/leiebrug.png" alt="Logo"/>
            <form method="post" action="Login">
                <br><br><br>
                <%
                    Object ob = request.getAttribute("scholen");
                    out.println(ob);
                %>
                <br>
                <span>Gebruikersnaam:</span>
                <input type="text" name="Gebruikersnaam" value="" required>
                <br><br>
                <span>Wachtwoord:</span>
                <input type="password" name="Wachtwoord" value="" required>
                <br><br>
                <input type="submit" value="Log in" id="Login">
                <br><br>
                ${errormessage}
            </form>
        </div>
    </body>
</html>