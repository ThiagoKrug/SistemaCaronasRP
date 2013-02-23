<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="com.model.dao.UsuarioDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    Connection connection = (Connection) request.getAttribute("connection");
    UsuarioDAO udao = new UsuarioDAO(connection);
    request.setAttribute("udao", udao);
%>
<layout:page title="Listagem de Usuários" description="" keywords="">
    <jsp:body>
        <h1>Usuários</h1>
        <h4><a href="./usuario/formulario.jsp">Cadastrar Usuário</a></h4>
        <table>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>RG</th>
                    <th>Tipo de Usuário</th>
                    <th colspan="2">Opções</th>
                </tr>
            </thead>
            <tbody>
                <r:forEach var="usuario" items="${udao.getUsuarios()}">
                    <tr>
                        <td>${usuario.getNome()}</td>
                        <td>${usuario.getRg()}</td>
                        <td>${usuario.getTipoUsuario().getTipoUsuario()}</td>
                        <td><a href="./usuario/formulario.jsp?id_usuario=${usuario.getIdUsuario()}">Editar</a></td>
                        <td><a href="" onclick="excluir(${usuario.getIdUsuario()});">Excluir</a></td>
                    </tr>
                </r:forEach>
            </tbody>
        </table>
        <script type="text/javascript">
                            function excluir(idUsuario) {
                                event.preventDefault();

                                if (confirm("Deseja realmente excluir este usuário?")) {
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