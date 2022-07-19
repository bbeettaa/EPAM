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
    <meta charset="utf-8">
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>


        @media screen and (max-width: 767px) {
            .row.content {
                height: auto;
            }
        }

    </style>
</head>
<body>
<fmt:message key="title.userPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/user/userHead.jsp"/>


<div class="container-fluid text-center">
    <div class="row content">

        <div class="col-sm-2 sidenav">
        </div>

        <div class="col-sm-8 text-left">
            <table class="table table-hover">
                <caption style="font-size:160%;"><fmt:message key="title.allEvents"/></caption>
                <tr>
                    <th><fmt:message key="label.event.name"/></th>
                    <th><fmt:message key="label.event.date"/></th>
                    <th><fmt:message key="label.event.subs"/></th>
                    <th><fmt:message key="label.event.reports"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="entity" items="${requestScope.dao}">
                    <c:choose>
                        <c:when test="${requestScope.started.isActive(entity.id) == true}">
                            <tr class="warning">
                        </c:when>
                        <c:when test="${requestScope.started.isActive(entity.id) == false}">
                            <tr>
                        </c:when>
                    </c:choose>
                    <ul>
                        <td> <c:out value="${entity.name}"/> </td>
                        <td><c:out value="${entity.dateBegin}"/>
                            <c:out value="${entity.timeBegin}"/></td>
                        <td><c:out value="${entity.subCount}"/></td>
                        <td><c:out value="${entity.reportsCount}"/></td>
                        <td>
                            <form method="post" action="userEventInfo" id="info">
                                <input type="hidden" name="action" value="userEventInfo">
                                <input type="hidden" name="id" value="${entity.id}"/>
                                <input type="submit" value="<fmt:message key="label.button.info"/>" class="btn btn-info"
                                       style="width: 100px">
                            </form>
                        </td>
                        <td>
                            <form method="post" action="" id="dell">
                                <input type="hidden" name="action" value="unsubscribe">
                                <input type="hidden" name="userId" value="${requestScope.userId}"/>
                                <input type="hidden" name="id" value="${entity.id}"/>
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="label.button.unsubscribe"/>">
                            </form>
                        </td>
                        <c:choose>
                            <c:when test="${requestScope.started.isActive(entity.id) == true}">
                                <td>
                                    <form method="post" action="" id="connect">
                                        <input type="hidden" name="action" value="join">
                                        <input type="hidden" name="id" value="${entity.id}"/>
                                        <input type="submit" value="<fmt:message key="label.button.join"/>"
                                               class="btn btn-info" style="width: 100px">
                                    </form>
                                </td>
                            </c:when>
                        </c:choose>
                    </ul>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </div>

    </div>
</div>

</body>
</html>