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
<title><fmt:message key="student.course.title" /></title>
</head>
<body>
	<form name="exitForm" action="controller" method="post">
		<input type="hidden" name="command" value="exit" />
		<fmt:message key="login.button.exit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
	<h2>
		<fmt:message key="student.course.title" />
		<c:out value="${requestScope.courseData.name}"></c:out>
	</h2>

	<table cellpadding="3pt">
		<tr>
			<td><fmt:message key="student.course.finished" /> :</td>
			<td><c:out value="${requestScope.contractData.finishedPercent}"></c:out>%</td>
		</tr>
		<tr>
			<td><fmt:message key="student.course.mark" /> :</td>
			<c:set var="mark" value="${requestScope.contractData.mark}" />
			<c:choose>
				<c:when test="${mark eq null}">
					<td>---</td>
				</c:when>
				<c:otherwise>
					<td><c:out value="${mark}"></c:out></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td><fmt:message key="student.course.comment" /> :</td>
			<td><c:out value="${requestScope.contractData.comment}"></c:out>
		</tr>
	</table>
	<form name="StudentForm" action="controller" method="post">
		<button name="command" value="loginStudent">
			<fmt:message key="button.onmain" />
		</button>
	</form>
</body>
</html>