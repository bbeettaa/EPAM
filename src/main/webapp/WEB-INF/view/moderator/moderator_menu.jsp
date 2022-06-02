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
            <input type="hidden" name="action" value="moderatorMenu">
            <div>
                <label for="searchName"><fmt:message key="label.button.searchByName"/>:</label>
                <input type="search" id="searchName" name="searchName" value="${requestScope.searchName}">
                <input type="hidden" id="bySubscribers1" name="sort"  value="${requestScope.sort}">
                <input type="hidden" id="asc1" name="order" value="${requestScope.order}">
                <input type="submit" value="<fmt:message key="label.button.search"/>">
            </div><br>
        </form>
    </div><br>

    <div class="search">
        <form method="post" action="" style="margin-left: 10px"><br>
            <input type="hidden" name="action" value="moderatorMenu">
            <input type="radio" id="byName" name="sort" ${requestScope.sort == 'byName' ? "checked" : '' } value="byName"><label for="byName"><fmt:message key="label.sort.byName"/></label><br>
            <input type="radio" id="byDate" name="sort" ${requestScope.sort == 'byDate' ? "checked" : '' } value="byDate"><label for="byDate"><fmt:message key="label.sort.byDate"/></label><br>
            <input type="radio" id="byReports" name="sort" ${requestScope.sort == 'byReports' ? "checked" : '' } value="byReports"><label for="byReports"><fmt:message key="label.sort.byReports"/></label><br>
            <input type="radio" id="bySubscribers" name="sort" ${requestScope.sort == 'bySubscribers' ? "checked" : '' } value="bySubscribers"><label for="bySubscribers"><fmt:message key="label.sort.bySubs"/></label><br><br>

            <input type="radio" id="asc" name="order" ${requestScope.order == 'asc' ? "checked" : '' } value="asc"><label for="asc"><fmt:message key="label.sort.asc"/></label>
            <input type="radio" id="desc" name="order" ${requestScope.order == 'desc' ? "checked" : '' } value="desc"><label for="desc"><fmt:message key="label.sort.desc"/></label><br>

            <input type="hidden" id="searchName1" name="searchName" value="${requestScope.searchName}">
            <input type="submit" value="<fmt:message key="label.button.sort"/>">
        </form><br>
    </div><br>
    <div class="search">
        <form method="post" action="" style="margin-left: 10px">
            <br/><label><fmt:message key="title.createEvent"/></label>
            <div class="createUser" >
                <input type="hidden" name="action" value="createEventMenu">
                <input type="submit" value="<fmt:message key="label.button.create"/>" name="Ok"><br>
            </div>
        </form><br>
    </div>
</div>

<div class="columnMiddle">
    <table>
        <caption style="font-size:160%;"> <fmt:message key="title.allEvents"/> </caption>
        <tr>
            <th><fmt:message key="label.event.name"/></th>
            <th><fmt:message key="label.event.date"/></th>
            <th><fmt:message key="label.event.subs"/></th>
            <th><fmt:message key="label.event.reports"/></th>
        </tr>
        <c:forEach var="entity" items="${requestScope.dao}">
            <tr>
                <ul>
                    <td>
                        <c:choose>
                            <c:when test="${requestScope.started.isActive(entity.id) == true}">
                                <label style="color: orange"><c:out value="${entity.name}"/></label>
                            </c:when>
                            <c:when test="${requestScope.started.isActive(entity.id) == false}">
                                <c:out value="${entity.name}"/>
                            </c:when>
                        </c:choose>
                    </td>
                    <td><c:out value="${entity.dateBegin}"/>
                        <c:out value="${entity.timeBegin}"/></td>
                    <td><c:out value="${entity.subCount}"/></td>
                    <td><c:out value="${entity.reportsCount}"/></td>

                    <td>
                        <form method="post" action="" id="update">
                            <input type="hidden" name="action" value="infoEvent">
                            <input type="hidden" name="id" value="${entity.id}"/>
                            <input type="submit" value="<fmt:message key="label.button.info"/>"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="" id="dell">
                            <input type="hidden" name="action" value="deleteEvent">
                            <input type="hidden"  name="id" value="${entity.id}"/>

                            <input type="hidden" name="pageNum" value="${requestScope.pageNum}"/>
                            <input type="hidden" name="contentCount" value="${requestScope.contentCount}"/>
                            <input type="hidden" name="searchName" value="${requestScope.searchName}">
                            <input type="hidden" name="sort" value="${requestScope.sort}">
                            <input type="hidden" name="order" value="${requestScope.order}">
                            <input type="submit" value="<fmt:message key="label.button.delete"/>"/>
                        </form>
                    </td>
                </ul>
            </tr>
        </c:forEach>
    </table>

    <%--        /* pagination */--%>
    <div style="margin-left: 45%">

        <form method="post" action="">
            <input type="hidden" name="action" value="moderatorMenu" >
            <label><fmt:message key="label.page.current"/>: ${requestScope.pageNum}</label><br>
            <input type="hidden" name="searchName" value="${requestScope.searchName}">
            <input type="hidden" name="sort" value="${requestScope.sort}">
            <input type="hidden" name="order" value="${requestScope.order}">

            <c:choose>
                <c:when test="${requestScope.pageNum-1 > 0}">
                    <input type="submit" style="margin-right: 2px; width: 35px" name="pageNum" value="${requestScope.pageNum-1}">
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${requestScope.pageNum+1 <= requestScope.countPages}">
                    <input type="submit" style="margin-left: 2px; width: 35px" name="pageNum" value="${requestScope.pageNum+1}">
                </c:when>
            </c:choose>

            <select id="role" name="pageNum" style="width: 50px;margin-left: 20px; width: 45px" onchange="submit()">
                <c:forEach begin="1" end="${requestScope.countPages}" step="1" var="index">
                    <option value="${index}" ${requestScope.pageNum == index ? 'selected' : ''}>${index}</option>
                </c:forEach>
            </select>

            <br><br>
            <label><fmt:message key="label.page.content.count"/>:</label>
            <select id="role" name="contentCount" style="width: 50px" onchange="submit()">
                <option value="2" ${requestScope.contentCount == '2' ? 'selected' : ''}>2</option>
                <option value="10" ${requestScope.contentCount == '10' ? 'selected' : ''}>10</option>
            </select><br><br>
        </form>
    </div>
</div>
</body>
</html>