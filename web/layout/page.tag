<%@ tag description="Template" %>

<%@ attribute name="title"       required="true" description="Sistema de Carona" %>
<%@ attribute name="description" required="true" description="" %>
<%@ attribute name="keywords"    required="true" description="" %>

<%@ attribute name="extraHeader" fragment="true" description="Código extra do header" %>

<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>

<!DOCTYPE html>
<!--[if IE 7]>    <html class="no-js ie7 ie" lang="pt-BR"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 ie" lang="pt-BR"> <![endif]-->
<!--[if IE 9]>    <html class="no-js ie9 ie" lang="pt-BR"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-js" lang="pt-BR"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <base href="http://localhost:8080/SistemaCaronasJSP/" />
        <meta name="description" content="${description}" />
        <meta name="keywords" content="${keywords}" />

        <title>${title}</title>

        <!-- CSS styles -->
        <link rel='stylesheet' type='text/css' href='css/wuxia-green.css'>

        <link rel="stylesheet" type="text/css" href="css/plugins/jquery.validate.css" />

        <!-- Fav and touch icons -->
        <link rel="shortcut icon" href="img/icons/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/icons/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/icons/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="img/icons/apple-touch-icon-57-precomposed.png">

        <!-- Javascripts -->
        <script src="js/libs/jquery.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/libs/modernizr.js"></script>
        <script src="js/libs/selectivizr.js"></script>
        <script>
            jQuery(document).ready(function() {
                
                $('.navbar [title]').tooltip({
                    placement: 'bottom'
                });
				
                $('[role=main] [title]:not(.demoPopover)').tooltip({
                    placement: 'top'
                });
                
            });
        </script>

        <script src="js/plugins/validate/jquery.validate.engine.br.js" charset="UTF-8"></script>
        <script src="js/plugins/validate/jquery.validate.engine.js"></script>
        <script>
            jQuery(document).ready(function() {
                jQuery(".validate").validationEngine();
            });
        </script>

    </head>
    <body>

        <layout:header/>

        <div class="container" role="main">
            <jsp:doBody/>
        </div>

        <layout:footer/>

    </body>
</html>