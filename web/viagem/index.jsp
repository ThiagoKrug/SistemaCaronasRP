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
                    <th>Data de Efetivação</th>
                    <th>Motorista</th>
                    <th>Veículo</th>
                    <th>Data de Saída</th>
                    <th>Data de Retorno</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="viagem" items="${vdao.getViagens()}">
                    <tr>
                        <td>${viagem.getDataEfetivacaoFormatada()}</td>
                        <td>${viagem.getMotorista(connection).getNome()}</td>
                        <td>${viagem.getVeiculo(connection).getTipoVeiculo().getTipoVeiculo()} - ${viagem.getVeiculo().getPlaca()}</td>
                        <td>${viagem.getDataSaidaFormatada()}</td>
                        <td>${viagem.getDataRetornoFormatada()}</td>
                        <td class="opcoes">

                            <a class="btn btn-primary" href="./viagem/relat.jsp?id=${viagem.getIdViagem()}"><i class="icon-print icon-white"></i> Relatório</a>
                            <a class="btn btn-warning" href="./viagem/formulario2.jsp?id_viagem=${viagem.getIdViagem()}"><i class="icon-pencil icon-white"></i>Editar</a>
                        </td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
        <a href="./principal.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
        <script type="text/javascript">
                                function excluir(idViagem) {
                                    event.preventDefault();

                                    if (confirm("Deseja realmente excluir esta reserva?")) {
                                        /* Send the data using post */
                                        $.post("./viagem/excluir.jsp",
                                                {id_viagem: idViagem},
                                        function(data) {
                                            alert(data);
                                            console.log(data);
                                            window.location = "./viagem/index.jsp";
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
