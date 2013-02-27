<%--
    Document   : cadastrar
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.convert.VeiculoConverter"%><%@page import="com.model.entity.Veiculo"%><%@page import="com.model.dao.VeiculoDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8" %><%
    
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker("../index.jsp").authAjax(session, 
            new String[] {AuthChecker.ADMIN}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        VeiculoDAO vdao = new VeiculoDAO(connection);
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                VeiculoConverter vc = new VeiculoConverter();
                Veiculo veiculo = (Veiculo) vc.convertEntity(request);

                if (veiculo.getIdVeiculo() == null || veiculo.getIdVeiculo() <= 0) {
                    if (vdao.inserir(veiculo) == 1) {
                        saida.print("Veículo cadastrado com sucesso.");
                    } else {
                        saida.print("Problemas ao cadastrar o veículo!");
                    }
                } else {
                    if (vdao.alterar(veiculo) == 1) {
                        saida.print("Veículo atualizado com sucesso.");
                    } else {
                        saida.print("Problemas ao atualizar o veículo!");
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