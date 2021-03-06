<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('ROLE_USER')">
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
        $(document).ready(function() {
            checkUnreadMessages(<%=session.getAttribute("unreadMsg")%>);
        });
    </script>
</sec:authorize>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
</form>
<div id="header" class="top">
    <div class="container-fluid">
        <div class="row" style="padding-top: 30px;">
            <div class="col-sm-7">
                <h1 id="headTitle">MOUNTAIN TRACKER</h1>
            </div>
            <div class="col-sm-3 col-sm-offset-2" style="padding-top: 13px;">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <h4 style="color: white;">
                        User : <a id="headUserName" href="perfil.jsp" style="color:rgb(199, 213, 129);">${pageContext.request.userPrincipal.name} </a>| 
                                 <a href="javascript:formSubmit()" style="color:#EA9B9B;"> Logout</a>
                    </h4>
		</c:if>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <h2>
                        <a class="btn btn-info" style="float: right;" href="login.jsp">Login</a>
                    </h2>
		</c:if>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-inverse">
    <ul class="nav navbar-nav">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">News
            <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="creaModNews.jsp">Create New</a></li>
                </sec:authorize>  
              <li><a href="news.jsp">View News</a></li>
            </ul>
        </li>
        <li><a href="routes.jsp">Routes</a></li>
        <li><a href="photos.jsp">Photos</a></li>
        <sec:authorize access="hasRole('ROLE_USER')">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#">Profile<span class="badge"></span>
              <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="perfil.jsp">My Account</a></li>
                <li><a href="myNews.jsp">My News</a></li>
                <li><a href="myRoutes.jsp">My Routes</a></li>
                <li><a href="myAlbums.jsp">My Albums</a></li>
                <li><a href="myPhotos.jsp">My Photos</a></li>
                <li><a href="messages.jsp">Messages<span class="badge"></span></a></li>
              </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin
              <span class="caret"></span></a>
              <ul class="dropdown-menu">
                    <li><a href="blockedUsers.jsp">Blocked Users</a></li>
                    <li><a href="weaponsGest.jsp">Create Weapons</a></li>
                    <li><a href="soldierGest.jsp">Create Soldier/Army types</a></li>
                    <li><a href="settlementProp.jsp">Settlement Proposal</a></li>
                    <li><a href="settlementGest.jsp">Create Settlements</a></li>
              </ul>
            </li>
        </sec:authorize>
    </ul>
</nav>
