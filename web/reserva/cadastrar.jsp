<%--
    Document   : cadastrar
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.convert.SolicitacaoViagemConverter"%><%@page import="com.model.entity.SolicitacaoViagem"%><%@page import="com.model.dao.SolicitacaoViagemDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%
    new AuthChecker().authenticate(session, response, new String[] {"Administrador",
    "Servidor Solicitante"});
    PrintWriter saida = response.getWriter();
    Connection connection = (Connection) request.getAttribute("connection");
    SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
    if (request.getMethod().equalsIgnoreCase("post")) {
        try {
            SolicitacaoViagemConverter svc = new SolicitacaoViagemConverter();
            SolicitacaoViagem solicitacaoViagem = (SolicitacaoViagem) svc.convertEntity(request);

            if (solicitacaoViagem.getIdSolicitacaoViagem() == null || solicitacaoViagem.getIdSolicitacaoViagem() <= 0) {
                if (svdao.inserir(solicitacaoViagem) == 1) {
                    saida.print("Reserva solicitada com sucesso.");
                } else {
                    saida.print("Problemas ao solicitar a reserva!");
                }
            } else {
                if (svdao.alterar(solicitacaoViagem) == 1) {
                    saida.print("Reserva atualizada com sucesso.");
                } else {
                    saida.print("Problemas ao atualizar a reserva!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>