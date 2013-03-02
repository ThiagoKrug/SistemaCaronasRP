<%--
    Document   : cancelar
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.model.entity.StatusSolicitacaoViagem"%><%@page import="com.model.entity.SolicitacaoViagem"%><%@page import="com.model.dao.SolicitacaoViagemDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%@page import="com.mail.Mail" %><%
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker().authAjax(session,
            new String[] {AuthChecker.ADMIN, AuthChecker.SERVIDOR}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        SolicitacaoViagemDAO svdao = new SolicitacaoViagemDAO(connection);
        try {
            int id = Integer.parseInt(request.getParameter("id_solicitacao_viagem"));
            SolicitacaoViagem solicitacaoViagem = svdao.getById(id);
            solicitacaoViagem.setStatus(StatusSolicitacaoViagem.CANCELADO.toString());
            int linhasAfetadas = svdao.alterar(solicitacaoViagem);
            if (linhasAfetadas == 1) {
                Mail mail = new Mail();
                mail.sendmail(solicitacaoViagem.getSolicitante().getEmail(),
                        Mail.CANCEL_TEMPLATE);
                mail.sendmail(solicitacaoViagem.getAutorizante().getEmail(),
                        Mail.CANCEL_TEMPLATE);
                saida.print("Reserva cancelada com sucesso.");
            } else {
                saida.print("Problemas ao cancelar a reserva.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>