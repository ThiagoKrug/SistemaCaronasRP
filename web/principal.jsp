<%-- 
    Document   : principal
    Created on : 23/02/2013, 12:58:22
    Author     : Usuario
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
            System.out.println(session.getAttribute("Username"));
            if (session.getAttribute("Username") == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <h1>Sistema de Caronas Unipampa</h1>
        <h2>Bem vindo, <%= session.getAttribute("Name") %></h2>
        <a href="passageiro/index.jsp">Passageiros</a>
    </body>
</html>
