<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<fmt:setBundle basename="resources"/>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Conferences</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <head>
        <title>Conferences</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            * {
                box-sizing: border-box;
            }

            .header button {
                width: 100px;
                height: 30px;
            }

            .header select {
                width: 100px;
            }

            .header label {
                margin-right: 35.3%;
            }

            ul.nav {
                margin-left: 0px;
                padding-left: 0px;
                list-style: none;
            }

            .nav li {
                display: inline;
            }

            ul.nav input {
                font-size: 20px;
                display: inline-block;
                width: 120px;
                height: 35px;
                padding: 10px;
                text-align: center;
            }


            /* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
            @media (max-width: 600px) {
                .column.side, .column.middle {
                    width: 100%;
                }
            }
        </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="moderatorMenu"><fmt:message key="label.button.toMain"/></a></li>
                <li><a href="suggestReports"><fmt:message key="label.button.allSuggestReports"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form action="" method="post">
                    <li>
                        <select class="btn btn-default dropdown-toggle" id="language" data-toggle="dropdown"
                                name="language" onchange="submit()" style="margin-top: 7px;margin-right: 25px">
                            <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>

                            <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
                        </select>
                    </li>
                    <li><a href="logout" style="margin-right: 25px"><span
                            class="glyphicon glyphicon-log-out"></span><fmt:message key="label.button.logout"/></a></li>
                </form>
            </ul>
        </div>
    </div>
</nav>


</body>
</html>


