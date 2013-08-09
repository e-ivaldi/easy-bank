<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<spring:url value="/resources/bootstrap" var="bootstrap" />
<spring:url value="/login-process" var="loginUrl" />
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

<spring:message var="siteTitle" code="site.title" />
<spring:message var="indexTitle" code="index.title" />
<spring:message var="indexDescription" code="index.description" />
<spring:message var="login" code="login"  />
<spring:message var="welcome" code="welcome" arguments="${username}" />
<spring:message var="formUsername" code="index.login.form.username" />
<spring:message var="formPassword" code="index.login.form.password" />
<spring:message var="formSubmit" code="index.login.form.submit" />