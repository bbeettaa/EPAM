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
    <title>Title</title>
    <style>
        .block{
            margin-left: auto;
            margin-right: auto;
            width: 500px;
        }
    </style>
</head>
<body>

<!-- choose language -->
<form action="" method="post"  style="margin-left: 82%">
    <label style="font-size: 20px; margin-right: 0"><fmt:message key="label.selectLanguage"/>:</label>
    <input type="hidden" name="action" value="updateUserPage">
    <input type="hidden" name="roleToSet" value="${requestScope.user.role}"/>
    <input type="number" hidden name="id" value="${requestScope.user.id}"/>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
    </select>
</form>
<div class="block">
<form method="post" action="">
    <input type="hidden" name="action" value="updateUser">

            <label><fmt:message key="label.login"/>: <input type="text" name="loginToSet" value="${requestScope.user.login}" minlength="5" maxlength="30" required/></label>
            <c:choose>
                <c:when test="${requestScope.message == 'login exists'}">
                    <label style="color: red"><fmt:message key="lable.login.exists"/></label>
                </c:when>
            </c:choose><br>
            <label><fmt:message key="label.password"/>: <input style="position: center" type="text" name="passwordToSet" value="${requestScope.user.password}" minlength="5" maxlength="30" required/></label><br>
            <label><fmt:message key="label.name"/>: <input type="text" name="nameToSet" value="${requestScope.user.name}" maxlength="30" /></label><br>
            <label><fmt:message key="label.surname"/>: <input type="text" name="surnameToSet" value="${requestScope.user.surname}" maxlength="30" /></label><br>
            <label><fmt:message key="label.email"/>: <input type="email" name="emailToSet" value="${requestScope.user.email}" maxlength="30" /></label>
            <c:choose>
                <c:when test="${requestScope.message == 'email exists'}">
                    <label style="color: red"><fmt:message key="lable.email.exist"/></label>
                </c:when>
            </c:choose><br>

            <label for="role">
                <fmt:message key="label.role"/></label>: <select id="role" name="roleToSet">
            <option value="user"><fmt:message key="label.user"/></option>
            <option value="speaker"><fmt:message key="label.speaker"/></option>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <option value="moderator"><fmt:message key="label.moderator"/></option>
                </c:when>
            </c:choose>
        </select><br><br>

            <input type="hidden" name="roleToSet" value="${requestScope.user.role}"/>
            <input type="number" hidden name="id" value="${requestScope.user.id}"/>
            <input type="submit" value="Update" name="<fmt:message key="label.registration"/>"><br>
        </form>
    </div>
</body>
</html>