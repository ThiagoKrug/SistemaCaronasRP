<%-- 
    Document   : login
    Created on : 23/02/2013, 09:40:02
    Author     : Usuario
--%>

<%@page import="com.model.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.model.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String name = request.getParameter("username");
    String password = request.getParameter("password");
    Connection connection = (Connection)request.getAttribute("connection");
    UsuarioDAO ud = new UsuarioDAO(connection);
    List<Usuario> usuarios = ud.getUsuarios();
    for (Usuario usuario: usuarios) {
        if (usuario.getUsername().equals(name)) {
            if (usuario.getSenha().equals(password)) {
                session.setAttribute("Username", name);
                session.setAttribute("Clearance", usuario.getTipoUsuario().getTipoUsuario());
            }
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            if (session.getAttribute("Username") == null) {
        %>
            <h2>Login ou Senha incorretos.</h2>
            <a href="index.jsp">Voltar</a>
        <%
            }
        %>
    </body>
</html>
