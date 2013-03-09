<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

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
%>
<layout:page title="Listagem de Viagens" description="" keywords="">
    <jsp:body>
        <h1>Viagens</h1>
        <h4><a class="btn btn-primary" href="./viagem/formulario.jsp"><i class="icon-plus icon-white"></i> Criar Viagem</a></h4>
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
                <r:forEach var="viagem" items="${vdao.getViagens()}">
                    <tr>
                        <td>${viagem.getDataEfetivacaoFormatada()}</td>
                        <td>${viagem.getMotorista().getNome()}</td>
                        <td>${viagem.getVeiculo().getTipoVeiculo()}</td>
                        <td>${viagem.getDataSaidaFormatada()}</td>
                        <td>${viagem.getDataRetornoFormatada()}</td>
                        <td>${viagem.getStatus()}</td>
                        <td class="opcoes">
                            <a class="btn btn-danger" href="" onclick="excluir(${solicitacaoViagem.getIdSolicitacaoViagem()});"><i class="icon-remove icon-white"></i> Excluir</a>
                        </td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
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
