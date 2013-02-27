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
    new AuthChecker().authenticate(session, response, AuthChecker.TODOS);
%>
<layout:page title="Sistema de Caronas" description="" keywords="">
    <jsp:body>
        <style>
            h2
            {
                padding:10px;
            }
        </style>
        <h1>Sistema de Caronas</h1>
        <h2>Bem vindo, <c:out value="${sessionScope.name}"/></h2>
        
        <a class="btn btn-success" href="./passageiro/index.jsp">Passageiros</a>
        <a class="btn btn-success" href="./veiculo/index.jsp">Veiculos</a>
        <a class="btn btn-success" href="./usuario/index.jsp">Usuarios</a>
        <a class="btn btn-success" href="./reserva/index.jsp">Reservas</a>
    </jsp:body>
</layout:page>