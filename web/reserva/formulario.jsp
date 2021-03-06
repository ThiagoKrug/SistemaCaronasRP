<%-- 
    Document   : formulario
    Created on : 08/02/2013, 15:37:43
    Author     : thiago
--%>
<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.TipoVeiculoDAO"%>
<%@page import="com.model.entity.Passageiro"%>
<%@page import="com.model.dao.PassageiroDAO"%>
<%@page import="com.model.dao.UsuarioDAO"%>
<%@page import="com.model.entity.SolicitacaoViagem"%>
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
    UsuarioDAO udao = new UsuarioDAO(connection);
    request.setAttribute("udao", udao);
    PassageiroDAO pdao = new PassageiroDAO(connection);
    request.setAttribute("pdao", pdao);
    TipoVeiculoDAO tvdao = new TipoVeiculoDAO(connection);
    request.setAttribute("tvdao", tvdao);
    String idSolicitacaoViagem = request.getParameter("id_solicitacao_viagem");
    if (idSolicitacaoViagem != null) {
        request.setAttribute("solicitacaoViagem", svdao.getById(Integer.parseInt(idSolicitacaoViagem)));
    } else {
        SolicitacaoViagem solicitacaoViagem = new SolicitacaoViagem();
        request.setAttribute("solicitacaoViagem", solicitacaoViagem);
    }
%>
<layout:page description="" keywords="" title="Solicitação de Reserva">
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
                var $inputs = $('#solicitacao_viagem :input');
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
                $.post("./reserva/cadastrar.jsp", values, function(data) {
                    alert(data);
                    console.log(data);
                    window.location = "./reserva/index.jsp";
                    return;
                }
                );
            }
            ;
            $(function() {
                $.datepicker.setDefaults($.datepicker.regional[ "pt-BR" ]);
                $("#datepicker_saida").datepicker();
                $("#datepicker_retorno").datepicker();
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
                        $("#id_passageiro").val(passageiro.idPassageiro);
                        $("#rg_passageiro").val(passageiro.rg);
                        $("#rg_passageiro").attr('disabled', "true");
                        $("#telefone_passageiro").val(passageiro.telefone);
                        $("#telefone_passageiro").attr('disabled', "true");
                        $("#endereco_passageiro").val(passageiro.endereco);
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
        <form class="form-horizontal" action="" method="POST" id="solicitacao_viagem">
            <fieldset>
                <div id="legend">
                    <legend class="">Solicitação de Reserva</legend>
                </div>
                <input type="hidden" name="id_solicitacao_viagem" id="id_solicitacao_viagem" value="${solicitacaoViagem.getIdSolicitacaoViagem()}" />
                <div class="control-group">
                    <label class="control-label" for="numero_pedido">Número do Pedido</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="numero_pedido" value="${solicitacaoViagem.getNumeroPedido()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="solicitante">Servidor Solicitante</label>
                    <div class="controls">
                        <select name="solicitante" class="input-xlarge">
                            <r:forEach var="usuario" items="${udao.getUsuarios()}">
                                <option value="${usuario.getIdUsuario()}"
                                        <r:if test="${usuario.getIdUsuario() == solicitacaoViagem.getSolicitante().getIdUsuario()}"> selected="true" </r:if>>${usuario.getNome()}</option>
                            </r:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="numero_transportados">Número de passageiros</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="numero_transportados" value="${solicitacaoViagem.getNumeroTransportados()}" />
                    </div>
                </div>
                <div class="well">
                    <div id="legend">
                        <legend>Informe os passageiros</legend>
                    </div>
                    <input type="hidden" name="id_passageiro" id="id_passageiro" />
                    <div class="control-group">
                        <label class="control-label" for="nome_passageiro">Nome do Passageiro</label>
                        <div class="controls">
                            <input class="input-xlarge" type="text" name="nome_passageiro" id="nome_passageiro" onblur='completeFields($("#nome_passageiro").val());' onkeyup='completeFields($("#nome_passageiro").val());' /><a class="btn btn-primary" onclick='addPassageiro($("#id_passageiro").val(), $("#rg_passageiro").val(), $("#nome_passageiro").val());'>Adicionar Passageiro</a>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="rg_passageiro">RG do Passageiro</label>
                        <div class="controls">
                            <input class="input-xlarge" type="text" name="rg_passageiro" id="rg_passageiro" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="telefone_passageiro">Telefone do Passageiro</label>
                        <div class="controls">
                            <input class="input-xlarge" type="text" name="telefone_passageiro" id="telefone_passageiro" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="endereco_passageiro">Endereço do Passageiro</label>
                        <div class="controls">
                            <input class="input-xlarge" type="text" name="endereco_passageiro" id="endereco_passageiro" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="passageiros">Passageiros adicionados na reserva</label>
                        <div class="controls">
                            <select multiple="multiple" name="passageiros" id="passageiros" class="input-xxlarge">
                                <r:forEach var="passageiro" items="${solicitacaoViagem.getPassageiros()}">
                                    <option value="${passageiro.getIdPassageiro()}">${passageiro.getNome()} - ${passageiro.getRg()}</option>
                                </r:forEach>
                            </select>
                            <a class="btn btn-danger" onclick="retirarPassageiros();">Retirar passageiro</a>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">Os passageiros são servidores da Unipampa?</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="servidores" value="true" checked="checked">Sim
                        </label>
                        <label class="radio">
                            <input type="radio" name="servidores" value="false">Não
                        </label>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="data_saida">Data de Saída</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="data_saida" id="datepicker_saida" value="${solicitacaoViagem.getDataSaidaFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="data_saida">Hora de Saída</label>
                    <div class="controls">
                        <input class="input-xlarge" type="time" name="hora_saida" value="${solicitacaoViagem.getHoraSaidaFormatada()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="local_saida">Local de Saída</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="local_saida" value="${solicitacaoViagem.getLocalSaida()}" />
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
                        <textarea name="objetivo" class="input-xlarge" rows="4">${solicitacaoViagem.getObjetivo()}</textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="tipo_veiculo">Tipo de Veículo</label>
                    <div class="controls">
                        <select name="tipo_veiculo" class="input-xlarge" required="true">
                            <r:forEach var="tipoVeiculo" items="${tvdao.getTiposVeiculos()}">
                                <option value="${tipoVeiculo.getIdTipoVeiculo()}"
                                        <r:if test="${tipoVeiculo.getIdTipoVeiculo() == solicitacaoViagem.getTipoVeiculo().getIdTipoVeiculo()}"> selected="true" </r:if>>${tipoVeiculo.getTipoVeiculo()}</option>
                            </r:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <a href="./reserva/index.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
                        <button class="btn btn-success" type="submit" onclick="salvar();" ><i class="icon-ok icon-white"></i> Solicitar Reserva</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </jsp:body>
</layout:page>
