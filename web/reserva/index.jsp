<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.SolicitacaoViagemDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker().authenticate(session, response, new String[] {"Administrador",
    "Servidor Solicitante"});
    Connection connection = (Connection) request.getAttribute("connection");
    SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
    request.setAttribute("svdao", svdao);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reserva</h1>
        <h4><a href="formulario.jsp">Cadastrar Reserva</a></h4>
        <table>
            <thead>
                <tr>
                    <th>Número do Pedido</th>
                    <th>Solicitante</th>
                    <th>Tipo de Veículo</th>
                    <th>Data de Saída</th>
                    <th>Data de Retorno</th>
                    <th>Status</th>
                    <th colspan="3">Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="solicitacaoViagem" items="${svdao.getSolicitacoes()}">
                    <tr>
                        <td>${solicitacaoViagem.getNumeroPedido()}</td>
                        <td>${solicitacaoViagem.getSolicitante().getNome()}</td>
                        <td>${solicitacaoViagem.getTipoVeiculo().getTipoVeiculo()}</td>
                        <td>${solicitacaoViagem.getDataSaidaFormatada()}</td>
                        <td>${solicitacaoViagem.getDataRetornoFormatada()}</td>
                        <td>${solicitacaoViagem.getStatus()}</td>
                        <r:choose>
                            <r:when test="${solicitacaoViagem.getStatus() == 'efetivado'}">
                                <td><a href="" onclick="cancelar(${solicitacaoViagem.getIdSolicitacaoViagem()})">Cancelar Solicitação</a></td>
                            </r:when>
                            <r:when test="${solicitacaoViagem.getStatus() == 'solicitado'}">
                                <td><a href="" onclick="efetivar(${solicitacaoViagem.getIdSolicitacaoViagem()})">Efetivar Solicitação</a></td>
                            </r:when>
                            <r:otherwise><td></td></r:otherwise>
                        </r:choose>
                        <td><a href="formulario.jsp?id_solicitacao_viagem=${solicitacaoViagem.getIdSolicitacaoViagem()}">Editar</a></td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
        <script type="text/javascript" src="../resources/js/jquery.js"></script>
        <script type="text/javascript">
                                    function excluir(idSolicitacaoViagem) {
                                        event.preventDefault();

                                        if (confirm("Deseja realmente excluir esta reserva?")) {
                                            /* Send the data using post */
                                            $.post("excluir.jsp",
                                                    {id_solicitacao_viagem: idSolicitacaoViagem},
                                            function(data) {
                                                alert(data);
                                                console.log(data);
                                                window.location = "index.jsp";
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
                                            $.post("cancelar.jsp",
                                                    {id_solicitacao_viagem: idSolicitacaoViagem},
                                            function(data) {
                                                alert(data);
                                                console.log(data);
                                                window.location = "index.jsp";
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
                                            $.post("efetivar.jsp",
                                                    {id_solicitacao_viagem: idSolicitacaoViagem},
                                            function(data) {
                                                alert(data);
                                                console.log(data);
                                                window.location = "index.jsp";
                                                return;
                                            }
                                            );
                                        }
                                        ;
                                    }
                                    ;
        </script>
    </body>
</html>