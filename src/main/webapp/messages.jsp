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
        <link rel="stylesheet" href="css/MyStyles.css"/>
        <title>MOUNTAIN TRACKER</title>
        <script src="js/FormsValidations.js"> </script>
        <script src="js/WaitingDialog.js"></script>
        <script src="js/TableLoad.js"></script>
        <script src="js/MapsScript.js"></script>
        <script>
            $(document).ready(function() {
                $('#userTo').autocomplete({
                    source: function(request, response) {
                      $.get( "MessageLoader?filtro='ok'", function( data ) {
                            var result = "";
                            var match = $("#userTo").val();
                            var values = data.split(",");
                            var i = 0;
                            while(i < values.length) {
                                if(values[i].indexOf(match) > -1) {
                                    result += values[i] + ",";
                                }
                                i++;
                            }
                            result.substring(0, result.lastIndexOf(","));
                            response(result.split(","));
                      });  
                    }
                });
            });
        </script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <div class="container-fluid" style="margin-bottom: 30px;">
            <ul class="nav nav-pills">
                <li class="active"><a data-toggle="pill" href="#received">Received</a></li>
                <li><a data-toggle="pill" href="#sended">Sended</a></li>
                <li><a data-toggle="pill" href="#new">New</a></li>
            </ul>
            <div class="tab-content">
                <div id="received" class="tab-pane fade in active">
                    <%@include file='tables/receivedMessages.jsp'%>
                </div>
                <div id="sended" class="tab-pane fade">
                    <%@include file='tables/sendedMessages.jsp'%>
                </div>
                <div id="new" class="tab-pane fade">
                    <form id="sendMessage" novalidate="novalidate" class="form-horizontal" role="form" action="<c:url value='/MessageLoader?${_csrf.parameterName}=${_csrf.token}&send=1'/>" method="post">
                        <div id="signupalert" style="display:none" class="alert alert-danger">
                            <p>Error:</p>
                            <span></span>
                        </div>
                        <div class="form-group">
                            <label for="userTo" class="col-md-3 control-label">User To</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="userTo" name="userTo" placeholder="Ref. Titulo">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subject" class="col-md-3 control-label">Subject</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="subject" name="subject" placeholder="Ref. Titulo">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="text" class="col-md-3 control-label">Message</label>
                            <div class="col-md-9">
                                <%@include file='editors/textEditor.jsp'%>
                                <input type="text" class="form-control" id="text" name="text" style="display: none;">
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Button -->                                        
                            <div class="col-md-offset-3 col-md-4">
                                <input id="btn-send" name="submit" type="submit" value="Send" class="btn btn-info"/>
                            </div>
                            <div class="col-md-4">
                                <a onClick="goBack()" class="btn btn-primary">Atras</a>
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
