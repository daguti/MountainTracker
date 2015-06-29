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
        <script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>
        <script src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>

        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/TableLoad.js"></script>
        <script src="js/MapsScript.js"></script>
        <script src="js/PageLoads.js"></script>
        <script>
            $(document).ready(function() {
                loadRouteDetails(<%=request.getParameter("name")%>, <%=request.getParameter("description")%>,
                                 <%=request.getParameter("distance")%>, <%=request.getParameter("ascend")%>, 
                                 <%=request.getParameter("descend")%>, <%=request.getParameter("minHeight")%>,
                                 <%=request.getParameter("maxHeight")%>, <%=request.getParameter("id")%>);
            });
        </script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <div class="container-fluid" style="margin-bottom: 30px;">
            <div class='panel panel-inverse'>
                <div class='panel-inverse-title' style='font-size:30px;'>
                    
                </div>
                <div class='panel-body'>
                    <form novalidate="novalidate" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="routeName" class="col-md-3 control-label">Route Name</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="routeName" name="routeName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-md-3 control-label">Description</label>
                            <div class="col-md-9">
                                <textarea id="description" style="width: 100%; height: 100%;">
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="distance" class="col-md-3 control-label">Distance(m)</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="distance" name="distance">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="totalAscend" class="col-md-3 control-label">Total Ascend(m)</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="totalAscend" name="totalAscend">
                            </div>
                            <label for="totalDescend" class="col-md-3 control-label">Total Descend(m)</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="totalDescend" name="totalDescend">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="minHeight" class="col-md-3 control-label">Min Height(m)</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="minHeight" name="minHeight">
                            </div>
                            <label for="maxHeight" class="col-md-3 control-label">Max Height(m)</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="maxHeight" name="maxHeight">
                            </div>
                        </div>
                        <div class="form-group form-group-lg">
                            <div class="col-md-offset-3 col-md-6">
                                <div class='panel panel-inverse'>
                                    <div class='panel-inverse-title' style='font-size:20px;'>
                                        ROUTE MAP
                                    </div>
                                    <div class='panel-body'>
                                        <div id="googleMap" style="width:100%;height:300px;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-5 col-md-4">
                                <a onClick="goBack()" class="btn btn-primary">Back</a>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
            </div>
        </div>
        <%@include file='static/footer.jsp'%>
    </body>
</html>
