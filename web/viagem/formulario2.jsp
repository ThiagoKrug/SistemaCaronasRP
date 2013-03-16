<%-- 
    Document   : formulario2
    Created on : 14/03/2013, 20:08:48
    Author     : Usuario
--%>

<%@page import="com.model.entity.Viagem"%>
<%@page import="com.model.dao.ViagemDAO"%>
<%@page import="com.model.dao.VeiculoDAO"%>
<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.entity.Passageiro"%>
<%@page import="com.model.dao.PassageiroDAO"%>
<%@page import="com.model.dao.UsuarioDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{
        AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    ViagemDAO vidao = new ViagemDAO(connection);
    UsuarioDAO udao = new UsuarioDAO(connection);
    request.setAttribute("udao", udao);
    PassageiroDAO pdao = new PassageiroDAO(connection);
    request.setAttribute("pdao", pdao);
    VeiculoDAO vdao = new VeiculoDAO(connection);
    request.setAttribute("vdao", vdao);
    String idViagem = request.getParameter("id_viagem");
    if (idViagem != null) {
        request.setAttribute("viagem", vidao.getById(Integer.parseInt(idViagem)));
    } else {
        Viagem viagem = new Viagem();
        request.setAttribute("viagem", viagem);
    }
%>
<layout:page description="" keywords="" title="Criar Viagem">
    <jsp:attribute name="extraBottom">
        <script type="text/javascript" src="./resources/js/jquery-ui-1.10.1.custom.min.js"></script>
        <script type="text/javascript" src="./resources/js/ui.datepicker-pt-BR.js"></script>
        <script type="text/javascript" src="./resources/js/passageiro.js"></script>
        <script type="text/javascript" src="./resources/js/json2.js"></script>
        <script type="text/javascript">
            var cont = 0;
            var listaPassageiros = new Array();
            function salvar() {
                event.preventDefault();
                // get all the inputs into an array.
                var $inputs = $('#viagem :input');
                // not sure if you wanted this, but I thought I'd add it.
                // get an associative array of just the values.
                var values = {};
                $inputs.each(function() {
                    values[this.name] = $(this).val();
                });
                for (x in listaPassageiros) {
                    if (listaPassageiros[x].idPassageiro[0] == "a") {
                        listaPassageiros[x].idPassageiro = null;
                    }
                }
                values.passageiros = JSON.stringify(listaPassageiros);
                console.log(values);
                /* Send the data using post */
                $.post("./viagem/cadastrar.jsp", values, function(data) {
                    alert(data);
                    console.log(data);
                    window.location = "./viagem/index.jsp";
                    return;
                }
                );
            }
            ;
            $(function() {
                $.datepicker.setDefaults($.datepicker.regional[ "pt-BR" ]);
                $("#datepicker_saida").datepicker();
                $("#datepicker_retorno").datepicker();
                $("#datepicker_efetivacao").datepicker();
            });
            $(function() {
                $("#nome_passageiro").autocomplete({
                    source: getNomes()
                });
            });
            function getNomes() {
                var availableNames = [
            <r:forEach var="passageiro" items="${pdao.getPassageiros()}">
                    "${passageiro.getNome()}",</r:forEach>
                ];
                return availableNames;
            }
            ;
            function getPassageiros() {
                var passageiros = new Array();
                var passageiro;
            <r:forEach var="passageiro" items="${pdao.getPassageiros()}">
                passageiro = new Passageiro(${passageiro.getIdPassageiro()},
                        "${passageiro.getNome()}",
                        "${passageiro.getRg()}",
                        "${passageiro.getTelefone()}",
                        "${passageiro.getEndereco()}");
                passageiros.push(passageiro);
            </r:forEach>
                    return passageiros;
                }
                ;
                function completeFields(nome) {
                    event.preventDefault();
                    var passageiro;
                    var achou = false;
                    var passageiros = getPassageiros();
                    for (x in passageiros) {
                        if (passageiros[x].nome == nome) {
                            passageiro = passageiros[x];
                            achou = true;
                        }
                    }
                    if (achou) {
                        $("#id_passageiro").val(passageiros[x].idPassageiro);
                        $("#rg_passageiro").val(passageiros[x].rg);
                        $("#rg_passageiro").attr('disabled', "true");
                        $("#telefone_passageiro").val(passageiros[x].telefone);
                        $("#telefone_passageiro").attr('disabled', "true");
                        $("#endereco_passageiro").val(passageiros[x].endereco);
                        $("#endereco_passageiro").attr('disabled', "true");
                    } else {
                        $("#id_passageiro").val('');
                        $("#rg_passageiro").removeAttr('disabled');
                        $("#rg_passageiro").val('');
                        $("#telefone_passageiro").removeAttr('disabled');
                        $("#telefone_passageiro").val('');
                        $("#endereco_passageiro").removeAttr('disabled');
                        $("#endereco_passageiro").val('');
                    }
                }
                ;
                function addPassageiro(id, rg, nome) {
                    var adicionar = true;
                    if (nome == "") {
                        adicionar = false;
                    }
                    //if (id != "") {
                    $("#passageiros option").each(function() {
                        var str = $(this).html().split(" - ");
                        console.log(str[1]);
                        if (str[1] == rg) {
                            adicionar = false;
                        }
                    });
                    //}
                    if (adicionar) {
                        cont++;
                        if (id == "") {
                            id = "a" + cont;
                        }
                        $("#passageiros").append('<option value="' + id + '">' + nome + ' - ' + rg + '</option>');
                        var passageiro = new Passageiro(id,
                                $("#nome_passageiro").val(),
                                $("#rg_passageiro").val(),
                                $("#telefone_passageiro").val(),
                                $("#endereco_passageiro").val());
                        listaPassageiros.push(passageiro);
                    } else if (nome == "") {
                        // o cara naum digito nada no nome do passageiro
                    } else {
                        alert("Passageiro já informado.");
                    }
                }
                ;
                function retirarPassageiros() {
                    $("#passageiros option:selected").each(function() {
                        for (x in listaPassageiros) {
                            if (listaPassageiros[x].idPassageiro == $(this).val()) {
                                listaPassageiros.splice(x, 1);
                            }
                        }
                        $(this).remove();
                    });
                }
                ;
        </script>
    </jsp:attribute>
    <jsp:body>
        <form class="form-horizontal" action="" method="POST" id="viagem">
            <fieldset>
                <div id="legend">
                    <legend class="">Criar Viagem</legend>
                </div>
                <input type="hidden" name="id_viagem" id="id_viagem" value="${viagem.getIdViagem()}" />
                <div class="control-group">
                    <label class="control-label" for="data_saida">Data de Saída</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="data_saida" id="datepicker_saida" value="${viagem.getDataSaidaFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="data_saida">Hora de Saída</label>
                    <div class="controls">
                        <input class="input-xlarge" type="time" name="hora_saida" value="${viagem.getHoraSaidaFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="local_saida">Local de Saída</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="local_saida" value="${viagem.getLocalSaida()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="data_retorno">Data de Retorno</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="data_retorno" id="datepicker_retorno" value="${solicitacaoViagem.getDataRetornoFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="data_saida">Hora de Retorno</label>
                    <div class="controls">
                        <input class="input-xlarge" type="time" name="hora_retorno" value="${solicitacaoViagem.getHoraRetornoFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="local_saida">Local de Retorno</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="local_retorno" value="${solicitacaoViagem.getLocalRetorno()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="percurso">Percurso</label>
                    <div class="controls">
                        <textarea name="percurso" class="input-xlarge" rows="4">${solicitacaoViagem.getPercurso()}</textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="objetivo">Objetivo da Viagem</label>
                    <div class="controls">
                        <textarea name="objetivo" class="input-xlarge" rows="4">${viagem.getObjetivo()}</textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="veiculo">Veículo</label>
                    <div class="controls">
                        <select name="veiculo" class="input-xlarge" required="true">
                            <r:forEach var="veiculo" items="${vdao.getVeiculos()}">
                                <option value="${veiculo.getIdVeiculo()}"
                                        <r:if test="${veiculo.getIdVeiculo() == viagem.getVeiculo().getIdVeiculo()}"> selected="true" </r:if>>${veiculo.getTipoVeiculo().getTipoVeiculo()} - ${veiculo.getPlaca()}</option>
                            </r:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="motorista">Motorista</label>
                    <div class="controls">
                        <select name="motorista" class="input-xlarge" required="true">
                            <r:forEach var="motorista" items="${udao.getMotoristas()}">
                                <option value="${motorista.getIdUsuario()}"
                                        <r:if test="${motorista.getIdUsuario() == viagem.getMotorista().getIdUsuario()}"> selected="true" </r:if>>${motorista.getNome()}</option>
                            </r:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="autorizante">Autorizante</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="autorizante" value="${viagem.getAutorizante()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="data_efetivacao">Data da Efetivação</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="data_efetivacao" id="datepicker_efetivacao" value="${viagem.getDataEfetivacaoFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <a href="./viagem/index.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
                        <button class="btn btn-success" type="submit" onclick="salvar();" ><i class="icon-ok icon-white"></i> Criar Viagem</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </jsp:body>
</layout:page>
