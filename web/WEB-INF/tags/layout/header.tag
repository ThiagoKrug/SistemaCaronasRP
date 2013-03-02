<%@ tag body-content="empty" description="Arquivo de TAG do Header" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="principal.jsp" name="top">Sistema de Caronas</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="#"><i class="icon-home"></i> Home</a></li>
                        <li class="divider-vertical"></li>
                        <li class="active"><a href="#"><i class="icon-file"></i> Pages</a></li>
                        <li class="divider-vertical"></li>
                    </ul>
                    <div class="btn-group pull-right">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user"></i> <span class="btnUsuario"><c:out value="${sessionScope.Name}"/></span> <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <!--<li><a href="#"><i class="icon-wrench"></i> Settings</a></li>
                            <li class="divider"></li>-->
                            <li><a href="./index.jsp"><i class="icon-share"></i> Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>