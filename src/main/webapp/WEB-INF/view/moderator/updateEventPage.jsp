<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Update Event</title>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<form method="post" action="">
    <div class="createUser" style="margin-left: 45%">
        <br/><h2><fmt:message key="title.updateEvent"/></h2>
        <input type="hidden" name="action" value="updateEvent">
        <input type="hidden" name="id" value="${requestScope.event.id}">
        <label><input type="text" name="nameToSet" value="${requestScope.event.name}" minlength="5" maxlength="30" required>: <fmt:message key="label.event.name"/> </label><label style="color: orange">*</label><br>
        <label><input type="date" name="dateToSet" value="${requestScope.event.dateBegin}" required>: <fmt:message key="label.event.date"/> </label><label style="color: orange">*</label><br>
        <label><input type="time" name="timeToSet" value="${requestScope.event.timeBegin}" >: <fmt:message key="label.event.time"/> </label><label style="color: orange">*</label><br>

        <input type="submit" value="<fmt:message key="label.button.update"/>" name="Ok"><br>
    </div>
</form>

</body>
</html>