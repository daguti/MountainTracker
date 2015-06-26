<%-- 
    Document   : photos
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
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/photo-gallery.js"></script>
        <script>
            $(document).ready(function() {
                
                $.ajax({
                    url : 'photos',
                    async: false,
                    type: "GET",
                    dataType: "text",
                    beforeSend: function() {
                      waitingDialog.show();  
                    },
                    success: function(responseText) {
                        $("#photoGallery").append(responseText);
                        waitingDialog.hide();
                    }
                });
                $('li img').on('click',function(){
                    openImageModal(this);
                });	
            });
        </script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <div class="row" style="text-align:center; border-bottom:1px dashed black;  padding:0 0 20px 0; margin-bottom:40px;">
                <h3 style="font-family:arial; font-weight:bold; font-size:30px;">PHOTO GALLERY</h3>
        </div>
        <sec:authorize access="hasRole('ROLE_USER')">
            <%@include file='upload/photoUploader.jsp'%>
                <div class="row" style="text-align:center; border-bottom:1px dashed black;  padding:0 0 20px 0; margin-bottom:40px;"></div>
        </sec:authorize>
        <%@include file='ImageView/carousel.jsp'%>
        <div id="photoGallery" class="container" style="margin-bottom: 30px;">
        </div>

        <%@include file='static/footer.jsp'%>
    </body>
</html>
