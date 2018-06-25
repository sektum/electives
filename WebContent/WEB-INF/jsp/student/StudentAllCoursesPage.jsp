<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="courses.title" /></title>
</head>
<body>
	<form name="exitForm" action="controller" method="post">
		<input type="hidden" name="command" value="exit" />
		<fmt:message key="login.button.exit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>

	<fmt:message key="coursesTable.column.noCourses" var="noCourses" />
	<fmt:message key="table.column.courseName" var="courseName" />
	<fmt:message key="table.column.lecturerName" var="lecturerName" />
	<fmt:message key="coursesTable.column.buttonSubscribe"
		var="buttonSubscribe" />
	<mytags:coursesTable coursesData="${requestScope.coursesData}"
		noCourses="${noCourses}" lecturerName="${lecturerName}"
		courseName="${courseName}" buttonSubscribe="${buttonSubscribe}" />

	<form name="StudentForm" action="controller" method="post">
		<button name="command" value="loginStudent">
			<fmt:message key="button.onmain" />
		</button>
	</form>
</body>
</html>