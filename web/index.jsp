<%-- 
    Document   : index
    Created on : 05/02/2013, 16:32:30
    Author     : thiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%
    session.setAttribute("Username", null);
    session.setAttribute("Name", null);
    session.setAttribute("Clearance", null);
    System.out.println(session.getAttribute("Username"));
%>
<layout:page title="Login" description="" keywords="">
    <jsp:attribute name="extraBottom">
        <script>
            function validateForm() {
                var logname = document.forms["LoginForm"]["username"].value;
                var pwd = document.forms["LoginForm"]["password"].value;
                var logEmpty = logname === null || logname === "";
                var pwdEmpty = pwd === null || pwd === "";
                if (logEmpty || pwdEmpty) {
                    alert("Os campos não podem estar vazios.");
                    return false;
                }
                return true;
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="span6">
            <form class="form-horizontal" method="POST" action="./login.jsp" name="LoginForm" onsubmit="return validateForm();">
                <fieldset>
                    <div id="legend">
                        <legend class="">Identifique-se!</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label"  for="username">Login</label>
                        <div class="controls">
                            <input type="text" id="login" name="username" class="span3" placeholder="Login">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"  for="username">Senha</label>
                        <div class="controls">
                            <input type="password" id="pwd" name="password" class="span3" placeholder="Senha">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button id="submit" type="submit" class="btn btn-primary input-medium">Logar</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </jsp:body>
</layout:page>