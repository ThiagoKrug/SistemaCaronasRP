<%-- 
    Document   : principal
    Created on : 23/02/2013, 12:58:22
    Author     : Usuario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@page import="com.auth.AuthChecker" %>
<%
    new AuthChecker().authenticate(session, response, new String[]{
        "Administrador", "Servidor Solicitante", "Motorista"});
%>
<layout:page title="" description="" keywords="">
    <jsp:body>
        <h1>Sistema de Caronas Unipampa</h1>
        <h2>Bem vindo, <c:out value="${sessionScope.name}"/></h2>
        <a href="./passageiro/index.jsp">Passageiros</a>
        <a href="./veiculo/index.jsp">Veiculos</a>
        <a href="./usuario/index.jsp">Usuarios</a>
    </jsp:body>
</layout:page>