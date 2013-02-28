<%--
    Document   : excluir.jsp
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.model.entity.Usuario"%><%@page import="com.model.dao.UsuarioDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8"%><%
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker("../index.jsp").authAjax(session,
            new String[] {AuthChecker.ADMIN}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        UsuarioDAO udao = new UsuarioDAO(connection);
        try {
            int id = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = udao.getById(id);
            int linhasAfetadas = udao.mudarSituacao(usuario);
            if (linhasAfetadas == 1) {
                saida.print("Situação alterada com sucesso.");
            } else {
                saida.print("Problemas ao mudar a situação do usuário.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>