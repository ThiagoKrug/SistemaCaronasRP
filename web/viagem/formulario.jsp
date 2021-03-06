<%-- 
    Document   : formulario
    Created on : 13/03/2013, 21:05:06
    Author     : Usuario
--%>

<%@page import="com.model.entity.StatusSolicitacaoViagem"%>
<%@page import="com.model.dao.SolicitacaoViagemDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.auth.AuthChecker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{
        AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
    request.setAttribute("svdao", svdao);
    request.setAttribute("cancelado", StatusSolicitacaoViagem.CANCELADO.toString());
    request.setAttribute("efetivado", StatusSolicitacaoViagem.EFETIVADO.toString());
    request.setAttribute("solicitado", StatusSolicitacaoViagem.SOLICITADO.toString());
    request.setAttribute("admin", AuthChecker.ADMIN.toString());
%>
<layout:page title="Listagem de Reservas" description="" keywords="">
    <jsp:body>
        <h1>Reservas</h1>
        <h4>Selecione as solicitações de viagem para efetivar.</h4>
        <form action="./viagem/formulario2.jsp" method="post">
            <input type="submit" value="Criar Viagem" class="btn btn-primary"/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Marcar</th>
                        <th>Número do Pedido</th>
                        <th>Solicitante</th>
                        <th>Tipo de Veículo</th>
                        <th>Data de Saída</th>
                        <th>Data de Retorno</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <r:forEach var="solicitacaoViagem" items="${svdao.getSolicitacoes()}">
                        <r:if test="${sessionScope.Clearance == admin
                                      or sessionScope.Username == solicitacaoViagem.getSolicitante().getUsername()}">
                              <tr <c:choose>
                                      <c:when test="${solicitacaoViagem.getStatus() == efetivado}"> class="success" </c:when>
                                      <c:when test="${solicitacaoViagem.getStatus() == cancelado}"> class="error" </c:when>
                                  </c:choose>>
                                  <td class="opcoes">
                                      <input type="checkbox" name="solicitacaoViagem${solicitacaoViagem.getIdSolicitacaoViagem()}" value="${solicitacaoViagem.getIdSolicitacaoViagem()}" />
                                  </td>
                                  <td>${solicitacaoViagem.getNumeroPedido()}</td>
                                  <td>${solicitacaoViagem.getSolicitante().getNome()}</td>
                                  <td>${solicitacaoViagem.getTipoVeiculo().getTipoVeiculo()}</td>
                                  <td>${solicitacaoViagem.getDataSaidaFormatada()}</td>
                                  <td>${solicitacaoViagem.getDataRetornoFormatada()}</td>
                                  <td>${solicitacaoViagem.getStatus()}</td>
                              </tr>
                        </r:if>
                    </r:forEach>
                </tbody>
            </table>
        </form>
        <a href="./viagem/index.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
    </jsp:body>
</layout:page>
