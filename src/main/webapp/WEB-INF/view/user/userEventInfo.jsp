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
    <title>Event info</title>
    <style>
        .sidenav {
            height: 100%;
            width: 250px;
            padding: 9px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: darkgrey;
            overflow-x: hidden;

            color: black;
            text-decoration: none;
            display: compact;
            margin-top: 85px;
        }

        .createUser input {
            width: 150px;
        }

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
<jsp:include page="/WEB-INF/view/user/userHead.jsp"/>


<table>
    <caption style="font-size:160%;"><fmt:message key="title.eventInfo"/></caption>
    <tr>
        <th><fmt:message key="label.event.name"/></th>
        <th><fmt:message key="label.event.date"/></th>
    </tr>

    <tr>
        <ul>
            <td><c:out value="${requestScope.event.name}"/></td>
            <td><c:out value="${requestScope.event.dateBegin}"/>
                <c:out value="${requestScope.event.timeBegin}"/></td>
        </ul>
    </tr>

</table>

<table>
    <caption style="font-size:160%;"><fmt:message key="title.allUsers"/></caption>
    <tr>
        <th><fmt:message key="label.report.name"/></th>
        <th><fmt:message key="label.speaker.name"/></th>
    </tr>
    <c:forEach var="report" items="${requestScope.event.reports}">
        <tr>
            <ul>
                <td><c:out value="${report.name}"/></td>
                <td><c:out value="${report.speaker.name}"/>
                <c:out value="${report.speaker.surname}"/>
                </td>
            </ul>
        </tr>
    </c:forEach>
</table>


</body>
</html>
