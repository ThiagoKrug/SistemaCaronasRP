<%-- 
    Document   : formulario
    Created on : 08/02/2013, 15:37:43
    Author     : thiago
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.model.entity.Passageiro"%>
<%@page import="com.convert.PassageiroConverter"%>
<%@page import="com.model.dao.PassageiroDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[] {"Administrador"});
    Connection connection = (Connection) request.getAttribute("connection");
    PassageiroDAO pdao = new PassageiroDAO(connection);
    String idPassageiro = request.getParameter("id_passageiro");
    if (idPassageiro != null) {
        request.setAttribute("passageiro", pdao.getById(Integer.parseInt(idPassageiro)));
    } else {
        Passageiro passageiro = new Passageiro();
        request.setAttribute("passageiro", passageiro);
    }
%>
<layout:page description="" keywords="" title="Cadastro de Passageiro">
    <jsp:body>
        <style>
            
        </style>
        <form class="form-horizontal" action="" method="POST" id="passageiro">
            <input type="hidden" name="id_passageiro" id="id_passageiro" value="${passageiro.getIdPassageiro()}" />
            <fieldset>
    <div id="legend">
      <legend class="">Cadastrar Passageiro</legend>
    </div>
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="nome">Nome</label>
      <div class="controls">
          <input type="text" id="nome" name="nome" value="${passageiro.getNome()}" class="input-xlarge" />
      </div>
    </div>
 
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="rg">RG</label>
      <div class="controls">
          <input type="text" id="rg" name="rg" value="${passageiro.getRg()}" class="input-xlarge" />
      </div>
    </div>
 
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="telefone">Telefone</label>
      <div class="controls">
          <input type="tel" id="telefone" name="telefone" value="${passageiro.getTelefone()}" class="input-xlarge" />
      </div>
    </div>
 
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="endereco">Endereço</label>
      <div class="controls">
          <input type="text" id="nome" name="endereco" value="${passageiro.getEndereco()}" class="input-xlarge" />
      </div>
    </div>
 
    
  </fieldset>
            <!--<table>
                <tbody>
                    <tr>
                        <td>Nome</td>
                        <td><input type="text" name="nome" value="${passageiro.getNome()}" /></td>
                    </tr>
                    <tr>
                        <td>RG</td>
                        <td><input type="text" name="rg" value="${passageiro.getRg()}" /></td>
                    </tr>
                    <tr>
                        <td>Telefone</td>
                        <td><input type="tel" name="telefone" value="${passageiro.getTelefone()}" /></td>
                    </tr>
                    <tr>
                        <td>Endereço</td>
                        <td><input type="text" name="endereco" value="${passageiro.getEndereco()}" /></td>
                    </tr>
                </tbody>
            </table>-->
            <input class="btn btn-success" type="submit" value="Salvar Passageiro" onclick="salvar();"/>
        </form>
        <script type="text/javascript">
                function salvar() {
                    event.preventDefault();
                    // get all the inputs into an array.
                    var $inputs = $('#passageiro :input');

                    // not sure if you wanted this, but I thought I'd add it.
                    // get an associative array of just the values.
                    var values = {};
                    $inputs.each(function() {
                        values[this.name] = $(this).val();
                    });
                    console.log(values);

                    /* Send the data using post */
                    $.post("./passageiro/cadastrar.jsp", values, function(data) {
                        alert(data);
                        console.log(data);
                        window.location = "./passageiro/index.jsp";
                        return;
                    }
                    );
                }
                ;
        </script>
    </jsp:body>
</layout:page>
