<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<fmt:setBundle basename="resources"/>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<ul class="nav navbar-nav navbar-right">
    <form action="" method="post" style="margin-right: 25px">
        <li>
            <select class="btn btn-default dropdown-toggle" id="language" data-toggle="dropdown"
                    name="language" onchange="submit()" style="margin-top: 7px;margin-right: 25px">
                <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
                <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
        </li>
    </form>
</ul>

<div class="container-fluid">
    <div class="row content">
        <br><br>
        <div class="col-sm-5"></div>

        <div class="col-sm-2 text-center">
            <div class="well">
                <form method="post" action="">
                    <h1><fmt:message key="title.login"/></h1><br>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" required name="login" class="form-control text-center"
                               placeholder="<fmt:message key="label.user.login"/>"
                               style="width: 200px">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" name="password" class="form-control text-center"
                               placeholder="<fmt:message key="label.user.password"/>"
                               style="width: 200px">
                    </div>
                    <br>
                    <input type="hidden" name="registration" value="true">
                    <input type="submit" value="<fmt:message key="label.button.enter"/>" class="btn btn-default"
                           style="width: 200px">
                    <c:choose>
                        <c:when test="${sessionScope.errorMessage != ''}">
                            <br><br>
                            <label style="color: red;font-size: 15px">${requestScope.errorMessage}</label>
                        </c:when>
                    </c:choose>
                </form>
            </div>
            <%--
                    <label><input type="time" name="timeToSet"
                    class="form-control text-center" style="width: 200px"
                                  placeholder="<fmt:message key="label.event.time"/>"
                                  required> </label> <label style="color: orange; font-size: 25px"> *</label><br>
         --%>
            <br><br>
            <div class="well">
                <form method="post" action="registration">
                    <input type="hidden" name="action" value="registration">
                    <input type="submit" class="btn btn-default" value="<fmt:message key="label.button.registration"/>"
                           style="width: 200px"><br>
                </form>
            </div>

        </div>
    </div>
</div>

</body>
</html>