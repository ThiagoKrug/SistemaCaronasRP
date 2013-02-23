<%--
    Document   : excluir.jsp
    Created on : 08/02/2013, 16:49:23
    Author     : thiago
--%><%@page import="com.auth.AuthChecker"%><%@page import="com.model.entity.Veiculo"%><%@page import="com.model.dao.VeiculoDAO"%><%@page import="java.io.PrintWriter"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8"%><%
    
    PrintWriter saida = response.getWriter();
    boolean auth = new AuthChecker("../index.jsp").authAjax(session, 
            new String[] {"Administrador"}, saida);
    if (auth) {
        Connection connection = (Connection) request.getAttribute("connection");
        VeiculoDAO vdao = new VeiculoDAO(connection);
        try {
            int id = Integer.parseInt(request.getParameter("id_veiculo"));
            Veiculo veiculo = (Veiculo) vdao.getById(id);
            int linhasAfetadas = vdao.deletar(veiculo);
            if (linhasAfetadas == 1) {
                saida.print("Veículo excluído com sucesso.");
            } else {
                saida.print("Problemas ao excluir o veículo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            saida.print(e.getMessage());
        }
    }
    saida.flush();
    saida.close();
%>