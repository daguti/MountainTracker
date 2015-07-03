<%-- 
    Document   : myAlbums
    Created on : Jun 30, 2015, 2:09:27 PM
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
        <script src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/FormsValidations.js"> </script>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/photo-gallery.js"></script>
        <script src="js/PageLoads.js"></script>
        <script>
            $(document).ready(function() {
               loadMyAlbums();
            });
        </script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <%@include file='ImageView/addToAlbum.jsp'%>
        <%@include file='ImageView/carousel.jsp'%>
        <div class="container-fluid" style="margin-bottom: 30px;">
            <ul class="nav nav-pills" style="margin-bottom: 40px;">
                <li class="active"><a data-toggle="pill" href="#albums">Albums<span class="badge"></span></a></li>
                <li><a data-toggle="pill" href="#create">Create Album</a></li>
            </ul>
            <div class="tab-content">
                <div id="albums" class="tab-pane fade in active">
                    <div id="albumButtons" style="display: none;" class="row">
                        <div class="col-sm-offset-4 col-sm-4">
                            <a id="addPhotos" onClick="loadAddToAlbumModal()" class="btn btn-primary" style="display: none;"><i style="color: white;" class="glyphicon glyphicon-plus"></i>Add Images</a>
                            <a id="backAlbums" onClick="backToAlbums()" class="btn btn-primary" style="display: none;">Back</a>
                        </div>
                    </div>
                    <div id="albumPhotoGallery" class="container" style="margin-bottom: 30px;margin-top: 30px;display: none;">
                    </div>
                </div>
                <div id="create" class="tab-pane fade">
                    <div class='panel panel-inverse' style="margin-left: 10%;margin-right: 10%;">
                        <div id='inverse-heading' class='panel-heading' style='padding:10px 15px;'>
                            <div class='panel-inverse-title'>
                                <i style="color: white;" class="glyphicon glyphicon-book"></i>
                                <h4 style="display: inline;">New Album</h4>
                            </div>
                        </div>
                        <div class='panel-body'>
                            <form id="createAlbum" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/AlbumLoader?${_csrf.parameterName}=${_csrf.token}&create=1'/>" method="post">
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                <div class="form-group">
                                    <label for="albumName" class="col-md-3 control-label">Album Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" id="album_name" name="albumName" placeholder="Album Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-xs-offset-2 col-sm-offset-4 col-xs-1">
                                        <input id="btn-send" name="submit" type="submit" value="Create" class="btn btn-info"/>
                                    </div>
                                    <div class="col-xs-offset-2 col-xs-1">
                                        <a onClick="goBack()" class="btn btn-primary">Back</a>
                                    </div>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file='static/footer.jsp'%>
    </body>
</html>
