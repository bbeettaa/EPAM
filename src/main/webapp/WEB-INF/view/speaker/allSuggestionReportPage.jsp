<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>My suggestions</title>
    <style>

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 1px;
            text-align: center;
            min-width: 35%;

            margin-left: 34%;
        }
    </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/speaker/speakerHead.jsp"/>

<table>
    <caption style="font-size:160%;"> <fmt:message key="title.suggestedReports"/> </caption>
    <tr>
        <th><fmt:message key="label.event.name"/></th>
        <th><fmt:message key="label.report.name"/></th>
        <th><fmt:message key="label.speaker.login"/></th>
    </tr>
    <c:forEach var="report" items="${requestScope.dao}">
        <tr>
            <ul>
                <td><c:out value="${requestScope.events.get(report.eventId).name}"/></td>
                <td><c:out value="${report.name}"/></td>
                <td><c:out value="${report.speaker.login}"/></td>
                <td>
                    <form method="post" action="" id="dell">
                        <input type="hidden" name="action" value="deleteSuggestReport">
                        <input type="hidden" name="id" value="${requestScope.event.id}"/>
                        <input type="hidden" name="reportName" value="${report.name}"/>
                        <c:choose>
                            <c:when test="${report.speaker.login == sessionScope.login}">
                                <input type="submit" value="delete"/>
                            </c:when>
                        </c:choose>
                    </form>
                </td>
            </ul>
        </tr>
    </c:forEach>
</table>


</body>
</html>
