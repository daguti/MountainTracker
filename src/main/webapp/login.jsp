<%-- 
    Document   : registrar
    Created on : Apr 27, 2015, 11:57:54 AM
    Author     : ESa10969
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
            $(document).ready(function(){
               if(location.href.indexOf("error") !== -1) {
                   $('#login-alert').show();
                   $('#loginUsername').css('border-color', 'red');
                   $('#loginPassword').css('border-color', 'red');
               } 
            });
            $("#file").ready(function() {
                $("h3").hide();
                $("#file").hide();
            });
        </script>
    </head>
    <body onload='$("#loginUsername").focus();'>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>
        </div>
        <div style="display:none; text-align: center;" id="login-alert" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Invalid User or Password!</strong>
        </div>
        <div class="container" style="margin-bottom: 30px;">    
            <div id="loginbox" style="margin-top:10px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-inverse" > 
                        <div style="padding-top:30px" class="panel-body" >
                            <form id="loginForm" name='loginForm' novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/login' />" method='POST'>
                                <div style="margin-bottom: 25px" class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input id="loginUsername" type="text" class="form-control" name="loginUsername" value="" placeholder="Username">                                        
                                        </div>
                                <div style="margin-bottom: 25px" class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input id="loginPassword" type="password" class="form-control" name="loginPassword" placeholder="Password">
                                        </div>   
                                    <div style="margin-top:10px" class="form-group">
                                        <!-- Button -->
                                        <div class="col-sm-12 controls">
                                          <input name="submit" type="submit" value="Login" class="btn btn-success"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-12 control">
                                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                                ¿No estas registrado?
                                            <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                                Registrate aquí
                                            </a>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>     
                            </div>                     
                        </div>  
            </div>
            <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-inverse">
                    <div id="inverse-heading" class="panel-heading">
                        <div class="panel-inverse-title">Registrar</div>
                        <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" style="color: white;" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Login</a></div>
                    </div>  
                    <div class="panel-body" >
                        <%@include file='profile/signupForm.jsp'%>
                     </div>
                </div>   
             </div> 
        </div>
        <footer class="footer">
            <%@include file='static/footer.jsp'%>
        </footer>
    </body>
</html>
