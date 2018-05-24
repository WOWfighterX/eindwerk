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
    </head>
    <body>
        <form method=post action=UploadFile enctype="multipart/form-data" id="uploadForm">
            <span>Evaluator: ik</span><br>
            <span>Medewerker: </span>
            <input type=text list=medewerker name="medewerker"><br>
            <%
                out.println(request.getAttribute("medewerkers"));
            %>
            <span>Functie: </span>
            <input type=text list=functie name="functie">
            <%
                out.println(request.getAttribute("functies"));
            %>
            <br>
            <span>datum: </span>
            <input type=date name=datum><br>
            <br><span>Type: </span>
            <input type=text list=type name=type>
            <datalist id=type>
                <option value="Functioneeringsgesprek">
                <option value="Evaluatiegesprek">
                <option value="Informeel gesprek">
            </datalist><br>
            <span>Bijlagen: </span>
            <input type="file" name="bijlagen" multiple><br>
            <input type=submit value=Opslaan>
        </form>
    </body>
</html>
