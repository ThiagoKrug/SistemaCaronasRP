<%-- 
    Document   : formulario2
    Created on : 14/03/2013, 20:08:48
    Author     : Usuario
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.model.entity.SolicitacaoViagem"%>
<%@page import="com.model.dao.SolicitacaoViagemDAO"%>
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

    if (request.getMethod().equalsIgnoreCase("post")) {
        List<SolicitacaoViagem> solicitacoes = new ArrayList<SolicitacaoViagem>();
        List<Passageiro> passageiros = new ArrayList<Passageiro>();
        SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
        for (SolicitacaoViagem sv : svdao.getSolicitacoes()) {
            if (request.getParameter("solicitacaoViagem" + sv.getIdSolicitacaoViagem()) != null) {
                solicitacoes.add(sv);
                passageiros.addAll(sv.getPassageiros());
            }
        }
        request.setAttribute("solicitacoes", solicitacoes);
        request.setAttribute("passageiros", passageiros);
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
                function addPassageiro(id, nome, rg, telefone, endereco) {
                    var adicionar = true;
                    $("#passageiros tr").each(function() {
                        if ($(this).find("input").val() == id) {
                            adicionar = false;
                        }
                    });

                    if (adicionar) {
                        $("#passageiros tbody").append("<tr>" +
                                "<td>" + nome + "</td>" +
                                "<td>" + rg + "</td>" +
                                "<td>" + telefone + "</td>" +
                                "<td>" + endereco + "</td>" +
                                "<td class='opcoes'><a class='btn btn-danger' onclick='retirarPassageiros(" + id + ");'><i class='icon-remove icon-white'></i></a></td>" +
                                "<input type='hidden' name='passageiro" + id + "' value='" + id + "' />" +
                                "</tr>");
                    } else {
                        alert("Passageiro já adicionado!");
                    }
                }
                ;
                function retirarPassageiros(id) {
                    $("#passageiros tr").each(function() {
                        //console.log($(this).find("input"));
                        if ($(this).find("input").val() == id) {
                            $(this).remove();
                        }
                    });
                }
                ;
        </script>
    </jsp:attribute>
    <jsp:body>
        <form class="form-horizontal" action="" method="POST" id="viagem">
            <fieldset>
                <div class="well" style="background-color: white">
                    <div id="legend">
                        <legend class="">Solicitações Selecionadas</legend>
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Número do Pedido</th>
                                <th>Solicitante</th>
                                <th>Tipo de Veículo</th>
                                <th>Data de Saída</th>
                                <th>Data de Retorno</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <r:forEach var="solicitacaoViagem" items="${solicitacoes}">
                                <r:if test="${sessionScope.Clearance == admin
                                              or sessionScope.Username == solicitacaoViagem.getSolicitante().getUsername()}">
                                      <tr <c:choose>
                                              <c:when test="${solicitacaoViagem.getStatus() == efetivado}"> class="success" </c:when>
                                              <c:when test="${solicitacaoViagem.getStatus() == cancelado}"> class="error" </c:when>
                                          </c:choose>>
                                          <td>${solicitacaoViagem.getNumeroPedido()}</td>
                                          <td>${solicitacaoViagem.getSolicitante().getNome()}</td>
                                          <td>${solicitacaoViagem.getTipoVeiculo().getTipoVeiculo()}</td>
                                          <td>${solicitacaoViagem.getDataSaidaFormatada()}</td>
                                          <td>${solicitacaoViagem.getDataRetornoFormatada()}</td>
                                          <td>${solicitacaoViagem.getStatus()}</td>
                                <input type="hidden" name="solicitacaoViagem${solicitacaoViagem.getIdSolicitacaoViagem()}" value="${solicitacaoViagem.getIdSolicitacaoViagem()}" />
                                </tr>
                            </r:if>
                        </r:forEach>
                        </tbody>
                    </table>
                    <br>
                    <div id="legend">
                        <legend class="">Passageiros</legend>
                    </div>
                    <input type="hidden" name="id_passageiro" id="id_passageiro" />
                    <div class="control-group">
                        <label class="control-label" for="nome_passageiro">Nome do Passageiro</label>
                        <div class="controls">
                            <input class="input-xlarge" type="text" name="nome_passageiro" id="nome_passageiro" onblur='completeFields($("#nome_passageiro").val());' onkeyup='completeFields($("#nome_passageiro").val());' /><a class="btn btn-primary" onclick='addPassageiro($("#id_passageiro").val(), $("#nome_passageiro").val(), $("#rg_passageiro").val(), $("#telefone_passageiro").val(), $("#endereco_passageiro").val());'><i class="icon-plus icon-white"></i> Adicionar Passageiro</a>
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
                    <label class="" for="passageiros">Passageiros adicionados na reserva:</label>
                    <table class="table table-bordered table-striped" id="passageiros">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>RG</th>
                                <th>Telefone</th>
                                <th>Endereço</th>
                                <th>Remover</th>
                            </tr>
                        </thead>
                        <tbody>
                            <r:forEach var="passageiro" items="${passageiros}">
                                <tr>
                                    <td>${passageiro.getNome()}</td>
                                    <td>${passageiro.getRg()}</td>
                                    <td>${passageiro.getTelefone()}</td>
                                    <td>${passageiro.getEndereco()}</td>
                                    <td class="opcoes"><a class="btn btn-danger" onclick="retirarPassageiros(${passageiro.getIdPassageiro()});"><i class="icon-remove icon-white"></i></a></td>
                            <input type="hidden" name="passageiro${passageiro.getIdPassageiro()}" value="${passageiro.getIdPassageiro()}" />
                            </tr>
                        </r:forEach>
                        </tbody>
                    </table>
                </div>
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
