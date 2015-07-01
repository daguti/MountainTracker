<script src="js/fileinput.min.js"></script>
<script src="js/fileinput_locale_LANG.js"></script>
<link rel="stylesheet" href="css/fileinput.min.css"/>
<script>
    $(document).ready(function() {
        $("#file").fileinput({
            browseClass: "btn btn-success",
            browseLabel: "Pick GPX File",
            browseIcon: '<i class="glyphicon glyphicon-globe"></i>'
        });
    });
</script>
<div id="subidaExcel" class="row" style="margin-top: -40px;">
    <div class="container-fluid">
        <form id="excel" action="<c:url value='/UploadDownloadFileServlet?${_csrf.parameterName}=${_csrf.token}&aero=1'/>" method="post" enctype="multipart/form-data" role='form'>
            <div class="form-group"> 
                <div class="col-sm-12">
                    <h3 style="font-family:arial; font-weight:bold; font-size:20px;text-align: center;">
                        Selecciona fichero GPX para importar:
                    </h3>
                    <div class="col-sm-offset-1" style="margin-bottom: 15px; width: 80%;">
                        <input id="file" type="file" accept=".gpx">
                        <!--<input id="file" type="file" >-->
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-1" style="margin-left: 45%;">
                    <input type="submit" value="Upload" class="btn btn-primary">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
</div>