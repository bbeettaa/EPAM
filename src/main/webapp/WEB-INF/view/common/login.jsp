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
    <title>Login</title>

</head>
<body>

<!-- choose language -->
<form action="" method="post" style="text-align: right; margin-right: 20px">
    <label style="font-size: 20px; margin-right: 0"><fmt:message key="label.button.selectLanguage"/>:</label>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
<%--        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>--%>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
    </select>
</form>

<form method="post" style="text-align: center">
    <h1><fmt:message key="title.login"/></h1><br>
    <input type="text" required placeholder="login" name="login"><br>
    <input type="password" required placeholder="password" name="password"><br><br>
    <input class="button" type="submit" value="<fmt:message key="label.button.enter"/>">
</form>

<form method="post" action="" style="text-align: center"><br>
    <input type="hidden" name="action" value="registration">
    <input type="submit" value="<fmt:message key="label.button.registration"/>"><br>
</form>

</body>
</html>