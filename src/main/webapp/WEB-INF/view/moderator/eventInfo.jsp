<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
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
            margin-top: 94px;
        }
        .createUser input{
            width: 150px;
        }
        .search{
            border: 2px solid black;
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
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<div class="sidenav">

    <div class="search">
        <form method="post" action="" style="margin-left: 10px"><br/>
            <label><fmt:message key="title.createReport"/></label>
            <div class="createUser">
                <input type="hidden" name="action" value="createReportPage">
                <input type="hidden" name="eventId" value="${requestScope.event.id}"/>
                <input type="submit" value="<fmt:message key="label.button.create"/>" name="Ok"><br>
            </div>
        </form><br/>
    </div>

</div>


<table>
    <caption style="font-size:160%;"> <fmt:message key="title.eventInfo"/> </caption>
    <tr>
        <th><fmt:message key="label.event.id"/></th>
        <th><fmt:message key="label.event.name"/></th>
        <th><fmt:message key="label.event.date"/></th>
    </tr>

    <tr>
        <ul>
            <td><c:out value="${requestScope.event.id}"/></td>
            <td><c:out value="${requestScope.event.name}"/></td>
            <td><c:out value="${requestScope.event.dateBegin}"/>
                <c:out value="${requestScope.event.timeBegin}"/></td>
            <td>
                <form method="post" action="" id="updateEv">
                    <input type="hidden" name="action" value="updateEventPage">
                    <input type="hidden"  name="id" value="${requestScope.event.id}"/>
                    <input type="submit" value="<fmt:message key="label.button.update"/>"/>
                </form>
            </td>
            <td>
                <form method="post" action="" id="dellEv">
                    <input type="hidden" name="action" value="deleteEvent">
                    <input type="hidden"  name="id" value="${requestScope.event.id}"/>
                    <input type="submit" value="<fmt:message key="label.button.delete"/>"/>
                </form>
            </td>
            <td>
                <c:choose>
                    <c:when test="${requestScope.startEvent == true}">
                        <form method="post" action="" id="start">
                            <input type="hidden" name="action" value="stopEvent">
                            <input type="hidden" name="id" value="${requestScope.event.id}"/>
                            <input type="submit" value="<fmt:message key="label.button.stop"/>"/>
                        </form>
                    </c:when>
                    <c:when test="${requestScope.startEvent == false}">
                        <form method="post" action="" id="start">
                            <input type="hidden" name="action" value="startEvent">
                            <input type="hidden" name="id" value="${requestScope.event.id}"/>
                            <input type="submit" value="<fmt:message key="label.button.start"/>"/>
                        </form>
                    </c:when>
                </c:choose>
            </td>
        </ul>
    </tr>

</table>
/* statistic*/
<c:choose>
    <c:when test="${requestScope.startEvent == true}">
        <table>
            <caption style="font-size:160%;"> <fmt:message key="title.statistic"/> </caption>
            <tr>
                <th><fmt:message key="label.event.subs"/></th>
                <th><fmt:message key="label.event.joined"/></th>
            </tr>
            <tr>
                <ul>
                    <td><c:out value="${requestScope.event.subCount}"/></td>
                    <td><c:out value="${requestScope.joinedUsers}"/></td>
                </ul>
            </tr>

        </table>
    </c:when>
</c:choose>
/* reports */
<table>
    <caption style="font-size:160%;"> <fmt:message key="title.allUsers"/> </caption>
    <tr>
            <th><fmt:message key="label.report.id"/></th>
            <th><fmt:message key="label.report.name"/></th>
            <th><fmt:message key="label.speaker.login"/></th>
    </tr>
    <c:forEach var="report" items="${requestScope.event.reports}">
        <tr>
            <ul>
                <td><c:out value="${report.id}"/></td>
                <td><c:out value="${report.name}"/></td>
                <td><c:out value="${report.speaker.login}"/></td>
                <td>
                    <form method="post" action="" id="update">
                        <input type="hidden" name="action" value="updateReportPage">
                        <input type="hidden" name="eventId" value="${requestScope.event.id}"/>
                        <input type="hidden" name="reportId" value="${report.id}"/>
                        <input type="submit" value="<fmt:message key="label.button.update"/>"/>
                    </form>
                </td>
                <td>
                    <form method="post" action="" id="dell">
                        <input type="hidden" name="action" value="deleteReport">
                        <input type="hidden"  name="id" value="${requestScope.event.id}"/>
                        <input type="hidden"  name="reportId" value="${report.id}"/>
                        <input type="submit" value="<fmt:message key="label.button.delete"/>"/>
                    </form>
                </td>
            </ul>
        </tr>
    </c:forEach>
</table>


</body>
</html>
