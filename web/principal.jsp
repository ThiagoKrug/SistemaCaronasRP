<%-- 
    Document   : principal
    Created on : 23/02/2013, 12:58:22
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.auth.AuthChecker" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            new AuthChecker().authenticate(session, response);
        %>
        <h1>Sistema de Caronas Unipampa</h1>
        <h2>Bem vindo, <%= session.getAttribute("Name") %></h2>
        <a href="passageiro/index.jsp">Passageiros</a>
    </body>
</html>
