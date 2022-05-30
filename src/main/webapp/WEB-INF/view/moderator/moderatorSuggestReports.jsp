<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
  <title>Event info</title>
  <style>

    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
      padding: 1px;
      text-align: center;
      min-width: 35%;

      margin-left: 34%;
    }
  </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/moderator/moderHead.jsp"/>

<table>
  <caption style="font-size:160%;"> <fmt:message key="title.allUsers"/> </caption>
  <tr>
    <%--<th><fmt:message key="label.id"/></th>
    <th><fmt:message key="label.login"/></th>
    <th><fmt:message key="label.password"/></th>--%>
    <th>event id</th>
    <th>report name</th>
    <th>speaker</th>
  </tr>
  <c:forEach var="report" items="${requestScope.dao}">
    <tr>
      <ul>
        <td><c:out value="${report.eventId}"/></td>
        <td><c:out value="${report.name}"/></td>
        <td><c:out value="${report.speaker.login}"/></td>
        <td> <form method="post" action="" id="acceptSuggestion">
            <input type="hidden" name="action" value="acceptSuggestion">
            <input type="hidden"  name="id" value="${report.eventId}"/>
            <input type="submit" value="acceptSuggestion"/>
          </form> </td>
        <td> <form method="post" action="" id="declineSuggestion">
            <input type="hidden" name="action" value="declineSuggestion">
            <input type="hidden"  name="id" value="${report.eventId}"/>
            <input type="submit" value="declineSuggestion"/>
          </form> </td>

      </ul>
    </tr>
  </c:forEach>
</table>


</body>
</html>
