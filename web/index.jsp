<%-- 
    Document   : index
    Created on : 05/02/2013, 16:32:30
    Author     : thiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    session.setAttribute("Username", null);
    session.setAttribute("Name", null);
    session.setAttribute("Clearance", null);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </head>
    <body>
        <h1>Identifique-se!</h1>
        <form method="POST" action="login.jsp" name="LoginForm" onsubmit="return validateForm();">
            <p>Login: <input type="text" id="login" name="username"></p>
            <p>Senha: <input type="password" id="pwd" name="password"></p>
            <p><input type="submit" name="Logar!"></p>
        </form>
    </body>
</html>
