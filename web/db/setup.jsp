<%-- 
    Document   : setup
    Created on : 03/03/2013, 18:39:59
    Author     : thiago
--%>

<%@page import="com.jdbc.ConnectionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setAttribute("connection", new ConnectionFactory().getConnection());
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Setup</title>
    </head>
    <body>
        <a href="./create.jsp">Create Database</a>
        <a href="./fixtures.jsp">Load Fixtures</a>
    </body>
</html>
