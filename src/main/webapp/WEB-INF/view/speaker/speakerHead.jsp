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
    <style>
        * {
            box-sizing: border-box;
        }

        .header {
            margin-top: -10px;
            margin-left: -15px;
            background-color: gray;
            padding: 10px;
            width: 101%;
            font-size: 30px;
            height: 87px;
          /*  text-align: right;*/
            position: relative;
            justify-content: space-between;
            align-items: center;
        }

        .header button{
            width: 100px;
            height: 30px;
        }

        .header select{
            width: 100px;
        }

        .header label{
            margin-right: 35.3%;
        }

        ul.nav{
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
            padding:10px;
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



<div class="header">
    <label style="margin-left: 42%"><fmt:message key="title.speakerPanel"/></label>
    <!-- logout -->
    <form method="post" action="" style="margin-left: 94.3%; margin-top: -40px">
        <input type="hidden" name="action" value="logout" >
        <button type="submit" ><fmt:message key="label.button.logout"/></button>
    </form>
    <!-- choose language -->
    <form action="" method="post" style="text-align: right">
        <label style="font-size: 20px; margin-right: 0"><fmt:message key="label.button.selectLanguage"/>:</label>
        <select id="language" name="language" onchange="submit()">
            <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
<%--            <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>--%>
            <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
        </select>
    </form>

    <form method="post" action="">
        <input type="hidden" name="action" value="speakerCabinet"/>
        <ul class="nav" style="margin-top: -70px">
            <li><input type="submit" value="<fmt:message key="label.button.toMain"/>"></li>
        </ul>
    </form>
    <form style="margin-left: 150px" method="post" action="">
        <input type="hidden" name="action" value="allSuggestReports"/>
        <ul class="nav" style="margin-top: -67px">
            <li><input style="width: 170px" type="submit" value="<fmt:message key="label.button.allMySuggestReports"/>"></li>
        </ul>
    </form>

</div>



</body>
</html>


