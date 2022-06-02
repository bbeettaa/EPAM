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
<div class="page-container-responsive">
  <div class="row space-top-8 space-8">
    <div class="col-md-5 col-middle">
      <h1><fmt:message key="error"/></h1>
      <a href=""><fmt:message key="label.button.toMain"/></a>
    </div>
    <div class="col-md-5 col-middle text-center">
      <img src="https://a0.muscache.com/airbnb/static/error_pages/404-Airbnb_final-d652ff855b1335dd3eedc3baa8dc8b69.gif" width="313" height="428" class="hide-sm" alt="Девочка уронила свое мороженое.">
    </div>
  </div>
</div>
</body>
</html>