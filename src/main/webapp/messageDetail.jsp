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
        <script src="js/WaitingDialog.js"></script>
        <script src="js/PageLoads.js"></script>
        <script>
            $(document).ready(function() {
                CKEDITOR.disableAutoInline = true;
                CKEDITOR.inline( 'messageText', {
                    removePlugins: 'toolbar',
                    uiColor : '#EEE'
                } );
                loadMessageDetail(<%=request.getParameter("userFrom")%>, <%=request.getParameter("userTo")%>,
                                  <%=request.getParameter("date")%>, <%=request.getParameter("subject")%>,
                                  <%=request.getParameter("text")%>, <%=request.getParameter("isRead")%>);
                
            });
        </script>
    </head>
    <body>
        <%@include file='static/header.jsp'%>
        <div class="container-fluid" style="margin-bottom: 30px;">
            <div class='panel panel-inverse' style="margin-left: 10%;margin-right: 10%;">
                <div id='inverse-heading' class='panel-heading' style='padding:10px 15px;'>
                    <div class='panel-inverse-title'>
                        <span style="color: white;" class="glyphicon glyphicon-envelope"></span>
                        <h4 style="display: inline;">Message</h4>
                    </div>
                </div>
                <div class='panel-body'>
                    <form novalidate="novalidate" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="userFrom" class="col-sm-1 control-label">FROM</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="userFrom" name="userFrom">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="userTo" class="col-sm-1 control-label">TO</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="userTo" name="userTo">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="date" class="col-sm-1 control-label">Date</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="date" name="date">
                            </div>
                            <div class="col-sm-7" id="isReadAl">
                                <label for="isRead" class="col-sm-4 control-label">Is Read?</label>
                                <div class="col-sm-8" style="padding-right: 0px;">
                                    <input type="text" class="form-control" id="isRead" name="isRead">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subject" class="col-sm-1 control-label">Subject</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" id="subject" name="subject">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="text" class="col-sm-1 control-label">Text</label>
                            <div class="col-sm-11" id="messageArea">
                                <textarea id="messageText" contenteditable="false">"></textarea>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <!-- Button -->                                        
                            <div class="col-xs-offset-2 col-sm-offset-4 col-xs-1">
                                <a onClick="deleteMessage(<%=request.getParameter("id")%>)" name="delete" class="btn btn-danger">Delete</a>
                            </div>
                            <div class="col-xs-offset-2 col-xs-1">
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
