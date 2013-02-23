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
            new String[] {"Administrador"});
    Connection connection = (Connection) request.getAttribute("connection");
    VeiculoDAO vdao = new VeiculoDAO(connection);
    request.setAttribute("vdao", vdao);
%>
<layout:page title="Listagem de Veículos" description="" keywords="">
    <jsp:body>
        <h1>Veículos</h1>
        <h4><a href="./veiculo/formulario.jsp">Cadastrar Veículo</a></h4>
        <table>
            <thead>
                <tr>
                    <th>Tipo do Veiculo</th>
                    <th>Placa</th>
                    <th>Capacidade de Passageiros</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="veiculo" items="${vdao.getVeiculos()}">
                    <tr>
                        <td>${veiculo.getTipo()}</td>
                        <td>${veiculo.getPlaca()}</td>
                        <td>${veiculo.getCapacidadePassageiro()}</td>
                        <td><a href="./veiculo/formulario.jsp?id_veiculo=${veiculo.getIdVeiculo()}">Editar</a></td>
                        <td><a href="" onclick="excluir(${veiculo.getIdVeiculo()});">Excluir</a></td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
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
    </jsp:body>
</layout:page>
