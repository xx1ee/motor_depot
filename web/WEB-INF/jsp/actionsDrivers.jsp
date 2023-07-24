<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 23.07.2023
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выберите действие</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/drivers">${"Все водители"}</a> <br>
<a href="${pageContext.request.contextPath}/addDriver">${"Добавить водителя"}</a> <br>
<a href="${pageContext.request.contextPath}/deleteDriver">${"Удалить водителя"}</a> <br>
<a href="${pageContext.request.contextPath}/findDriverBySerialNumber">${"Найти водителя"}</a> <br>
<a href="${pageContext.request.contextPath}/findFreeDriver">${"Свободные водители"}</a> <br>
<a href="${pageContext.request.contextPath}/setStatusDrivers">${"Обновить статус водителей"}</a> <br>
</body>
</html>
