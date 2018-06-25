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
<title><fmt:message key="student.label.title" /></title>
</head>
<body>
	<form name="exitForm" action="controller" method="post">
		<input type="hidden" name="command" value="exit" />
		<fmt:message key="login.button.exit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
	<h2>
		<fmt:message key="student.label.hello" />
		<c:out value="${sessionScope.authorizedUser.fullName}"></c:out>
	</h2>
	<fmt:message key="studentTable.column.noCourses" var="noCourses" />
	<fmt:message key="table.column.courseName" var="courseName" />
	<fmt:message key="table.column.lecturerName" var="lecturerName" />
	<fmt:message key="studentTable.column.courseProgress"
		var="courseProgress" />
	<fmt:message key="studentTable.column.mark" var="mark" />
	<fmt:message key="studentTable.column.buttonInfo" var="buttonInfo" />
	<fmt:message key="studentTable.column.buttonUnsubscribe"
		var="buttonUnsubscribe" />
	<mytags:studentTable studentData="${requestScope.studentData}"
		noCourses="${noCourses}" lecturerName="${lecturerName}"
		courseProgress="${courseProgress}" courseName="${courseName}"
		mark="${mark}" buttonUnsubscribe="${buttonUnsubscribe}"
		buttonInfo="${buttonInfo}" />
	<form name="coursesListForm" action="controller" method="post">
		<input type="hidden" name="command" value="allCourses" />
		<fmt:message key="student.button.allCourses" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
</body>
</html>