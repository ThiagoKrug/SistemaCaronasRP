<%-- 
    Document   : create
    Created on : 03/03/2013, 18:42:23
    Author     : thiago
--%><%@page import="java.io.File"%><%@page import="java.io.FileReader"%><%@page import="java.sql.Connection"%><%@page import="com.jdbc.database.ScriptRunner"%><%@page contentType="text/html" pageEncoding="UTF-8"%><%
    Connection connection = (Connection) request.getAttribute("connection");
    ScriptRunner sr = new ScriptRunner(connection, false, true);
    sr.setLogWriter(response.getWriter());
    sr.setErrorLogWriter(response.getWriter());
    sr.criarBanco();
%>