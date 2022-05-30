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
    <title>Suggest Report</title>
    <style>
        .update {
            margin-left: 45%;
        }
    </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/speaker/speakerHead.jsp"/>

<div class="update">
    <br/>
    <h2><fmt:message key="title.createNewUser"/></h2>
    <label style="margin-left: 5%">${requestScope.eventId}: <fmt:message key="title.eventId"/></label>
    <form method="post" action="">
        <input type="hidden" name="action" value="suggestReportCommand">
        <input type="hidden" name="eventId" value="${requestScope.eventId}"/>
        <input type="hidden" name="speakerLogin" value="${requestScope.login}"/>
        <label><input type="text" name="nameToSet" value="${nameToSet}" minlength="5" maxlength="30"
                      required>: <fmt:message key="label.name"/> </label><label style="color: orange">*</label><br>
        <label><input type="text" name="eventName" value="${requestScope.eventName}" minlength="5" maxlength="30"
                      required>: <fmt:message key="label.name"/> </label><label style="color: orange">*</label>
        <c:if test="${requestScope.message != null}"/>
        <c:choose>
            <c:when test="${requestScope.message == 'error'}">
                <label style="color: red"><fmt:message key="lable.non_existent_event"/></label>
            </c:when>
        </c:choose><br>

        <label><strong>${requestScope.login}</strong>: <fmt:message key="label.speaker"/> </label>

        <input type="submit" value="<fmt:message key="act.createUser"/>" name="Ok"><br>

    </form>
</div>
</body>
</html>