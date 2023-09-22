<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 12.07.2023
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить водителя</title>
</head>
<body>
<%@include file="header.jsp"%>
<form action = "/addDriver" method="post">
    <label for="name">ФИО:
        <input type="text" name="name" id="name" required>
    </label><br>
    <label for="birth">Дата рождения:
        <input type="date" name="birth" id="birth" required>
    </label><br>
    <label for="serialNumber">Серийный номер:
        <input type="text" name="serialNumber" id="serialNumber" required>
    </label><br>
    <select name="status" id="status">:
        <option value="Доступен">Доступен</option>
        <option value="Занят">Занят</option>
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
