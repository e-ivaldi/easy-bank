<%@ include file="/WEB-INF/tiles/common/taglibs.jsp" %>

<spring:url value="/logout" var="logoutUrl" />

<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
	<c:set var="username">
		<c:url value="guest" />
	</c:set>
</sec:authorize>

<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:set var="username">
		<c:url value="${user.username}" />
	</c:set>
	<c:set var="loggedIn" value="true" />
</sec:authorize>

<spring:message var="welcome" code="welcome" arguments="${username}" />

	<div class="row">
		<div class="col-lg-12">
			<ul class="breadcrumb text-right">
				<li class="active">${welcome}</li>				
				<c:if test="${not empty loggedIn}">
					<li><a href="${logoutUrl}">Logout</a></li>
				</c:if>				
			</ul>
		</div>
	</div> <!-- /class="row" -->