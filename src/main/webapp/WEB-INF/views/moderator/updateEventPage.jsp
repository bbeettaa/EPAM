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
    <title>Update Event</title>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<form method="post" action="">
    <div class="createUser" style="margin-left: 45%">
        <br/>
        <h2><fmt:message key="title.updateEvent"/></h2>
        <input type="hidden" name="action" value="updateEvent">
        <input type="hidden" name="id" value="${requestScope.event.id}">
        <label><input type="text" name="nameToSet" value="${requestScope.event.name}" minlength="5" maxlength="30"
                      class="form-control text-center" data-toggle="tooltip"
                      title="<fmt:message key="label.event.name"/>" data-placement="left"
                      style="width: 200px"
                      placeholder="<fmt:message key="label.event.name"/>"
                      required></label> <label style="color: orange; font-size: 25px"> *</label><br>
        <label><input type="date" name="dateToSet" value="${requestScope.event.dateBegin}" minlength="5" maxlength="30"
                      class="form-control text-center"
                      style="width: 200px" data-toggle="tooltip" title="<fmt:message key="label.event.date"/>"
                      data-placement="left"
                      placeholder="<fmt:message key="label.event.date"/>"
                      required> </label> <label style="color: orange; font-size: 25px"> *</label><br>
        <label><input type="time" name="timeToSet" value="${requestScope.event.timeBegin}"
                      class="form-control text-center" style="width: 200px"
                      placeholder="<fmt:message key="label.event.time"/>" data-toggle="tooltip"
                      title="<fmt:message key="label.event.time"/>" data-placement="left"
                      required> </label> <label style="color: orange; font-size: 25px"> *</label><br><br>

        <input type="submit" value="<fmt:message key="label.button.update"/>" class="btn btn-default" name="Ok"
               style="width: 200px"><br>
    </div>
</form>

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>

</body>
</html>