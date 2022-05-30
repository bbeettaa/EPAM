<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<c:set var="title" value="Страница входа" scope="page"/>
<jsp:include page="/WEB-INF/view/head.jsp"></jsp:include>
<body>
<div class="page-container-responsive">
  <div class="row space-top-8 space-8">
    <div class="col-md-5 col-middle">
      <h1 class="text-jumbo text-ginormous hide-sm">Ой!</h1>
      <h2>Похоже, произошла какая-то ошибка.</h2>
      <h6>Вернитесь на <a href="">Главную</a></h6>
    </div>
    <div class="col-md-5 col-middle text-center">
      <img src="https://a0.muscache.com/airbnb/static/error_pages/404-Airbnb_final-d652ff855b1335dd3eedc3baa8dc8b69.gif" width="313" height="428" class="hide-sm" alt="Девочка уронила свое мороженое.">
    </div>
  </div>
</div>
</body>
</html>