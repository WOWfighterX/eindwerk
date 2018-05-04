<%-- 
    Document   : message
    Created on : Apr 23, 2018, 12:52:13 AM
    Author     : aaron gevers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                out.println(request.getAttribute("message"));
                out.println(request.getAttribute("hulp"));
        %>
    </body>
</html>
