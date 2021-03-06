<%@ tag description="Template" %>
<%@ attribute name="title"       required="true" description="Sistema de Caronas" %>
<%@ attribute name="description" required="true" description="" %>
<%@ attribute name="keywords"    required="true" description="" %>
<%@ attribute name="extraBottom" fragment="true" description="Extra code to put before </body>" %>
<%@ attribute name="extraHead"   fragment="true" description="Extra code to put before </head>" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <base href="http://localhost:8080/SistemaCaronas/" />
    <meta name="description" content="${description}" />
    <meta name="keywords" content="${keywords}" />
    <title>Sistema de Caronas - ${title}</title>
    <link href="./resources/css/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link href="./resources/css/bootstrap.css" rel="stylesheet">
    <link href="./resources/css/estilo.css" rel="stylesheet">
    <style type="text/css">
        /* Sticky footer styles
        -------------------------------------------------- */
        html,
        body {
            height: 100%;
            /* The html and body elements cannot have any padding or margin. */
        }
        /* Wrapper for page content to push down footer */
        #wrap {
            min-height: 100%;
            height: auto !important;
            height: 100%;
            /* Negative indent footer by it's height */
            margin: 0 auto -60px;
        }
        /* Set the fixed height of the footer here */
        #push,
        #footer {
            height: 60px;
        }
        #footer {
            background-color: #f5f5f5;
        }
        /* Lastly, apply responsive CSS fixes as necessary */
        @media (max-width: 767px) {
            #footer {
                margin-left: -20px;
                margin-right: -20px;
                padding-left: 20px;
                padding-right: 20px;
            }
        }

        /* Custom page CSS
        -------------------------------------------------- */
        /* Not required for template or sticky footer method. */
        #wrap > .container {
            padding-top: 10px;
        }
        .container .credit {
            margin: 20px 0;
        }
        code {
            font-size: 80%;
        }
    </style>
    <link rel="icon" type="image/ico" href="./resources/img/favicon.ico">
    <jsp:invoke fragment="extraHead"/>
</head>
<body>
    <div id="wrap">
        <layout:header/>
        <div class="container">
            <jsp:doBody/>
        </div>
        <div id="push"></div>
    </div>
    <layout:footer/>
    <jsp:invoke fragment="extraBottom"/>
</body>
</html>