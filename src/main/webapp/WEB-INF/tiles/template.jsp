<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/tiles/common/taglibs.jsp" %>

<spring:url value="/resources/bootstrap" var="bootstrap" />

<html>
	<head>
		<tiles:insertAttribute name="meta" />
	</head>
	<body>
		<!-- JavaScript plugins (requires jQuery) -->
		<script src="http://code.jquery.com/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="${bootstrap}/js/bootstrap.min.js"></script>
	
		<!-- Optionally enable responsive features in IE8. Respond.js can be obtained from https://github.com/scottjehl/Respond -->
		<script src="${bootstrap}/js/respond.min.js"></script>
			
		<div id="header">
		
			<tiles:insertAttribute name="header" />
			
		</div> <!-- /id="header" -->
		<div id="body">
		
			<tiles:insertAttribute name="body" />
			
		</div><!-- /id="body" -->
		<div id="footer">
		
			<tiles:insertAttribute name="footer" />
			
		</div><!-- /id="footer" -->
	</body>
</html>