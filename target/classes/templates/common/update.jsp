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
    <title>Update</title>
    <style>
        .block {
            margin-left: auto;
            margin-right: auto;
            width: 500px;
        }
    </style>
</head>
<body>

<!-- choose language -->
<form action="" method="post" style="text-align: right;margin-right: 50px">
    <label style="font-size: 20px; margin-right: 0"><fmt:message key="label.button.selectLanguage"/>:</label>
    <input type="hidden" name="action" value="updateUserPage">
    <input type="hidden" name="roleToSet" value="${requestScope.user.role}"/>
    <input type="number" hidden name="id" value="${requestScope.user.id}"/>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <%--        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>--%>
        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
    </select>
</form>
<form action="" method="post" style="margin-left: 87.01%">
    <input type="hidden" name="action" value="login"/>
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <input type="submit" value="<fmt:message key="label.button.toMain"/>"
                   style="margin-left: 100px;width: 100px"/>
        </c:when>
        <c:when test="${sessionScope.role != 'ADMIN'}">
            <input type="submit" value="<fmt:message key="label.user.login"/>" style="margin-left: 100px;width: 100px"/>
        </c:when>
    </c:choose>

</form>
<div class="block">
    <form method="post" action="">
        <input type="hidden" name="action" value="updateUser">

        <label><fmt:message key="label.user.login"/>: <input type="text" name="loginToSet"
                                                             value="${requestScope.user.login}" minlength="5"
                                                             maxlength="30" required/><label style="color: orange">
            *</label></label>
        <c:choose>
            <c:when test="${requestScope.message == 'login exists'}">
                <label style="color: red"><fmt:message key="label.user.login.exists"/></label>
            </c:when>
        </c:choose><br>
        <label><fmt:message key="label.user.password"/>: <input style="position: center" type="text"
                                                                name="passwordToSet"
                                                                value="${requestScope.user.password}" minlength="5"
                                                                maxlength="30" required/><label style="color: orange">
            *</label></label><br>
        <label><fmt:message key="label.user.name"/>: <input type="text" name="nameToSet"
                                                            value="${requestScope.user.name}"
                                                            maxlength="30"/></label><br>
        <label><fmt:message key="label.user.surname"/>: <input type="text" name="surnameToSet"
                                                               value="${requestScope.user.surname}"
                                                               maxlength="30"/></label><br>
        <label><fmt:message key="label.user.email"/>: <input type="email" name="emailToSet"
                                                             value="${requestScope.user.email}" maxlength="30"/></label>
        <c:choose>
            <c:when test="${requestScope.message == 'email exists'}">
                <label style="color: red"><fmt:message key="label.user.email.exists"/></label>
            </c:when>
        </c:choose><br>

        <label for="role">
            <fmt:message key="label.user.role"/></label>: <select id="role" name="roleToSet">
        <option value="user" ${requestScope.user.role=='USER'?'selected':''}><fmt:message
                key="label.role.user"/></option>
        <option value="speaker" ${requestScope.user.role=='SPEAKER'?'selected':''}><fmt:message
                key="label.role.speaker"/></option>
        <c:choose>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <option value="moderator" ${requestScope.user.role=='MODERATOR'?'selected':''}><fmt:message
                        key="label.role.moderator"/></option>
            </c:when>
        </c:choose>
    </select><br><br>

        <input type="hidden" name="roleToSet" value="${requestScope.user.role}"/>
        <input type="number" hidden name="id" value="${requestScope.user.id}"/>
        <input type="submit" value="<fmt:message key="label.button.update"/>" name="Update">
        <c:choose>
            <c:when test="${requestScope.validateError != ''}">
                <label style="color: red">${requestScope.validateError}</label>
            </c:when>
        </c:choose><br>
    </form>
</div>
</body>
</html>