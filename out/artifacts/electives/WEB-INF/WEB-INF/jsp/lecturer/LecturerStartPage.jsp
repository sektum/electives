<%@ page import="ua.epam.groys.electives.entities.AuthorizedUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytags" uri="/MyTagLib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.epam.electives.localization.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="lecturer.label.title" /></title>
</head>
<body>
	<form name="exitForm" action="controller" method="post">
		<input type="hidden" name="command" value="exit" />
		<fmt:message key="login.button.exit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
	<h2>
		<fmt:message key="lecturer.label.hello" />
		<c:out value="${sessionScope.authorizedUser.fullName}"></c:out>
	</h2>
	<fmt:message key="lecturerTable.column.noStudent" var="noStudent" />
	<fmt:message key="lecturerTable.column.studentName" var="studentName" />
	<fmt:message key="lecturerTable.column.mark" var="mark" />
	<fmt:message key="lecturerTable.column.studyProgress"
		var="studyProgress" />
	<fmt:message key="lecturerTable.column.buttonInfo" var="buttonInfo" />
	<mytags:lecturerTable lecturerData="${requestScope.lecturerData}"
		noStudent="${noStudent}" studentName="${studentName}" mark="${mark}"
		studyProgress="${studyProgress}" buttonInfo="${buttonInfo}" />

</body>
</html>