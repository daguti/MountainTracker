<div id="subidaExcel" class="row col-sm-offset-4">
    <div class="container-fluid">
        <form id="excel" action="<c:url value='/UploadDownloadFileServlet?${_csrf.parameterName}=${_csrf.token}&img=1'/>" method="post" enctype="multipart/form-data" role='form'>
            <div class="form-group"> 
                <div class="col-sm-12">
                    Selecciona Fotos importar:
                    <input id="file" type="file" name="fileName" multiple>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-1">
                    <input type="submit" id="uploadBtn" value="Upload" class="btn btn-primary">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
</div>
