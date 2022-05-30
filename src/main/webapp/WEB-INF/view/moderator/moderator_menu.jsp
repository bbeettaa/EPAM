<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        *{
            align-content: center;
        }

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

        .createUser input, select{
            width: 150px;
        }

        .columnMiddle{
            margin-left: 280px;
        }

        .search{
            border: 2px solid black;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 1px;
            text-align: center;
            min-width: 75%;
        }
    </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<div class="sidenav">
    <div class="search">
        <form method="post" action="" style="margin-left: 10px"><br>
            <input type="hidden" name="action" value="searchNameEvent">
            <div>
                <label for="searchName"><fmt:message key="label.searchByName"/>:</label>
                <input type="search" id="searchName" name="searchName" value="${requestScope.searchName}">
                <input type="submit" value="<fmt:message key="act.search"/>">
            </div><br>
        </form>
    </div><br>

    <div class="search">
        <form method="post" action="" style="margin-left: 10px"><br>
            <input type="hidden" name="action" value="sortEvent">
            <input type="radio" id="byName" name="sort" ${requestScope.sort == 'byName' ? "checked" : '' } value="byName"><label for="byName">byName</label><br>
            <input type="radio" id="byDate" name="sort" ${requestScope.sort == 'byDate' ? "checked" : '' } value="byDate"><label for="byDate">byDate</label><br>
            <input type="radio" id="byReports" name="sort" ${requestScope.sort == 'byReports' ? "checked" : '' } value="byReports"><label for="byReports">byReports</label><br>
            <input type="radio" id="bySubscribers" name="sort" ${requestScope.sort == 'bySubscribers' ? "checked" : '' } value="bySubscribers"><label for="bySubscribers">bySubscribers</label><br><br>

            <input type="radio" id="asc" name="order" ${requestScope.order == 'asc' ? "checked" : '' } value="asc"><label for="asc">asc</label>
            <input type="radio" id="desc" name="order" ${requestScope.order == 'desc' ? "checked" : '' } value="desc"><label for="desc">desc</label><br>

            <input type="submit" value="<fmt:message key="act.sort"/>">
        </form><br>
    </div>

    <br/><h2><fmt:message key="title.createNewUser"/></h2>
    <form method="post" action="">
        <div class="createUser" >
            <input type="hidden" name="action" value="createEventMenu">
            <input type="submit" value="<fmt:message key="act.createUser"/>" name="Ok"><br>
        </div>
    </form>
</div>

<div class="columnMiddle">

    <table>
        <caption style="font-size:160%;"> <fmt:message key="title.allUsers"/> </caption>
        <tr>
            <%--<th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.login"/></th>
            <th><fmt:message key="label.password"/></th>--%>
                <th>Name</th>
                <th>Date</th>
                <th>Subscribers</th>
                <th>Reports</th>
        </tr>
        <c:forEach var="entity" items="${requestScope.dao}">
            <tr>
                <ul>
                    <td><c:out value="${entity.name}"/></td>

                    <td><c:out value="${entity.dateBegin}"/>
                    <c:out value="${entity.timeBegin}"/></td>
                    <td><c:out value="${entity.subCount}"/></td>
                    <td><c:out value="${entity.reportsCount}"/></td>

                    <td>
                        <form method="post" action="" id="update">
                            <input type="hidden" name="action" value="infoEvent">
                            <input type="hidden"  name="id" value="${entity.id}"/>
                            <input type="submit" value="info"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="" id="dell">
                            <input type="hidden" name="action" value="deleteEvent">
                            <input type="hidden"  name="id" value="${entity.id}"/>
                            <input type="submit" value="delete"/>
                        </form>
                    </td>
                </ul>
            </tr>
        </c:forEach>
    </table>

</div>>








</body>
</html>