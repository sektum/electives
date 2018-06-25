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
<title><fmt:message key="lecturer.student.title" /></title>
</head>
<body>
	<form name="exitForm" action="controller" method="post">
		<input type="hidden" name="command" value="exit" />
		<fmt:message key="login.button.exit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
	<h2>
		<fmt:message key="lecturer.student.label" />
		<c:out value="${requestScope.studentData.fullName}"></c:out>
	</h2>
	<form name="StudentForm" action="controller" method="post">
		<input type="hidden" name="contractId"
			value="${requestScope.contractData.id}" />
		<table cellpadding="3pt">
			<tr>
				<td><fmt:message key="student.course.finished" /> :</td>
				<c:set var="finishedPercent"
					value="${requestScope.contractData.finishedPercent}" />
				<td><input type="text" name="courseFinished" size="3"
					value="${finishedPercent}" />%</td>
			</tr>
			<tr>
				<td><fmt:message key="student.course.mark" /> :</td>
				<c:set var="mark" value="${requestScope.contractData.mark}" />
				<c:choose>
					<c:when test="${mark eq null}">
						<td><input type="text" name="mark" size="2" value="---" /></td>
					</c:when>
					<c:otherwise>
						<td><input type="text" name="mark" size="2" value="${mark}" /></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td><fmt:message key="student.course.comment" /> :</td>
				<c:set var="comment" value="${requestScope.contractData.comment}" />
				<td><textarea cols="40" rows="5" name="comment">${comment}</textarea></td>
			</tr>
		</table>

		<button name="command" value="saveLecturerStudent">
			<fmt:message key="button.save" />
		</button>
		<button name="command" value="loginLecturer">
			<fmt:message key="button.onmain" />
		</button>
	</form>
</body>
</html>