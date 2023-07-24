<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 17.07.2023
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать</title>
</head>
<body>
<%@include file="header.jsp"%>
<a href="${pageContext.request.contextPath}/actionsCar">${"Автомобили"}</a> <br>
<a href="${pageContext.request.contextPath}/actionsDrivers">${"Водители"}</a> <br>
<a href="${pageContext.request.contextPath}/actionsTrips">${"Рейсы"}</a> <br>
<a href="${pageContext.request.contextPath}/actionsUsers">${"Пользователи"}</a> <br>
</body>
</html>
