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
    <title>Update Report</title>
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
    <h2><fmt:message key="title.updateReport"/></h2>
    <label style="margin-left: 5%">${requestScope.reportId}: <fmt:message key="label.report.id"/></label>
    <form method="post" action="infoEvent">

        <input type="hidden" name="action" value="updateReport">
        <input type="hidden" name="id" value="${requestScope.reportId}"/>
        <input type="hidden" name="eventId" value="${requestScope.eventId}"/>

        <label><input type="text" name="nameToSet" value="${requestScope.report.name}" minlength="5" maxlength="30"
                      class="form-control text-center" style="width: 200px"
                      data-toggle="tooltip"
                      title="<fmt:message key="label.event.name"/>" data-placement="left"
                      required> </label> <label style="color: orange; font-size: 25px"> *</label><br>

        <c:choose>
        <c:when test="${requestScope.message != null}">
        <div class="form-group has-error  ">
            </c:when>
            <c:when test="${requestScope.message == null}">
            <div class="form-group ">
                </c:when>
                </c:choose>
        <label><input type="text" name="speakerLogin" value="${requestScope.report.speaker.login}" minlength="5"
                      class="form-control text-center" style="width: 200px"
                      data-toggle="tooltip"
                      title="<fmt:message key="label.speaker.login"/>" data-placement="left"
                      maxlength="30"></label>

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
        <input type="submit" value="<fmt:message key="label.button.update"/>" class="btn btn-default" name="Ok"
               style="width: 200px"><br>
    </form>
</div>

<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>

</body>
</html>