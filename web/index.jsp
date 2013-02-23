<%-- 
    Document   : index
    Created on : 05/02/2013, 16:32:30
    Author     : thiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Identifique-se!</h1>
        <form method="POST" action="login.jsp">
            <p>Login: <input type="text" name="username"></p>
            <p>Senha: <input type="password" name="password"></p>
            <p><input type="submit" name="Logar!"></p>
        </form>
    </body>
</html>
