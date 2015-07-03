<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="addAlbumModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="border-radius: 8px;"> 
        <div class="modal-header" style="background-color: black; border-radius: 8px 8px 0px 0px">
            <div style="display:none; text-align: center;" id="add-alert" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Photos added succesfully!</strong>
            </div>
            <h3 style="display: inline;color: white;"></h3>
            <a style="float: right;" class="btn btn-info" onclick="addImagesToAlbum()"><i class="glyphicon glyphicon-plus"></i>Add</a>
        </div>
        <div class="modal-body">   
            <div id="photoGallery" class="container-fluid" style="margin-bottom: 30px;">
            </div>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
