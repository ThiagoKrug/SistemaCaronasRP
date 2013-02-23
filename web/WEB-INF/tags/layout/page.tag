<%@ tag description="Template" %>
<%@ attribute name="title"       required="true" description="Sistema de Caronas" %>
<%@ attribute name="description" required="true" description="" %>
<%@ attribute name="keywords"    required="true" description="" %>
<%@ attribute name="extraBottom" fragment="true" description="Extra code to put before </body>" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <base href="http://localhost:8080/SistemaCaronas/" />
    <meta name="description" content="${description}" />
    <meta name="keywords" content="${keywords}" />
    <title>${title}</title>
    <link href="./resources/css/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link href="./resources/css/bootstrap.css" rel="stylesheet">
    <link rel="icon" type="image/ico" href="./resources/img/favicon.ico">
</head>
<body>
    <layout:header/>
    <div class="container">
        <jsp:doBody/>
    </div>
    <layout:footer/>
    <jsp:invoke fragment="extraBottom"/>
</body>
</html>