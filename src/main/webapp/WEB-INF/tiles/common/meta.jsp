<%@ include file="/WEB-INF/tiles/common/taglibs.jsp" %>

<spring:url value="/resources/bootstrap" var="bootstrap" />
<spring:message var="siteTitle" code="site.title" />

		<title>${siteTitle}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="${bootstrap}/css/bootstrap.min.css" rel="stylesheet" media="screen">
		
		<link href="${bootstrap}/css/custom.css" rel="stylesheet" media="screen">
