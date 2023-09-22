<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 14.07.2023
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить рейс</title>
</head>
<body>
<%@include file="header.jsp"%>
<form action = "/addTrip" method="post">
    <label for="dr">Id водителя:
        <input type="text" name="dr" id="dr" required>
    </label><br>
    <label for="car">Id авто:
        <input type="text" name="car" id="car" required>
    </label><br>
    <label for="stDepart">Станция отправления:
        <input type="text" name="stDepart" id="stDepart" required>
    </label><br>
    <label for="stArr">Станция прибытия:
        <input type="text" name="stArr" id="stArr" required>
    </label><br>
    <label for="timeDepart">Время отправления:
        <input type="datetime-local" name="timeDepart" id="timeDepart" required>
    </label><br>
    <label for="timeArr">Время прибытия:
        <input type="datetime-local" name="timeArr" id="timeArr" required>
    </label><br>
    <select name="status" id="status">:
        <option value="Зарегестрирован">Зарегестрирован</option>
        <option value="Исполняется">Исполняется</option>
        <option value="Завершен">Завершен</option>
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
