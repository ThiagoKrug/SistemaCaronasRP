<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.VeiculoDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response,
            new String[]{AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    VeiculoDAO vdao = new VeiculoDAO(connection);
    request.setAttribute("vdao", vdao);
%>
<layout:page description="" keywords="" title="Listagem de Veículos">
    <jsp:attribute name="extraBottom">
        <script type="text/javascript">
            function excluir(idVeiculo) {
                event.preventDefault();

                if (confirm("Deseja realmente excluir este veículo?")) {
                    /* Send the data using post */
                    $.post("./veiculo/excluir.jsp",
                            {id_veiculo: idVeiculo},
                    function(data) {
                        alert(data);
                        console.log(data);
                        window.location = "./veiculo/index.jsp";
                        return;
                    }
                    );
                }
                ;
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <h1>Veículos</h1>
        <h4><a class="btn btn-primary" href="./veiculo/formulario.jsp"><i class="icon-plus icon-white"></i> Cadastrar Veículo</a></h4>
        <table id="veiculos" class="datatable table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Tipo do Veiculo</th>
                    <th>Placa</th>
                    <th>Capacidade de Passageiros</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="veiculo" items="${vdao.getVeiculos()}">
                    <tr>
                        <td>${veiculo.getTipo()}</td>
                        <td>${veiculo.getPlaca()}</td>
                        <td>${veiculo.getCapacidadePassageiro()}</td>
                        <td class="opcoes"><a class="btn btn-warning" href="./veiculo/formulario.jsp?id_veiculo=${veiculo.getIdVeiculo()}"><i class="icon-edit icon-white"></i> Editar</a>
                        <a class="btn btn-danger" href="" onclick="excluir(${veiculo.getIdVeiculo()});" ><i class="icon-remove icon-white"></i> Excluir</a></td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
        <a href="./principal.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
    </jsp:body>
</layout:page>
