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
        <script src="js/MapsScript.js"></script>
        <script>
            $(document).ready(function() {
                waitingDialog.show();
                $.ajax({
                    url : 'photos',
                    async: false,
                    type: "GET",
                    dataType: "text",
                    success: function(responseText) {
                        $("#photoGallery").append(responseText);
                    }
                });
                $.ajax({
                    url : 'photos?carousel=1',
                    async: false,
                    type: "GET",
                    dataType: "text",
                    success: function(responseText) {
                        $("#myCarousel").append(responseText);
                    }
                });
                waitingDialog.hide();
            });
        </script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <sec:authorize access="hasRole('ROLE_USER')">
            <%@include file='upload/photoUploader.jsp'%>
        </sec:authorize>
        <%@include file='ImageView/carousel.jsp'%>
        <div id="photoGallery" class="container">
            <h2>Photo Gallery</h2>

        </div>

        <%@include file='static/footer.jsp'%>
    </body>
</html>