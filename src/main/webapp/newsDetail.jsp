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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="//cdn.ckeditor.com/4.4.7/full/ckeditor.js"></script>
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/photo-gallery.js"></script>
        <script src="js/PageLoads.js"></script>
        <script>
            $(document).ready(function() {
               $('li img').on('click',function(){
                    openImageModalAlbum(this);
                });
                CKEDITOR.disableAutoInline = true;
                CKEDITOR.inline( 'showEditor', {
                    removePlugins: 'toolbar'
                } );
            });
        </script>
    </head>
    
    <body>
        <%@include file='static/header.jsp'%>
        <%@include file='ImageView/carousel.jsp'%>
        <div class="container-fluid" style="margin-bottom: 30px;">
            <%=session.getAttribute("detailNew")%>
            <%session.setAttribute("detailNew", null);%>
            <div class="row">
                <div class ="col-xs-offset-5 col-sm-offset-5 col-sm-1" style="margin-bottom: 20px;">
                    <a onClick="goBack()" class="btn btn-primary">Back</a>
                </div>
            </div>
        </div>
        <%@include file='static/footer.jsp'%>
    </body>
</html>
