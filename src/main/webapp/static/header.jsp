<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
</form>
<div class="top">
    <div class="container-fluid">
        <div class="row" style="padding-top: 30px;">
            <div class="col-sm-7">
                <!--<a href="index.jsp"><img src="css/img/logoAgencia.gif"/></a>-->
                <h1>MOUNTAIN TRACKER</h1>
            </div>
            <div class="col-sm-3 col-sm-offset-1">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <h4>
                        User : <a href="perfil.jsp">${pageContext.request.userPrincipal.name} </a>| 
                                 <a href="javascript:formSubmit()"> Logout</a>
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
        <li><a href="news.jsp">News</a></li>
        <li><a href="routes.jsp">Routes</a></li>
        <li><a href="photos.jsp">Photos</a></li>
        
        <sec:authorize access="hasRole('ROLE_USER')">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#">Profile
              <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="personalData.jsp">Personal Data</a></li>
                <li><a href="account.jsp">My Account</a></li>
                <li><a href="myRoutes.jsp">My Routes</a></li>
                <li><a href="myPhotos.jsp">My Photos</a></li>
                <li><a href="messages.jsp">Messages</a></li>
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
