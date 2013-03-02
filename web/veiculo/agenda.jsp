<%-- 
    Document   : index
    Created on : 08/02/2013, 15:22:44
    Author     : thiago
--%>

<%@page import="com.auth.AuthChecker"%>
<%@page import="com.model.dao.VeiculoDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    new AuthChecker("../index.jsp").authenticate(session, response,
            new String[]{AuthChecker.ADMIN});
    Connection connection = (Connection) request.getAttribute("connection");
    VeiculoDAO vdao = new VeiculoDAO(connection);
    request.setAttribute("vdao", vdao);
%>
<layout:page title="Agenda dos Veículos" description="" keywords="">
    <jsp:attribute name="extraHead">
        <link rel="stylesheet" href="./resources/fullcalendar/fullcalendar.css">
    </jsp:attribute>
    <jsp:attribute name="extraBottom">
        <script type="text/javascript" src="./resources/fullcalendar/fullcalendar.js"/>
        <script type="text/javascript">
            $(document).ready(function() {

                // page is now ready, initialize the calendar...

                $('#calendar').fullCalendar({
                    // put your options and callbacks here
                })

            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div id='calendar'></div>
    </jsp:body>
</layout:page>
