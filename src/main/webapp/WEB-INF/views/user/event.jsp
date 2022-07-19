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
        * {
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
            margin-top: 85px;
        }

        .createUser input, select {
            width: 150px;
        }

        .columnMiddle {
            margin-left: 280px;
        }

        .search {
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
<fmt:message key="title.userPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/user/userHead.jsp"/>

<div style="text-align: center" >
<video width="800" height="460" controls autoplay muted loop id="myVideo">
    <source src="mov_bbb.mp4" type="video/mp4">
    <source src="mov_bbb.webm" type="video/webm">
    <source src="mov_bbb.ogv" type="video/ogv">
    Your browser does not support the video tag.
</video>
</div>

</body>
</html>