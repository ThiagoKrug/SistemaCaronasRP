<%-- 
    Document   : fixtures
    Created on : 11/03/2013, 23:04:12
    Author     : thiago
--%><%@page import="com.jdbc.fixtures.Fixtures"%><%@page import="java.sql.Connection"%><%@page contentType="text/html" pageEncoding="UTF-8"%><%
    Connection connection = (Connection) request.getAttribute("connection");
    Fixtures fixtures = new Fixtures();
    fixtures.loadFixtures(connection);
%>