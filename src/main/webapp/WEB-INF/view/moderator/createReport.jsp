<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Create Report</title>
    <style>
        .update {
            margin-left: 45%;
        }
    </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<div class="update">
    <br/>
    <h2><fmt:message key="title.createNewUser"/></h2>
    <label style="margin-left: 5%">${eventId}: <fmt:message key="title.eventId"/></label>
    <form method="post" action="">

        <input type="hidden" name="action" value="createReport">
        <input type="hidden" name="id" value="${reportId}"/>
        <input type="hidden" name="eventId" value="${eventId}"/>
        <label><input type="text" name="nameToSet" value="${requestScope.report.name}" minlength="5" maxlength="30"
                      required>: <fmt:message key="label.name"/> </label><label style="color: orange">*</label><br>
        <label><input type="text" name="speakerLogin" value="${requestScope.report.speaker.login}" minlength="5"
                      maxlength="30">: <fmt:message key="label.speaker"/> </label>
        <c:if test="${message != null}"/>
        <c:choose>
            <c:when test="${message == 'non-existent'}">
                <label style="color: red"><fmt:message key="lable.non_existent_user"/></label>
            </c:when>
            <c:when test="${message == 'not speaker'}">
                <label style="color: red"><fmt:message key="lable.user is not speaker"/></label>
            </c:when>
        </c:choose><br>

        <input type="submit" value="<fmt:message key="act.createUser"/>" name="Ok"><br>

    </form>
</div>
</body>
</html>