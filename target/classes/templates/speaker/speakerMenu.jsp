<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%@ taglib uri="/WEB-INF/mytags.tld" prefix="j" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        .row.content {
            height: 793px;
            margin-top: -20.5px;
        }

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }
    </style>
</head>
<body>
<fmt:message key="title.adminPanel" var="titleName"/>
<c:set var="title" value="${titleName}" scope="application"/>
<jsp:include page="/WEB-INF/view/speaker/speakerHead.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav" style="height: 100%">
            <div class="well">
                <form method="post" action="speakerCabinet" style="margin-left: 10px">
                    <input type="hidden" name="action" value="speakerCabinet">
                    <div>
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="<fmt:message key="label.button.searchByName"/>"
                                   value="${requestScope.searchName}" name="searchName">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit" value="${requestScope.searchName}"><i
                                        class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>

                        <input type="hidden" id="bySubscribers1" name="sort" value="${requestScope.sort}">
                        <input type="hidden" id="asc1" name="order" value="${requestScope.order}">
                    </div>
                </form>
            </div>

            <div class="well">
                <form method="post" action="speakerCabinet" style="margin-left: 10px"><br>
                    <input type="hidden" name="action" value="speakerCabinet">

                    <div class="well">
                        <div class="radio">
                            <label><input type="radio" id="byName" name="sort" ${requestScope.sort == 'byName' ? "checked" : '' }
                                          value="byName"><fmt:message key="label.sort.byName"/></label>
                        </div>

                        <div class="radio">
                            <label><input type="radio" id="byDate" name="sort" ${requestScope.sort == 'byDate' ? "checked" : '' }
                                          value="byDate"><fmt:message key="label.sort.byDate"/></label>
                        </div>

                        <div class="radio">
                            <label><input type="radio" id="byReports" name="sort" ${requestScope.sort == 'byReports' ? "checked" : '' }
                                          value="byReports"><fmt:message key="label.sort.byReports"/></label>
                        </div>

                        <div class="radio">
                            <label><input type="radio" id="bySubscribers" name="sort" ${requestScope.sort == 'bySubscribers' ? "checked" : '' }
                                          value="bySubscribers"><fmt:message key="label.sort.bySubs"/></label>
                        </div>
                    </div>

                    <div class="well">
                        <div class="radio">
                            <label><input type="radio" id="asc" name="order" ${requestScope.order == 'asc' ? "checked" : '' }
                                          value="asc"><fmt:message key="label.sort.asc"/></label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="desc" name="order" ${requestScope.order == 'desc' ? "checked" : '' }
                                          value="desc"><fmt:message key="label.sort.desc"/></label>
                        </div>

                        <input type="hidden" id="searchName1" name="searchName" value="${requestScope.searchName}">
                    </div>
                    <button class="btn btn-default" type="submit" style="width: 150px"><i
                            class="glyphicon glyphicon-search"></i></button>
                </form>
            </div>

            <div class="well">
                <form method="post" action="speakerCabinet" style="margin-left: 10px">
                    <div class="createUser">
                        <input type="hidden" name="action" value="suggestReport">
                        <input type="submit" class="btn btn-default" value="<fmt:message key="title.suggestReport"/>" name="Ok"><br>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-sm-8 text-left">
            <table class="table table-hover">
                <caption style="font-size:160%;"><fmt:message key="title.allEvents"/></caption>
                <tr>
                    <th><fmt:message key="label.event.name"/></th>
                    <th><fmt:message key="label.event.date"/></th>
                    <th><fmt:message key="label.event.subs"/></th>
                    <th><fmt:message key="label.event.reports"/></th>
                    <th></th>
                </tr>
                <c:forEach var="entity" items="${requestScope.dao}">
                    <c:choose>
                        <c:when test="${requestScope.started.isActive(entity.id) == true}">
                            <tr class="warning">
                        </c:when>
                        <c:when test="${requestScope.started.isActive(entity.id) == false}">
                            <tr>
                        </c:when>
                    </c:choose>
                    <ul>
                        <j:eventRecord event="${entity}"/>
                        <td>
                            <form method="post" action="speakerEventInfo" id="info">
                                <input type="hidden" name="action" value="speakerEventInfo">
                                <input type="hidden"  name="id" value="${entity.id}"/>
                                <input type="submit" value="<fmt:message key="label.button.info"/>" class="btn btn-info"
                                       style="width: 100px">
                            </form>
                        </td>

                    </ul>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </div>

        <div class="col-sm-8 text-left">
            <form method="post" action="">
                <input type="hidden" name="action" value="speakerCabinet">
                <input type="hidden" name="searchName" value="${requestScope.searchName}">
                <input type="hidden" name="sort" value="${requestScope.sort}">
                <input type="hidden" name="order" value="${requestScope.order}">

                <div>
                    <label><fmt:message key="label.page.content.count"/>:</label>
                    <select id="role" class="btn btn-default dropdown-toggle" name="contentCount" style="width: 55px"
                            onchange="submit()">
                        <option value="2" ${requestScope.contentCount == '2' ? 'selected' : ''}>2</option>
                        <option value="10" ${requestScope.contentCount == '10' ? 'selected' : ''}>10</option>
                    </select>

                    <ul class="pagination" style="margin-left: 800px">
                        <c:forEach begin="${requestScope.previousPage}" end="${requestScope.pageNum-1}" step="1"
                                   var="index">
                            <c:choose>
                                <c:when test="${requestScope.pageNum != index && index <= requestScope.countPages}">
                                    <li>
                                        <button type="submit" class="btn btn-link"
                                                style="margin-right: 2px; width: 35px" name="pageNum"
                                                value="${index}">${index}</button>
                                    </li>
                                </c:when>

                            </c:choose>
                        </c:forEach>
                    </ul>
                    <ul class="pagination">
                        <c:forEach begin="${requestScope.pageNum}" end="${requestScope.pageNum+2}" step="1" var="index">
                            <c:choose>
                                <c:when test="${requestScope.pageNum != index && index <= requestScope.countPages}">
                                    <li>
                                        <button type="submit" class="btn btn-link"
                                                style="margin-right: 2px; width: 35px" name="pageNum"
                                                value="${index}">${index}</button>
                                    </li>
                                </c:when>
                                <c:when test="${requestScope.pageNum == index }">
                                    <li class="active"><a type="submit" class="btn btn-link"
                                                          style="margin-right: 2px; width: 35px"
                                                          value="${index}">${index}</a></li>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <select name="pageNum" style="width: 55px;margin-left: 20px" onchange="submit()"
                                class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <c:forEach begin="1" end="${requestScope.countPages}" step="1" var="index">
                                <option value="${index}" ${requestScope.pageNum == index ? 'selected' : ''}>${index}</option>
                            </c:forEach>
                        </select>
                    </ul>
                </div>

            </form>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <br>
</footer>



</body>
</html>