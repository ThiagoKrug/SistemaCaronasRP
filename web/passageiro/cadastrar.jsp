<%--
    Document   : cadastrar
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%@page import="com.model.entity.Passageiro"%><%@page import="com.convert.PassageiroConverter"%><%@page import="com.model.dao.PassageiroDAO"%><%
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker().authAjax(session,
            new String[] {AuthChecker.ADMIN}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        PassageiroDAO pdao = new PassageiroDAO(connection);
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                PassageiroConverter pc = new PassageiroConverter();
                Passageiro passageiro = (Passageiro) pc.convertEntity(request);

                if (passageiro.getIdPassageiro() == null || passageiro.getIdPassageiro() <= 0) {
                    if (pdao.inserir(passageiro) == 1) {
                        saida.print("Passageiro cadastrado com sucesso.");
                    } else {
                        saida.print("Problemas ao cadastrar o passageiro!");
                    }
                } else {
                    if (pdao.alterar(passageiro) == 1) {
                        saida.print("Passageiro atualizado com sucesso.");
                    } else {
                        saida.print("Problemas ao atualizar o passageiro!");
                    }
                }
            } catch (Exception e) {
                saida.print(e.getMessage());
            }
        }
    }
    saida.flush();
    saida.close();
%>