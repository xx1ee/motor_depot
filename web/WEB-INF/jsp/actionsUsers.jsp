<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 23.07.2023
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выберите действие</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/users">${"Все пользователи"}</a> <br>
<a href="${pageContext.request.contextPath}/deleteUser">${"Удалить пользователя"}</a> <br>
<a href="${pageContext.request.contextPath}/findUser">${"Все рейсы"}</a> <br>
</body>
</html>
