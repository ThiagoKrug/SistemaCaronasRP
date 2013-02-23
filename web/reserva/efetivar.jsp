<%-- 
    Document   : efetivar
    Created on : 13/02/2013, 04:33:19
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.model.entity.StatusSolicitacaoViagem"%><%@page import="com.model.entity.SolicitacaoViagem"%><%@page import="com.model.dao.SolicitacaoViagemDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%
    new AuthChecker().authenticate(session, response, new String[] {"Administrador"});
    PrintWriter saida = response.getWriter();
    Connection connection = (Connection) request.getAttribute("connection");
    SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
    try {
        int id = Integer.parseInt(request.getParameter("id_solicitacao_viagem"));
        SolicitacaoViagem solicitacaoViagem = svdao.getById(id);
        solicitacaoViagem.setStatus(StatusSolicitacaoViagem.EFETIVADO.toString());
        int linhasAfetadas = svdao.alterar(solicitacaoViagem);
        if (linhasAfetadas == 1) {
            saida.print("Reserva efetivada com sucesso.");
        } else {
            saida.print("Problemas ao efetivar a reserva.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        saida.print(e.getMessage());
    }
    saida.flush();
    saida.close();
%>