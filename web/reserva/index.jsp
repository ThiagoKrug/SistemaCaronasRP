<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="com.model.entity.StatusSolicitacaoViagem"%>
<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.SolicitacaoViagemDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{
        AuthChecker.ADMIN, AuthChecker.SERVIDOR});
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
        <h4><a class="btn btn-primary" href="./reserva/formulario.jsp"><i class="icon-plus icon-white"></i> Cadastrar Reserva</a></h4>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Número do Pedido</th>
                    <th>Solicitante</th>
                    <th>Tipo de Veículo</th>
                    <th>Data de Saída</th>
                    <th>Data de Retorno</th>
                    <th>Status</th>
                    <th>Opções</th>
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
                              <td>${solicitacaoViagem.getNumeroPedido()}</td>
                              <td>${solicitacaoViagem.getSolicitante().getNome()}</td>
                              <td>${solicitacaoViagem.getTipoVeiculo().getTipoVeiculo()}</td>
                              <td>${solicitacaoViagem.getDataSaidaFormatada()}</td>
                              <td>${solicitacaoViagem.getDataRetornoFormatada()}</td>
                              <td>${solicitacaoViagem.getStatus()}</td>
                              <td class="opcoes">
                                  <r:if test="${solicitacaoViagem.getStatus() == solicitado and
                                                sessionScope.Clearance == admin}">
                                      <a class="btn btn-warning" href="./reserva/formulario.jsp?id_solicitacao_viagem=${solicitacaoViagem.getIdSolicitacaoViagem()}"><i class="icon-edit icon-white"></i> Editar</a>
                                  </r:if>
                                  <r:if test="${solicitacaoViagem.getStatus() == efetivado
                                                and (sessionScope.Clearance == admin
                                                or sessionScope.Username == solicitacaoViagem.getSolicitante().getUsername())}">
                                        <a class="btn btn-inverse" href="" onclick="cancelar(${solicitacaoViagem.getIdSolicitacaoViagem()});"><i class="icon-minus-sign icon-white"></i> Cancelar Solicitação</a>
                                  </r:if>
                                  <r:if test="${solicitacaoViagem.getStatus() != efetivado and (sessionScope.Clearance == admin
                                                or sessionScope.Username == solicitacaoViagem.getSolicitante().getUsername())}">
                                        <a class="btn btn-danger" href="" onclick="excluir(${solicitacaoViagem.getIdSolicitacaoViagem()});"><i class="icon-remove icon-white"></i> Excluir</a>
                                  </r:if>

                              </td>
                          </tr>
                    </r:if>
                </r:forEach>
            </tbody>
        </table>
        <a href="./principal.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
        <script type="text/javascript">
                                            function excluir(idSolicitacaoViagem) {
                                                event.preventDefault();

                                                if (confirm("Deseja realmente excluir esta reserva?")) {
                                                    /* Send the data using post */
                                                    $.post("./reserva/excluir.jsp",
                                                            {id_solicitacao_viagem: idSolicitacaoViagem},
                                                    function(data) {
                                                        alert(data);
                                                        console.log(data);
                                                        window.location = "./reserva/index.jsp";
                                                        return;
                                                    }
                                                    );
                                                }
                                                ;
                                            }
                                            ;

                                            function cancelar(idSolicitacaoViagem) {
                                                event.preventDefault();

                                                if (confirm("Deseja realmente cancelar esta reserva?")) {
                                                    /* Send the data using post */
                                                    $.post("./reserva/cancelar.jsp",
                                                            {id_solicitacao_viagem: idSolicitacaoViagem},
                                                    function(data) {
                                                        alert(data);
                                                        console.log(data);
                                                        window.location = "./reserva/index.jsp";
                                                        return;
                                                    }
                                                    );
                                                }
                                                ;
                                            }
                                            ;

                                            function efetivar(idSolicitacaoViagem) {
                                                event.preventDefault();

                                                if (confirm("Deseja realmente efetivar esta reserva?")) {
                                                    /* Send the data using post */
                                                    $.post("./reserva/efetivar.jsp",
                                                            {id_solicitacao_viagem: idSolicitacaoViagem},
                                                    function(data) {
                                                        alert(data);
                                                        console.log(data);
                                                        window.location = "./reserva/index.jsp";
                                                        return;
                                                    }
                                                    );
                                                }
                                                ;
                                            }
                                            ;
        </script>
    </jsp:body>
</layout:page>
