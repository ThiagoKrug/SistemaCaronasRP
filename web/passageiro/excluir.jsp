<%--
    Document   : excluir.jsp
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page import="com.model.entity.Passageiro"%><%@page import="com.model.dao.PassageiroDAO"%><%@page contentType="text/html" pageEncoding="UTF-8"%><%
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker().authAjax(session,
            new String[] {"Administrador"}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        PassageiroDAO pdao = new PassageiroDAO(connection);
        try {
            int id = Integer.parseInt(request.getParameter("id_passageiro"));
            Passageiro passageiro = pdao.getById(id);
            int linhasAfetadas = pdao.deletar(passageiro);
            if (linhasAfetadas == 1) {
                saida.print("Passageiro excluído com sucesso.");
            } else {
                saida.print("Problemas ao excluir o passageiro.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>