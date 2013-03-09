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
        <script type="text/javascript" src="./resources/fullcalendar/fullcalendar.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#calendar').fullCalendar({
                    events: [
                        {
                            title: 'titulo',
                            start: '2013-03-08 10:30',
                            end: '2013-03-08 11:30',
                            allDay: false,
                            color: 'blue'
                        }, {
                            title: 'titulo',
                            start: '2013-03-08 10:30',
                            end: '2013-03-08 11:30',
                            allDay: false,
                            color: 'red'
                        }
                    ],
                    selectable: true,
                    selectHelper: false,
                    defaultView: 'agendaWeek',
                    allDaySlot: false,
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    editable: false,
                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Aug', 'Set', 'Out', 'Nov', 'Dez'],
                    buttonText: {
                        prev: '&nbsp;&#9668;&nbsp;', // left triangle
                        next: '&nbsp;&#9658;&nbsp;', // right triangle
                        prevYear: '&nbsp;&lt;&lt;&nbsp;', // <<
                        nextYear: '&nbsp;&gt;&gt;&nbsp;', // >>
                        today: 'hoje',
                        month: 'mês',
                        week: 'semana',
                        day: 'dia'
                    },
                    allDayText: 'Dia Inteiro',
                    columnFormat: {
                        month: 'ddd', // Mon
                        week: 'ddd d/M', // Mon 9/7
                        day: 'dddd d/M', // Monday 9/7
                    },
                    timeFormat: 'H:mm{ - H:mm}',
                    axisFormat: 'H:mm',
                    titleFormat: {
                        month: "MMMM 'de' yyyy", // September 2009
                        week: "{d 'de' MMMM 'de' yyyy}",
                        day: "dddd, d 'de' MMMM 'de' yyyy" // Tuesday, Sep 8, 2009
                    }
                })
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div id='calendar'></div>
        <br>
        <div class="control-group">
            <div class="controls">
                <a href="./principal.jsp" class="btn btn-info"><i class="icon-arrow-left icon-white"></i> Voltar</a>
            </div>
        </div>
    </jsp:body>
</layout:page>
