<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Create Event</title>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<form method="post" action="">
    <div class="createUser" style="margin-left: 45%">
        <br/><h2><fmt:message key="title.createEvent"/></h2>
        <input type="hidden" name="action" value="createEvent">
        <label><input type="text" name="nameToSet" minlength="5" maxlength="30"
                      required>: <fmt:message key="label.event.name"/> </label><label style="color: orange">*</label><br>
        <label><input type="date" name="dateToSet" minlength="5" maxlength="30"
                      required>: <fmt:message key="label.event.date"/> </label><label style="color: orange">*</label><br>
        <label><input type="time" name="timeToSet" required>: <fmt:message key="label.event.time"/> </label><label style="color: orange">*</label><br>

        <input type="submit" value="<fmt:message key="label.button.create"/>" name="Ok"><br>
    </div>
</form>

</body>
</html>
