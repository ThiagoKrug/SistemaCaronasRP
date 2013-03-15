<%@tag import="com.auth.AuthChecker"%>
<%@ tag body-content="empty" description="Arquivo de TAG do Header" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="r" %>
<%
    request.setAttribute("admin", AuthChecker.ADMIN.toString());
    request.setAttribute("motorista", AuthChecker.MOTORISTA.toString());
    request.setAttribute("servidor", AuthChecker.SERVIDOR.toString());
%>
<header>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <div class="span2"></div>
                <a class="brand" href="principal.jsp" name="top">Sistema de Caronas</a>
                <div class="nav-collapse collapse">
                    <r:if test="${sessionScope.Clearance == admin || sessionScope.Clearance == motorista || sessionScope.Clearance == servidor}">
                        <ul class="nav">
                            <li><a href="./reserva/index.jsp"><i class="icon-home"></i> Reservas</a></li>
                            <li class="divider-vertical"></li>
                                <r:if test="${sessionScope.Clearance == admin}">
                                <li><a href="./viagem/index.jsp"><i class="icon-globe"></i> Viagens</a></li>
                                <li class="divider-vertical"></li>
                                <ul class="nav">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-calendar"></i> Agendas <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="./veiculo/agenda.jsp">Agenda dos Veículos</a></li>
                                            <li class="divider"></li>
                                            <li><a href="./usuario/motorista/agenda.jsp">Agenda dos Motoristas</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                <li class="divider-vertical"></li>
                                <ul class="nav">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i> Formulários <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="./veiculo/index.jsp">Veículos</a></li>
                                            <li class="divider"></li>
                                            <li><a href="./passageiro/index.jsp">Passageiros</a></li>
                                            <li class="divider"></li>
                                            <li><a href="./usuario/index.jsp">Usuários</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </r:if>
                        </ul>
                        <div class="btn-group pull-right">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="icon-user"></i> <span class="btnUsuario"><c:out value="${sessionScope.Name}"/></span> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="./index.jsp"><i class="icon-share"></i> Logout</a></li>
                            </ul>
                        </div>
                    </r:if>
                </div>
            </div>
        </div>
    </div>
</header>