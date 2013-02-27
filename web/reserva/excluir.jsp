<%--
    Document   : excluir.jsp
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.model.entity.SolicitacaoViagem"%><%@page import="com.model.dao.SolicitacaoViagemDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8"%><%
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker().authAjax(session,
            new String[] {AuthChecker.ADMIN, AuthChecker.SERVIDOR}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
        try {
            int id = Integer.parseInt(request.getParameter("id_solicitacao_viagem"));
            SolicitacaoViagem solicitacaoViagem = svdao.getById(id);
            int linhasAfetadas = svdao.deletar(solicitacaoViagem);
            if (linhasAfetadas == 1) {
                saida.print("Reserva excluída com sucesso.");
            } else {
                saida.print("Problemas ao excluir a reserva.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>