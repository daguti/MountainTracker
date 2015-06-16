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
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/FormsValidations.js"> </script>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/MapsScript.js"></script>
        <script>
            $(document).ready(function() {
                waitingDialog.show();
                if(<%=session.getAttribute("publicNews")%> === null) {
                    $.ajax({
                        url : 'news?mine=1',
                        async: true,
                        type: "GET",
                        dataType: "text",
                        success: function(responseText) {
                            location.reload();
                            waitingDialog.hide();
                        }
                    });
                } else {
                    waitingDialog.hide();
                }
            });
        </script>
    </head>
    
    <body>
        <%@include file='static/header.jsp'%>
        <div class="container-fluid">
            <%=session.getAttribute("publicNews")%>
            <%session.setAttribute("publicNews", null);%>
        </div>
        <%@include file='static/footer.jsp'%>
    </body>
</html>
