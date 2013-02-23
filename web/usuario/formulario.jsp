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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[] {"Administrador"});
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro de Usuário</h1>
        <form action="" method="POST" id="usuario">
            <input type="hidden" name="id_usuario" id="id_usuario" value="${usuario.getIdUsuario()}" />
            <table>
                <tbody>
                    <tr>
                        <td>Tipo de Usuário</td>
                        <td><select name="tipo_usuario">
                                <r:forEach var="tipoUsuario" items="${tudao.getTiposUsuarios()}">
                                    <option value="${tipoUsuario.getIdTipoUsuario()}"
                                            <r:if test="${tipoUsuario.getIdTipoUsuario() == usuario.getTipoUsuario().getIdTipoUsuario()}"> selected="true" </r:if>>${tipoUsuario.getTipoUsuario()}</option>
                                </r:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Nome</td>
                        <td><input type="text" name="nome" value="${usuario.getNome()}" /></td>
                    </tr>
                    <tr>
                        <td>RG</td>
                        <td><input type="text" name="rg" value="${usuario.getRg()}" /></td>
                    </tr>
                    <tr>
                        <td>Telefone</td>
                        <td><input type="tel" name="telefone" value="${usuario.getTelefone()}" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="email" name="email" value="${usuario.getEmail()}" /></td>
                    </tr>
                    <tr>
                        <td>Número do Servidor</td>
                        <td><input type="text" name="numero_servidor" value="${usuario.getNumeroServidor()}" /></td>
                    </tr>
                    <tr>
                        <td>Nome de Usuário</td>
                        <td><input type="text" name="username" value="${usuario.getUsername()}" /></td>
                    </tr>
                    <tr>
                        <td>Senha</td>
                        <td><input type="password" name="senha" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Salvar Passageiro" onclick="salvar();"/>
        </form>
        <script type="text/javascript" src="../resources/js/jquery.js"></script>
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
