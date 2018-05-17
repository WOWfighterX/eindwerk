<%-- 
    Document   : Admintools.jsp
    Created on : May 9, 2018, 11:12:14 AM
    Author     : aaron gevers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="JS/Admin.js" type="text/javascript"></script>
        <link href="CSS/Admin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="left">
            <div id="+pers" onclick="Menu('+pers')">
                <p>Personeel Toevoegen</p>
            </div>
            <div id="+func" onclick="Menu('+func')">
                <p>Functie Toevoegen</p>
            </div>
            <div id="-func" onclick="Menu('-func')">
                <p>Functie verw</p>
            </div>
            <div id="+eval" onclick="Menu('+eval')">
                <p>Evaluator Veranderen</p>
            </div>
            <div id="+recht" onclick="Menu('+recht')">
                <p>Rechten Wijzigen</p>
            </div>
            <div id="+school" onclick="Menu('+school')">
                <p>School Toevoegen</p>
            </div>
            <div id="-school" onclick="Menu('-school')">
                <p>- School Verwijderen</p>
            </div>
            <div id="-account" onclick="Menu('-account')">
                <p>- Account In/Uitschakelen</p>
            </div>
        </div>
        <div class="right">

            <div id="PersToev">
                <form method=post action=PersToev enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Stamboeknr: </p>
                        <p>Voornaam: </p>
                        <p>Familienaam: </p>
                        <p>School: </p>
                        <p>Geboortedatum: </p>
                        <p>Functie: </p>
                        <p>Email: </p>
                        <p>Wachtwoord: </p>
                    </div>
                    <div class="divRechts">
                        <input type="text" name="stamboeknr">
                        <input type="text" name="voornaam">
                        <input type="text" name="familienaam">
                        <input type="text" name="school">
                        <input type="date" name="datum">
                        <input type="text" name="functie">
                        <input type="text" name="email">
                        <input type="text" name="wachtwoord">
                    </div>
                    <button type="button">Toevoegen</button>
                </form>
            </div>

            <div id="FuncToev">
                <form method=post action=FuncToev enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Medewerker: </p>
                        <p>Functie: </p>
                    </div>
                    <div class="divRechts">
                        <input id="medewerkerLijst" type="text" name="example" list="medewerkerList">
                        <%
                            out.println(request.getAttribute("medewerkers"));
                        %>
                        <input type="text" name="functie">
                    </div>
                    <button type="button">Veranderen</button>
                </form>
            </div>
                        
            <div id="FuncVerw">
                <form method=post action=FuncVerw enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Medewerker: </p>
                        <p>Functie: </p>
                    </div>
                    <div class="divRechts">
                        <input id="medewerkerLijst" oninput='onInput()' type="text" name="example" list="medewerkerList">
                        <%
                            out.println(request.getAttribute("medewerkers"));
                            out.println(request.getAttribute("functies"));
                        %>
                    </div>
                    <button type="button">Veranderen</button>
                </form>
            </div>

            <div id="EvalVer">
                <form method=post action=EvalVer enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Huidige Evaluator: </p>
                        <p>Nieuwe Evaluator: </p>
                    </div>
                    <div class="divRechts">
                        <br>
                        <%
                            out.println(request.getAttribute("evaluatoren"));
                        %>
                        <input id="medewerkerLijst" type="text" name="example" list="medewerkerList">
                        <%
                            out.println(request.getAttribute("medewerkers"));
                        %>
                    </div>
                    <button type="button">Veranderen</button>
                </form>
            </div>

            <div id="Rechten">
                <form method=post action=Rechten enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Werknemer: </p>
                        <p>Admin recht: </p>
                    </div>
                    <div class="divRechts">
                        <input id="medewerkerLijst" type="text" name="example" list="medewerkerList">
                        <%
                            out.println(request.getAttribute("medewerkers"));
                        %>
                        <input id="Adminrecht" type="text" list="adminrechtList">
                        <datalist id="adminrechtList">
                            <option value="Admin">
                            <option value="Geen Admin">
                        </datalist>
                    </div>
                    <button type="button">Veranderen</button>
                </form>
            </div>

            <div id="SchoolToev">
                <form method=post action=SchoolToev enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Instellingsnr: </p>
                        <p>Schoolnaam: </p>
                        <p>Straat: </p>
                        <p>Postcode: </p>
                        <p>Stad: </p>
                    </div>
                    <div class="divRechts">
                        <input type="text" name="instellingsnr">
                        <input type="text" name="schoolnaam">
                        <input type="text" name="straat">
                        <input type="text" name="postcode">
                        <input type="text" name="stad">
                    </div>
                    <button type="button">Toevoegen</button>
                </form>
            </div>

            <div id="SchoolVerw">
                <form method=post action=SchoolVerw enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Schoolnaam: </p>
                    </div>
                    <div class="divRechts">
                        <br>
                        <%
                            out.println(request.getAttribute("scholen"));
                        %>
                    </div>
                    <button type="button">Verwijderen</button>
                </form>
            </div>

            <div id="Account">
                <form method=post action=Account enctype="multipart/form-data">
                    <div class="divLinks">
                        <p>Werknemer: </p>
                        <p>Status: </p>
                    </div>
                    <div class="divRechts">
                        <input id="medewerkerLijst" type="text" name="example" list="medewerkerList">
                        <%
                            out.println(request.getAttribute("medewerkers"));
                        %>
                        <input id="Status" type="text" list="StatusList">
                        <datalist id="StatusList">
                            <option value="Actief">
                            <option value="Inactief">
                        </datalist>
                    </div>
                    <button type="button">Aanpassen</button>
                </form>
            </div>

        </div>
    </body>
</html>