<%-- 
    Document   : perfil
    Created on : Jun 19, 2015, 10:02:29 AM
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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/FormsValidations.js"> </script>
        <script src="js/WaitingDialog.js"></script>
        <script>
            $(document).ready(function() {
                $.ajax({
                    url : 'userStorage?profile=1',
                    async: false,
                    type: "GET",
                    dataType: "text",
                    beforeSend: function() {
                        waitingDialog.show();
                    },
                    success: function(responseText) {
                        var props = responseText.split(";");
                        $("#username").val(props[0]);
                        $("#password").val(props[1]);
                        $("#nombre").val(props[2]);
                        $("#apellidos").val(props[3]);
                        $("#birthday").val(props[4]);
                        $("#email").val(props[5]);
                        $("#ciudad").val(props[6]);
                        $("#pais").val(props[7]);
                        waitingDialog.hide();
                        $("#username").prop("disabled", true);
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>
        </div>
        <div class="container" style="margin-bottom: 30px;">
            <div id="signupbox" style="margin-top:20px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-inverse">
                    <div id="inverse-heading" class="panel-heading">
                        <div class="panel-inverse-title">Profile</div>
                    </div>  
                    <div class="panel-body" >
                        <%@include file='profile/signupForm.jsp'%>
                     </div>
                </div>   
             </div> 
        </div>
    </body>
</html>
