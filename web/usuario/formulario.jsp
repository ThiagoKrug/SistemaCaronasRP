<%-- 
    Document   : formulario
    Created on : 08/02/2013, 15:37:43
    Author     : thiago
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.TipoUsuarioDAO"%>
<%@page import="com.model.entity.TipoUsuario"%>
<%@page import="com.model.entity.Usuario"%>
<%@page import="com.model.dao.UsuarioDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
<<<<<<< HEAD
    new AuthChecker("../index.jsp").authenticate(session, response, new String[]{"Administrador"});
=======
    new AuthChecker("../index.jsp").authenticate(session, response, new String[] {
        AuthChecker.ADMIN});
>>>>>>> a700e5e1cb9824dcbf04587aff15c12a3c45d811
    Connection connection = (Connection) request.getAttribute("connection");
    UsuarioDAO udao = new UsuarioDAO(connection);
    TipoUsuarioDAO tudao = new TipoUsuarioDAO(connection);
    request.setAttribute("tudao", tudao);
    String idUsuario = request.getParameter("id_usuario");
    if (idUsuario != null) {
        request.setAttribute("usuario", udao.getById(Integer.parseInt(idUsuario)));
    } else {
        Usuario usuario = new Usuario();
        request.setAttribute("usuario", usuario);
    }
%>
<layout:page description="" keywords="" title="Cadastro de Usuário">
    <jsp:body>
        <style>
            td
            {
                padding: 10px;
            }
        </style>
        <form class="form-horizontal" action="" method="POST" id="usuario">
            <fieldset>
                <div id="legend">
                    <legend class="">Cadastro de Usuário</legend>
                </div>
                <input type="hidden" name="id_usuario" id="id_usuario" value="${usuario.getIdUsuario()}" />
                <div class="control-group">
                    <label class="control-label" for="tipoUsuario">Tipo de Usuário</label>
                    <div class="controls">
                        <select name="tipo_usuario" class="input-xlarge">
                            <r:forEach var="tipoUsuario" items="${tudao.getTiposUsuarios()}">
                                <option value="${tipoUsuario.getIdTipoUsuario()}"
                                        <r:if test="${tipoUsuario.getIdTipoUsuario() == usuario.getTipoUsuario().getIdTipoUsuario()}"> selected="true" </r:if>>${tipoUsuario.getTipoUsuario()}</option>
                            </r:forEach>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="nome">Nome</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="nome" value="${usuario.getNome()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="rg">RG</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="rg" value="${usuario.getRg()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="telefone">Telefone</label>
                    <div class="controls">
                        <input class="input-xlarge" type="tel" name="telefone" value="${usuario.getTelefone()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="email">Email</label>
                    <div class="controls">
                        <input class="input-xlarge" type="email" name="email" value="${usuario.getEmail()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="numero_servidor">Número do Servidor</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="numero_servidor" value="${usuario.getNumeroServidor()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="username">Nome de Usuário</label>
                    <div class="controls">
                        <input class="input-xlarge" type="text" name="username" value="${usuario.getUsername()}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="senha">Senha</label>
                    <div class="controls">
                        <input class="input-xlarge" type="password" name="senha" value="" />
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <a href="./usuario/index.jsp" class="btn btn-info">Voltar</a>
                        <button class="btn btn-success" type="submit" onclick="salvar();" ><i class="icon-ok icon-white"></i> Salvar Usuário</button>
                    </div>
                </div>
            </fieldset>
        </form>
        <script type="text/javascript">
                            function salvar() {
                                event.preventDefault();
                                // get all the inputs into an array.
                                var $inputs = $('#usuario :input');

                                // not sure if you wanted this, but I thought I'd add it.
                                // get an associative array of just the values.
                                var values = {};
                                $inputs.each(function() {
                                    values[this.name] = $(this).val();
                                });
                                console.log(values);

                                /* Send the data using post */
                                $.post("./usuario/cadastrar.jsp", values, function(data) {
                                    alert(data);
                                    console.log(data);
                                    window.location = "./usuario/index.jsp";
                                    return;
                                }
                                );
                            }
                            ;
        </script>
    </jsp:body>
</layout:page>
