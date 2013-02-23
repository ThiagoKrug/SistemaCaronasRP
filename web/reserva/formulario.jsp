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
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{"Administrador", "Servidor Solicitante"});
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
        <h1>Solicitação de Reserva</h1>
        <form class="form-horizontal" action="" method="POST" id="solicitacao_viagem">
            <input type="hidden" name="id_solicitacao_viagem" id="id_solicitacao_viagem" value="${solicitacaoViagem.getIdSolicitacaoViagem()}" />
            <table>
                <tbody>
                    <tr>
                        <td>Número do Pedido</td>
                        <td><input type="text" name="numero_pedido" value="${solicitacaoViagem.getNumeroPedido()}" /></td>
                    </tr>
                    <tr>
                        <td>Servidor Solicitante</td>
                        <td><select name="solicitante">
                                <r:forEach var="usuario" items="${udao.getUsuarios()}">
                                    <option value="${usuario.getIdUsuario()}"
                                            <r:if test="${usuario.getIdUsuario() == solicitacaoViagem.getSolicitante().getIdUsuario()}"> selected="true" </r:if>>${usuario.getNome()}</option>
                                </r:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Número de passageiros</td>
                        <td><input type="text" name="numero_transportados" value="${solicitacaoViagem.getNumeroTransportados()}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><center>Informe os passageiros</center></td>
                </tr>
                <input type="hidden" name="id_passageiro" id="id_passageiro" />
                <tr>
                    <td>Nome do Passageiro</td>
                    <td><input type="text" name="nome_passageiro" id="nome_passageiro" onblur='completeFields($("#nome_passageiro").val());' onkeyup='completeFields($("#nome_passageiro").val());' /><a class="btn btn-primary" onclick='addPassageiro($("#id_passageiro").val(), $("#rg_passageiro").val(), $("#nome_passageiro").val());'>Adicionar Passageiro</a></td>
                </tr>
                <tr>
                    <td>RG do Passageiro</td>
                    <td><input type="text" name="rg_passageiro" id="rg_passageiro" /></td>
                </tr>
                <tr>
                    <td>Telefone do Passageiro</td>
                    <td><input type="text" name="telefone_passageiro" id="telefone_passageiro"/></td>
                </tr>
                <tr>
                    <td>Endereço do Passageiro</td>
                    <td><input type="text" name="endereco_passageiro" id="endereco_passageiro" /></td>
                </tr>
                <tr>
                    <td>Passageiros adicionados na reserva</td>
                    <td><select multiple="true" name="passageiros" id="passageiros">
                            <r:forEach var="passageiro" items="${solicitacaoViagem.getPassageiros()}">
                                <option value="${passageiro.getIdPassageiro()}">${passageiro.getNome()} - ${passageiro.getRg()}</option>
                            </r:forEach>
                        </select>
                        <a class="btn btn-danger" onclick="retirarPassageiros();">Retirar passageiro</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><label>Os passageiros são servidores da Unipampa?</label><br/>
                        <input type="radio" name="servidores" value="true" checked="checked">Sim
                        <input type="radio" name="servidores" value="false">Não
                    </td>
                </tr>
                <tr>
                    <td>Data de Saída</td>
                    <td><input type="text" id="datepicker_saida" name="data_saida" value="${solicitacaoViagem.getDataSaidaFormatada()}" /></td>
                </tr>
                <tr>
                    <td>Hora de Saída</td>
                    <td><input type="time" name="hora_saida" value="${solicitacaoViagem.getHoraSaidaFormatada()}" /></td>
                </tr>
                <tr>
                    <td>Local de Saída</td>
                    <td><input type="text" name="local_saida" value="${solicitacaoViagem.getLocalSaida()}" /></td>
                </tr>
                <tr>
                    <td>Data de Retorno</td>
                    <td><input type="text" id="datepicker_retorno" name="data_retorno" value="${solicitacaoViagem.getDataRetornoFormatada()}" /></td>
                </tr>
                <tr>
                    <td>Hora de Retorno</td>
                    <td><input type="time" name="hora_retorno" value="${solicitacaoViagem.getHoraRetornoFormatada()}" /></td>
                </tr>
                <tr>
                    <td>Local de Retorno</td>
                    <td><input type="text" name="local_retorno" value="${solicitacaoViagem.getLocalRetorno()}" /></td>
                </tr>
                <tr>
                    <td>Percurso</td>
                    <td><textarea name="percurso">${solicitacaoViagem.getPercurso()}</textarea></td>
                </tr>
                <tr>
                    <td>Objetivo da Viagem</td>
                    <td><textarea name="objetivo">${solicitacaoViagem.getObjetivo()}</textarea></td>
                </tr>
                <tr>
                    <td>Tipo de Veículo</td>
                    <td><select name="tipo_veiculo" required="true">
                            <r:forEach var="tipoVeiculo" items="${tvdao.getTiposVeiculos()}">
                                <option value="${tipoVeiculo.getIdTipoVeiculo()}"
                                        <r:if test="${tipoVeiculo.getIdTipoVeiculo() == solicitacaoViagem.getTipoVeiculo().getIdTipoVeiculo()}"> selected="true" </r:if>>${tipoVeiculo.getTipoVeiculo()}</option>
                            </r:forEach>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
            <input class="btn btn-success" type="submit" value="Solicitar Reserva" onclick="salvar();"/>
        </form>
    </jsp:body>
</layout:page>
