<%-- 
    Document   : MeldingDetail
    Created on : May 13, 2018, 3:58:36 PM
    Author     : aaron gevers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            out.println("<title>Servlet NotificatieDetailServlet</title>");      
            out.println("<link href=\"CSS/NotificatieDetail.css\" rel=\"stylesheet\" type=\"text/css\"/>");
        %>
    </head>
    <body>
        <%
            Object ob = request.getAttribute("gen");
            out.println(ob);
        %>
    </body>
</html>