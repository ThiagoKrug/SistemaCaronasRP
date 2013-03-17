<%-- 
    Document   : relat
    Created on : 16/03/2013, 23:36:41
    Author     : Usuario
--%>

<%@page import="java.util.List"%>
<%@page import="com.model.entity.Passageiro"%>
<%@page import="com.model.entity.Viagem"%>
<%@page import="com.model.dao.ViagemDAO"%>
<%@page import="com.auth.AuthChecker"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{
        AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    ViagemDAO vdao = new ViagemDAO(connection);
    request.setAttribute("vdao", vdao);
    request.setAttribute("admin", AuthChecker.ADMIN.toString());
    Viagem v = (Viagem)vdao.getById(Integer.parseInt(request.getParameter("id")));
%>
<!doctype html>
<html>
    <head>
        <title>Relatório de Criação de Viagem</title>
        <style>
            header
            {
                margin-left: auto;
                margin-right: auto;
                width: 40%;
            }
            
             header div.imag
            {
                margin-left: auto;
                margin-right: auto;
                width: 50%;
            }
            
            header h2
            {
                text-align: center;
            }
        </style>
    </head>
    
    <body>
        <header class="headers">
            <div class="imag">
            <img src="../imagens/unipampa.jpg" alt="Unipampa" legend="Unipampa" title="Unipampa"/>
            </div>
            <h2>Relatório de Criação da Viagem</h2>
        </header>
            <h3>Nome Autorizante: <%= v.getAutorizante() %></h3>
            <h3>Motorista: <%= v.getMotorista(connection).getNome() %>    Rg: <%= v.getMotorista(connection).getRg() %></h3>
            <h3>Placa do Veículo: <%=v.getVeiculo(connection).getPlaca() %></h3>
            <h3>Data de Saída: <%= v.getDataSaidaFormatada() %></h3>
            <h3>Hora de Saída: <%= v.getHoraSaidaFormatada() %></h3>
            <h3>Data de Saída: <%= v.getDataRetornoFormatada() %></h3>
            <h3>Hora de Saída: <%= v.getHoraRetornoFormatada() %></h3>
            <h3>Percurso: <%= v.getPercurso() %></h3>
            <h3>Local Saída: <%= v.getLocalSaida() %></h3>
            <h3>Local Retorno: <%= v.getLocalRetorno() %></h3>
            <h3>Objetivo: <%= v.getObjetivo() %></h3>
            
            <% 
                List<Passageiro> pass = v.getPassageiros(connection);
                for (int i = 0; i < pass.size(); i++) {%>
                <h3>Sequencia: <%= i %> Nome: <%= pass.get(i).getNome() %> Rg: <%= pass.get(i).getRg() %></h3>
            <% }%>
            
        </section>
    </body>
</html>
