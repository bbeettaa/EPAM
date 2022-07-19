<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<fmt:setBundle basename="resources"/>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!doctype html>
<html>
<c:set var="title" value="Error" scope="page"/>
<body>
<div class="container text-center">

    <form method="get" action="" >

      <c:choose>
        <c:when test="${requestScope.error == '404'}">
          <h1>404  Page not found</h1>
        </c:when>
        <c:when test="${requestScope.error != '404'}">
          <h1><fmt:message key="error"/></h1>
        </c:when>
      </c:choose>

        <a href="logout"><fmt:message key="label.button.toMain"/></a>
    </form>

    <div class="col-md-5 col-middle text-center">
      <img src="https://a0.muscache.com/airbnb/static/error_pages/404-Airbnb_final-d652ff855b1335dd3eedc3baa8dc8b69.gif" width="313" height="428" class="hide-sm" alt="Девочка уронила свое мороженое.">
    </div>
  </div>


</body>
</html>