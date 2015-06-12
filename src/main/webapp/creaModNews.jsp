<%-- 
    Document   : news
    Created on : Jun 10, 2015, 2:33:09 PM
    Author     : ESa10969
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
        <script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/FormsValidations.js"> </script>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/MapsScript.js"></script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <div class="container-fluid">
           <div class='panel panel-inverse'>
            <div class='panel-heading' style='background-color: #222;border-color: #080808;'>
                <div class='panel-inverse-title'>Create New</div>
            </div>
            <div class='panel-body'>
                <form id="creaNew" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/news?crea=1'/>" method="post">
                    <div id="signupalert" style="display:none" class="alert alert-danger">
                        <p>Error:</p>
                        <span></span>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-md-3 control-label">Titulo</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="title" name="title" placeholder="Ref. Titulo">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nombre" class="col-md-3 control-label">Texto</label>
                        <div class="col-md-9">
                            <%@include file='editors/textEditor.jsp'%>
                            <input type="text" class="form-control" id="text" name="text" style="display: none;">
                        </div>
                    </div>
                    <div class="form-group">
                        <!-- Button -->                                        
                        <div class="col-md-offset-3 col-md-4">
                            <input id="btn-signup" name="submit" type="submit" value="Guardar" class="btn btn-info"/>
                        </div>
                        <div class="col-md-4">
                            <a onClick="goBack()" class="btn btn-primary">Atras</a>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
           </div> 
        </div>
        <%@include file='static/footer.jsp'%>
    </body>
</html>
