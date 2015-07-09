<script src="js/fileinput.min.js"></script>
<script src="js/fileinput_locale_LANG.js"></script>
<link rel="stylesheet" href="css/fileinput.min.css"/>
<form id="signupform" class="form-horizontal" role="form" action="<c:url value='/userStorage?${_csrf.parameterName}=${_csrf.token}'/>" method="post" enctype="multipart/form-data">
    <div id="signupalert" style="display:none" class="alert alert-danger">
        <p>Error:</p>
        <span></span>
    </div>
    <div class="form-group"> 
        <div class="col-sm-12">
            <h3 style="font-family:arial; font-weight:bold; font-size:20px;text-align: center;">
                Selecciona Fotos importar:
            </h3>
            <div class="col-sm-offset-1" style="margin-bottom: 15px; width: 80%;">
                <input id="file" name="file" type="file" accept="image/*">
                <!--<input id="file" type="file" >-->
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="username" class="col-md-3 control-label">Username</label>
        <div class="col-md-9">
            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
            <input type="text" class="form-control" id="username2" name="username2" placeholder="Username" style="display: none">
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
        <label for="ciudad" class="col-md-3 control-label">Ciudad</label>
        <div class="col-md-9">
            <input type="text" id="ciudad" class="form-control" name="city" placeholder="Ciudad">
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
