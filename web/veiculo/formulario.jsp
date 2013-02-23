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
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{"Administrador"});
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
<layout:page description="" keywords="" title="Cadastro de Veículo">
    <jsp:body>
        <form class="form-horizontal" action="" method="POST" id="veiculo">
            <fieldset>
                <div id="legend">
                    <legend class="">Cadastro de Veiculo</legend>
                </div>
                <input type="hidden" name="id_veiculo" id="id_veiculo" value="${veiculo.getIdVeiculo()}" />
                <div class="control-group">
                    <label class="control-label" for="tipoVeiculo">Tipo de Veículo</label>
                    <div class="controls">
                        <select name="tipo_veiculo" class="input-xlarge">
                            <r:forEach var="tipoVeiculo" items="${tvdao.getTiposVeiculos()}">
                                <option value="${tipoVeiculo.getIdTipoVeiculo()}"
                                        <r:if test="${tipoVeiculo.getIdTipoVeiculo() == veiculo.getTipoVeiculo().getIdTipoVeiculo()}"> selected="true" </r:if>>${tipoVeiculo.getTipoVeiculo()}</option>
                            </r:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="placa">Placa</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="placa" value="${veiculo.getPlaca()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="quilometragem">Quilometragem</label>
                    <div class="controls">
                        <input class="input-xlarge" type="number" name="quilometragem" value="${veiculo.getQuilometragem()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="capacidadePassageiros">Capacidade de Passageiros</label>
                    <div class="controls">
                        <input class="input-xlarge" type="number" name="capacidade_passageiro" value="${veiculo.getCapacidadePassageiro()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="cor">Cor</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="cor" value="${veiculo.getCor()}" />
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <a href="./veiculo/index.jsp" class="btn btn-info">Voltar</a>
                        <input class="btn btn-success" type="submit" value="Salvar Veículo" onclick="salvar();"/>
                    </div>
                </div>
            </fieldset>
        </form>
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
                        $.post("./veiculo/cadastrar.jsp", values, function(data) {
                            alert(data);
                            console.log(data);
                            window.location = "./veiculo/index.jsp";
                            return;
                        }
                        );
                    }
                    ;
        </script>
    </jsp:body>
</layout:page>
