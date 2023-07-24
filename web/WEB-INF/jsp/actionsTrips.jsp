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
  <a href="${pageContext.request.contextPath}/trips">${"Все рейсы"}</a> <br>
  <a href="${pageContext.request.contextPath}/addTrip">${"Добавить рейс"}</a> <br>
  <a href="${pageContext.request.contextPath}/deleteTrip">${"Удалить рейс"}</a> <br>
  <a href="${pageContext.request.contextPath}/findTripsBySerialNumber">${"Найти рейс"}</a> <br>
  <a href="${pageContext.request.contextPath}/completeTrip">${"Завершить выполненные рейсы"}</a> <br>
  <a href="${pageContext.request.contextPath}/allTripsPerPeriod">${"Рейсы за определенный период"}</a> <br>
  <a href="${pageContext.request.contextPath}/allTripsDepartOf">${"Рейсы отправленные из определенного города"}</a> <br>
  </body>
</html>
