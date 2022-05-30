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
      text-align: right;
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

    /* Style the footer */
    .footer {
      background-color: #f1f1f1;
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



<div class="header">
  <!-- logout -->
  <form method="post" action="">
    <label>${title}</label>
    <input type="hidden" name="action" value="logout" >
    <button type="submit" ><fmt:message key="act.logout"/></button>
  </form>
  <!-- choose language -->
  <form action="" method="post" >
    <label style="font-size: 20px; margin-right: 0"><fmt:message key="label.selectLanguage"/>:</label>
    <select id="language" name="language" onchange="submit()">
      <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
      <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
      <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
      <input type="hidden" name="login" value="${login}">
      <input type="hidden" name="password" value="${password}">
    </select>
  </form>
</div>



</body>
</html>


