<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="com.model.entity.Usuario"%>
<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.UsuarioDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response, new String[] {
        AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    UsuarioDAO udao = new UsuarioDAO(connection);
    request.setAttribute("udao", udao);
    request.setAttribute("ativo", Usuario.ATIVO);
    request.setAttribute("inativo", Usuario.INATIVO);
%>
<layout:page title="Listagem de Usuários" description="" keywords="">
    <jsp:body>
        <h1>Usuários</h1>
        <h4><a class="btn btn-primary" href="./usuario/formulario.jsp"><i class="icon-plus icon-white"></i> Cadastrar Usuário</a></h4>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>RG</th>
                    <th>Tipo de Usuário</th>
                    <th>Situação</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="usuario" items="${udao.getUsuarios()}">
                    <tr>
                        <td>${usuario.getNome()}</td>
                        <td>${usuario.getRg()}</td>
                        <td>${usuario.getTipoUsuario().getTipoUsuario()}</td>
                        <td>${usuario.getSituacao()}</td>
                        <td class="opcoes"><a class="btn btn-warning" href="./usuario/formulario.jsp?id_usuario=${usuario.getIdUsuario()}"><i class="icon-edit icon-white"></i> Editar</a>
                        <r:if test="${usuario.getSituacao() == ativo}">
                            <a class="btn btn-danger" href="" onclick="excluir(${usuario.getIdUsuario()});"><i class="icon-remove icon-white"></i> Desativar</a></td>
                        </r:if>
                        <r:if test="${usuario.getSituacao() == inativo}">
                            <a class="btn btn-success" href="" onclick="excluir(${usuario.getIdUsuario()});"><i class="icon-plus icon-white"></i> Ativar</a>
                        </r:if>
                        </td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
        <script type="text/javascript">
                            function excluir(idUsuario) {
                                event.preventDefault();

                                if (confirm("Deseja realmente inativar este usuário?")) {
                                    /* Send the data using post */
                                    $.post("./usuario/excluir.jsp",
                                            {id_usuario: idUsuario},
                                    function(data) {
                                        alert(data);
                                        console.log(data);
                                        window.location = "./usuario/index.jsp";
                                        return;
                                    }
                                    );
                                }
                                ;
                            }
        </script>
    </jsp:body>
</layout:page>
