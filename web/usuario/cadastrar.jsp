<%--
    Document   : cadastrar
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.convert.UsuarioConverter"%><%@page import="com.model.entity.Usuario"%><%@page import="com.model.dao.UsuarioDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%
    new AuthChecker().authenticate(session, response, new String[] {"Administrador"});
    PrintWriter saida = response.getWriter();
    Connection connection = (Connection) request.getAttribute("connection");
    UsuarioDAO udao = new UsuarioDAO(connection);
    if (request.getMethod().equalsIgnoreCase("post")) {
        try {
            UsuarioConverter uc = new UsuarioConverter();
            Usuario usuario = (Usuario) uc.convertEntity(request);

            if (usuario.getIdUsuario()== null || usuario.getIdUsuario()<= 0) {
                if (udao.inserir(usuario) == 1) {
                    saida.print("Usuário cadastrado com sucesso.");
                } else {
                    saida.print("Problemas ao cadastrar o usuário!");
                }
            } else {
                if (udao.alterar(usuario) == 1) {
                    saida.print("Usuário atualizado com sucesso.");
                } else {
                    saida.print("Problemas ao atualizar o usuário!");
                }
            }
        } catch (Exception e) {
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>