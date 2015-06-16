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
    </head>
    <body onload='$("#loginUsername").focus();'>
        <div class="container-fluid">
            <div class="row">
                <%@include file='static/header.jsp'%>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
            <script>$('#login-alert').show();</script>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <div class="container">    
            <div id="loginbox" style="margin-top:10px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-inverse" > 
                        <div style="padding-top:30px" class="panel-body" >
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
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
                                <form id="signupform" class="form-horizontal" role="form" action="<c:url value='/userStorage'/>" method="post">
                                    <div id="signupalert" style="display:none" class="alert alert-danger">
                                        <p>Error:</p>
                                        <span></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="username" class="col-md-3 control-label">Username</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-md-3 control-label">Password</label>
                                        <div class="col-md-9">
                                            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre" class="col-md-3 control-label">Nombre</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="nombre" name="name" placeholder="Nombre">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="apellidos" class="col-md-3 control-label">Apellidos</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="apellidos" name="surname" placeholder="Apellidos">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="birthday" class="col-md-3 control-label">Nacimiento</label>
                                        <div class="col-md-9">
                                            <input type="text" id="birthday" class="form-control" name="birthday" placeholder="Nacimiento">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="col-md-3 control-label">Email</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="city" class="col-md-3 control-label">Ciudad</label>
                                        <div class="col-md-9">
                                            <input type="text" id="direccion" class="form-control" name="city" placeholder="Ciudad">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="pais" class="col-md-3 control-label">País</label>
                                        <div class="col-md-9">
                                            <input type="text" id="pais" class="form-control" name="country" placeholder="País">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <!-- Button -->                                        
                                        <div class="col-md-offset-3 col-md-9">
                                            <input id="btn-signup" name="submit" type="submit" value="Registrar" class="btn btn-info"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </form>
                             </div>
                        </div>   
             </div> 
        </div>
        <footer class="footer">
            <%@include file='static/footer.jsp'%>
        </footer>
    </body>
</html>
