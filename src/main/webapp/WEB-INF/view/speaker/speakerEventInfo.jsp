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
    .sidenav {
      height: 100%;
      width: 250px;
      padding: 9px;
      position: fixed;
      z-index: 1;
      top: 0;
      left: 0;
      background-color: darkgrey;
      overflow-x: hidden;

      color: black;
      text-decoration: none;
      display: compact;
      margin-top: 85px;
    }
    .createUser input{
      width: 150px;
    }
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
<jsp:include page="/WEB-INF/view/speaker/speakerHead.jsp"/>

<div class="sidenav">


  <br/><h2><fmt:message key="title.suggestReport"/></h2>
  <form method="post" action="">
    <div class="createUser">
      <input type="hidden" name="action" value="suggestReport">
      <input type="hidden" name="eventId" value="${requestScope.event.id}"/>
      <input type="submit" value="<fmt:message key="act.suggestReport"/>" name="Ok"><br>
    </div>
  </form>
</div>


<table>
  <caption style="font-size:160%;"> <fmt:message key="title.infoEvent"/> </caption>
  <tr>
    <%--<th><fmt:message key="label.id"/></th>
    <th><fmt:message key="label.login"/></th>
    <th><fmt:message key="label.password"/></th>--%>
    <th>report id</th>
    <th>event name</th>
    <th>date</th>
  </tr>

  <tr>
    <ul>
      <td><c:out value="${requestScope.event.id}"/></td>
      <td><c:out value="${requestScope.event.name}"/></td>
      <td><c:out value="${requestScope.event.dateBegin}"/>
        <c:out value="${requestScope.event.timeBegin}"/></td>
    </ul>
  </tr>

</table>

<table>
  <caption style="font-size:160%;"> <fmt:message key="title.allUsers"/> </caption>
  <tr>
    <%--<th><fmt:message key="label.id"/></th>
    <th><fmt:message key="label.login"/></th>
    <th><fmt:message key="label.password"/></th>--%>
    <th>report id</th>
    <th>report name</th>
    <th>speaker</th>
  </tr>
  <c:forEach var="report" items="${requestScope.event.reports}">
    <tr>
      <ul>
        <td><c:out value="${report.id}"/></td>
        <td><c:out value="${report.name}"/></td>
        <td><c:out value="${report.speaker.login}"/></td>
        <td>
          <form method="post" action="" id="update">
            <input type="hidden" name="action" value="speakerUpdateReportPage">
            <input type="hidden" name="eventId" value="${requestScope.event.id}"/>
            <input type="hidden" name="reportId" value="${report.id}"/>
            <c:choose>
              <c:when test="${report.speaker.login == sessionScope.login}">
                <input type="submit" value="update"/>
              </c:when>
            </c:choose>
          </form>
        </td>
        <td>
          <form method="post" action="" id="dell">
            <input type="hidden" name="action" value="deleteReport">
            <input type="hidden"  name="id" value="${requestScope.event.id}"/>
            <input type="hidden"  name="reportId" value="${report.id}"/>
            <c:choose>
              <c:when test="${report.speaker.login == sessionScope.login}">
                <input type="submit" value="unsubscribe"/>
              </c:when>
            </c:choose>
          </form>
        </td>
      </ul>
    </tr>
  </c:forEach>
</table>


</body>
</html>
