<%-- 
    Document   : routes
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
        <script src="js/MapsScript.js"></script>
        <script src="js/PageLoads.js"></script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <div class="container-fluid" style="margin-bottom: 30px;">
            <div class="row" style="text-align:center; border-bottom:1px dashed black;  padding:0 0 20px 0; margin-bottom:40px;">
                <h3 style="font-family:arial; font-weight:bold; font-size:30px;">ROUTES</h3>
            </div>
            <sec:authorize access="hasRole('ROLE_USER')">
                <%@include file='upload/routeUploader.jsp'%>
                <div class="row" style="text-align:center; border-bottom:1px dashed black;  padding:0 0 20px 0; margin-bottom:40px;"></div>
            </sec:authorize>
            <div class="row">
                <script src="js/TableLoad.js"></script>
                <%@include file='tables/routesTable.jsp'%>
            </div>
        </div>
        <%@include file='static/footer.jsp'%>
    </body>
</html>
