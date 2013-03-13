<%-- 
    Document   : quilometragem
    Created on : 12/03/2013, 15:37:18
    Author     : Usuario
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.model.dao.VeiculoDAO"%>
<%@page import="com.model.entity.Veiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{
        AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    VeiculoDAO vdao = new VeiculoDAO(connection);
    request.setAttribute("vdao", vdao);
%>
<layout:page title="Listagem de Passageiros" description="" keywords="">
<jsp:body>
        <style>
            h1, h4
            {
                margin:10px;
                padding:10px;
            }
        </style>
        <h1>Relatório de Quilometragem</h1>
        <h4><a class="btn btn-primary" href="./passageiro/formulario.jsp"><i class="icon-plus icon-white"></i>  Cadastrar Passageiro</a></h4>
        <table class="datatable table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Tipo</th>
                    <th>Placa</th>
                    <th>Quilometragem</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="veiculo" items="${vdao.getByQuilometragem()}">
                    <tr>
                        <td>${veiculo.getTipoVeiculo().getTipoVeiculo()}</td>
                        <td>${veiculo.getPlaca()}</td>
                        <td>${veiculo.getQuilometragem()}</td>
                        
                    </tr>
                </r:forEach>
            </tbody>
        </table>

    </jsp:body>
</layout:page>
