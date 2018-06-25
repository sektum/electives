<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.epam.electives.localization.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
	<h3>
		<fmt:message key="error.label.title" />
	</h3>
	<hr />
	<fmt:message key="error.label.unknown.error" />
	<hr />

	<form name="exitForm" action="controller" method="post">
		<input type="hidden" name="command" value="exit" />
		<fmt:message key="login.button.exit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
</body>
</html>