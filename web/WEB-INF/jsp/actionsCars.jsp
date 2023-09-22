<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 23.07.2023
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Выберите действие</title>
  </head>
  <body>
  <a href="${pageContext.request.contextPath}/cars">${"Все авто"}</a> <br>
  <c:if test="${sessionScope.user.toString().contains('роль - ADMIN')}">
    <a href="${pageContext.request.contextPath}/addCar">${"Добавить авто"}</a> <br>
    <a href="${pageContext.request.contextPath}/deleteCar">${"Удалить авто"}</a> <br>
    <a href="${pageContext.request.contextPath}/setStatusCars">${"Обновить статус авто"}</a> <br>
  </c:if>
  <a href="${pageContext.request.contextPath}/findCarBySerialNumber">${"Найти авто"}</a> <br>
  <a href="${pageContext.request.contextPath}/findFreeCar">${"Свободные авто"}</a> <br>
  </body>
</html>
