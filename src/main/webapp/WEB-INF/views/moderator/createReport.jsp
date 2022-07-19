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
    <h2><fmt:message key="title.createReport"/></h2>
    <label style="margin-left: 5%">${requestScope.eventId}: <fmt:message key="label.event.id"/></label>
    <form method="post" action="infoEvent">

        <input type="hidden" name="action" value="createReport">
        <input type="hidden" name="id" value="${requestScope.reportId}"/>
        <input type="hidden" name="eventId" value="${requestScope.eventId}"/>

            <label><input type="text" name="nameToSet" value="${requestScope.report.name}" minlength="5"
                          class="form-control" placeholder="<fmt:message key="label.event.name"/>"
                          maxlength="30"
                          required></label> <label style="color: orange; font-size: 25px"> *</label><br>

        <c:if test="${requestScope.message != null}"/>
        <c:choose>
        <c:when test="${requestScope.message != null}">
        <div class="form-group has-error  ">
            </c:when>
            <c:when test="${requestScope.message == null}">
            <div class="form-group ">
                </c:when>
                </c:choose>
            <label><input type="text" name="speakerLogin" value="${requestScope.report.speaker.login}" minlength="5"
                          class="form-control" placeholder="<fmt:message key="label.speaker.login"/>"
                          maxlength="30"> </label>
            <c:if test="${requestScope.message != null}"/>
            <c:choose>
                <c:when test="${requestScope.message == 'non-existent'}">
                    <label style="color: red"><fmt:message key="label.user.login.nonExist"/></label>
                </c:when>
                <c:when test="${requestScope.message == 'not speaker'}">
                    <label style="color: red"><fmt:message key="label.speaker.notSpeaker"/></label>
                </c:when>
            </c:choose><br>
        </div>
        <input type="submit" value="<fmt:message key="label.button.create"/>" class="btn btn-default" name="Ok"
                       style="width: 200px"><br>

    </form>
</div>
</body>
</html>