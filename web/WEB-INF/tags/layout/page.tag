<%@ tag description="Template" %>
<%@ attribute name="title"       required="true" description="Sistema de Caronas" %>
<%@ attribute name="description" required="true" description="" %>
<%@ attribute name="keywords"    required="true" description="" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <base href="http://localhost:8080/SistemaCaronas/" />
    <meta name="description" content="${description}" />
    <meta name="keywords" content="${keywords}" />
    <title>${title}</title>
    <link href="../resources/css/ui-lightness/jquery-ui.css" rel="stylesheet">
</head>
<body>
    <layout:header/>
    <div class="container">
        <jsp:doBody/>
    </div>
    <layout:footer/>
</body>
</html>