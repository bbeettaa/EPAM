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
            top: 94px;
            left: 0;
            background-color: darkgrey;
            overflow-x: hidden;

            color: black;
            text-decoration: none;
            display: compact;
        }

        .createUser input, select{
            width: 150px;
        }

        .columnMiddle{
            margin-left: 280px;
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
<jsp:include page="/WEB-INF/view/head.jsp"/>

<div class="sidenav">
    <form method="post" action="">
        <input type="hidden" name="action" value="findUser">
        <div>
            <label for="findLogin"><fmt:message key="label.searchByLogin"/>:</label>
            <input type="search" id="findLogin" name="findLogin" value="${findLogin}">
            <input type="submit" value="<fmt:message key="act.search"/>">
        </div>
    </form>

    <br/><h2><fmt:message key="title.createNewUser"/></h2>
    <form method="post" action="">
        <div class="createUser" >
            <input type="hidden" name="action" value="registration">
            <input type="submit" value="<fmt:message key="act.createUser"/>" name="Ok"><br>
        </div>
    </form>
</div>

<div class="columnMiddle">

    <table>
        <caption style="font-size:160%;"> <fmt:message key="title.allUsers"/> </caption>
        <tr>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.login"/></th>
            <th><fmt:message key="label.password"/></th>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.surname"/></th>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="label.role"/></th>
        </tr>
        <c:forEach var="user" items="${requestScope.dao}">
            <tr>
                <ul>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.surname}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <form method="post" action="" id="dell">
                            <input type="hidden" name="action" value="deleteUser">
                            <input type="number" hidden name="id" value="${user.id}"/>
                            <c:if test="${user.role != ADMIN}"/>

                            <c:choose>
                                <c:when test="${user.role != 'ADMIN'}">
                                    <input type="submit" name="delete" value="<fmt:message key="act.delete"/>"/>
                                </c:when>
                            </c:choose>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="" id="update">
                            <input type="hidden" name="action" value="updateUserPage">
                            <input type="hidden"  name="id" value="${user.id}"/>
                            <input type="submit" value="<fmt:message key="act.update"/>"/>
                        </form>
                    </td>
                </ul>
            </tr>
        </c:forEach>
    </table>

</div>>








</body>
</html>