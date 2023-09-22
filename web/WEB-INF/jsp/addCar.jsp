<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 12.07.2023
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Добавить авто</title>
</head>
<body>
<%@include file="header.jsp"%>
<form action = "/addCar" method="post">
    <label for="model">Модель:
        <input type="text" name="model" id="model" required>
    </label><br>
    <label for="number">Номер:
        <input type="text" name="number" id="number" required>
    </label><br>
    <select name="status" id="status">:
        <option value="Доступна">Доступна</option>
        <option value="Занята">Занята</option>
    </select><br>
    <button type="add">Добавить</button>
    <c:if test="${not empty requestScope.errors}">
        <div>
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
