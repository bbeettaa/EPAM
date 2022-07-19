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
        .row.content {
            height: 793px;
            margin-top: -20.5px;
        }

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: white;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }
    </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/speaker/speakerHead.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">

        <div class="col-sm-2 sidenav" style="height: 100%">
            <div class="well">
                <form method="post" action="suggestReport" style="margin-left: 10px"><br>
                    <div class="createUser">
                        <input type="hidden" name="action" value="suggestReport">
                        <input type="hidden" name="eventId" value="${requestScope.event.id}"/>
                        <input type="submit" class="btn btn-default" value="<fmt:message key="title.suggestReport"/>"
                               name="Ok">
                    </div>
                </form>
                <br>
            </div>
        </div>


        <div class="col-sm-8 text-left">
            <table class="table table-hover">
                <caption style="font-size:160%;"><fmt:message key="title.eventInfo"/></caption>
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
                    </ul>
                </tr>
            </table>

            <table class="table table-hover">
                <caption style="font-size:160%;"><fmt:message key="title.allReports"/></caption>
                <tr>
                    <th><fmt:message key="label.report.id"/></th>
                    <th><fmt:message key="label.report.name"/></th>
                    <th><fmt:message key="label.speaker.name"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="report" items="${requestScope.reports}">
                    <tr>
                        <ul>
                            <td><c:out value="${report.id}"/></td>
                            <td><c:out value="${report.name}"/></td>
                            <td><c:out value="${report.speaker.login}"/></td>
                            <td>
                                <form method="post" action="" id="update">
                                    <input type="hidden" name="action" value="speakerUpdateReportPage">
                                    <input type="hidden" name="eventId" value="${requestScope.event.id}"/>
                                    <input type="hidden" name="reportId" value="${report.id}"/>
                                    <c:choose>
                                        <c:when test="${report.speaker.login == sessionScope.login}">
                                            <input type="submit" class="btn btn-info"
                                                   value="<fmt:message key="label.button.update"/>"/>
                                        </c:when>
                                    </c:choose>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="" id="dell">
                                    <input type="hidden" name="action" value="deleteReport">
                                    <input type="hidden" name="id" value="${requestScope.event.id}"/>
                                    <input type="hidden" name="reportId" value="${report.id}"/>
                                    <c:choose>
                                        <c:when test="${report.speaker.login == sessionScope.login}">
                                            <input type="submit" class="btn btn-danger"
                                                   value="<fmt:message key="label.button.delete"/>"/>
                                        </c:when>
                                    </c:choose>
                                </form>
                            </td>
                        </ul>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</div>


</body>
</html>