<%-- 
    Document   : principal
    Created on : 23/02/2013, 12:58:22
    Author     : Usuario
--%>

<%@page import="com.mail.Mail"%>
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
            new AuthChecker().authenticate(session, response, new String[] {
                "Administrador", "Servidor Solicitante", "Motorista"});
        %>
        <h1>Sistema de Caronas Unipampa</h1>
        <h2>Bem vindo, <%= session.getAttribute("Name") %></h2>
        <a href="passageiro/index.jsp">Passageiros</a>
        <a href="veiculo/index.jsp">Veiculos</a>
        <a href="usuario/index.jsp">Usuarios</a>
    </body>
</html>
