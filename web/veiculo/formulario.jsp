<%-- 
    Document   : formulario
    Created on : 08/02/2013, 15:37:43
    Author     : thiago
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.TipoVeiculoDAO"%>
<%@page import="com.model.entity.Veiculo"%>
<%@page import="com.model.dao.VeiculoDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[] {"Administrador"});
    Connection connection = (Connection) request.getAttribute("connection");
    VeiculoDAO vdao = new VeiculoDAO(connection);
    TipoVeiculoDAO tvdao = new TipoVeiculoDAO(connection);
    request.setAttribute("tvdao", tvdao);
    String idVeiculo = request.getParameter("id_veiculo");
    if (idVeiculo != null) {
        request.setAttribute("veiculo", vdao.getById(Integer.parseInt(idVeiculo)));
    } else {
        Veiculo veiculo = new Veiculo();
        request.setAttribute("veiculo", veiculo);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro de Veículos</h1>
        <form action="" method="POST" id="veiculo">
            <input type="hidden" name="id_veiculo" id="id_veiculo" value="${veiculo.getIdVeiculo()}" />
            <table>
                <tbody>
                    <tr>
                        <td>Tipo de Veículo</td>
                        <td><select name="tipo_veiculo">
                                <r:forEach var="tipoVeiculo" items="${tvdao.getTiposVeiculos()}">
                                    <option value="${tipoVeiculo.getIdTipoVeiculo()}"
                                            <r:if test="${tipoVeiculo.getIdTipoVeiculo() == veiculo.getTipoVeiculo().getIdTipoVeiculo()}"> selected="true" </r:if>>${tipoVeiculo.getTipoVeiculo()}</option>
                                </r:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Placa</td>
                        <td><input type="text" name="placa" value="${veiculo.getPlaca()}" /></td>
                    </tr>
                    <tr>
                        <td>Quilometragem</td>
                        <td><input type="number" name="quilometragem" value="${veiculo.getQuilometragem()}" /></td>
                    </tr>
                    <tr>
                        <td>Capacidade de Passageiros</td>
                        <td><input type="number" name="capacidade_passageiro" value="${veiculo.getCapacidadePassageiro()}" /></td>
                    </tr>
                    <tr>
                        <td>Cor</td>
                        <td><input type="text" name="cor" value="${veiculo.getCor()}" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Salvar Veículo" onclick="salvar();"/>
        </form>
        <script type="text/javascript" src="../resources/js/jquery.js"></script>
        <script type="text/javascript">
                function salvar() {
                    event.preventDefault();
                    // get all the inputs into an array.
                    var $inputs = $('#veiculo :input');

                    // not sure if you wanted this, but I thought I'd add it.
                    // get an associative array of just the values.
                    var values = {};
                    $inputs.each(function() {
                        values[this.name] = $(this).val();
                    });
                    console.log(values);

                    /* Send the data using post */
                    $.post("cadastrar.jsp", values, function(data) {
                        alert(data);
                        console.log(data);
                        window.location = "index.jsp";
                        return;
                    }
                    );
                }
                ;
        </script>
    </body>
</html>
