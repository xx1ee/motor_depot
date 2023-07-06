<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 04.07.2023
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рейсы</title>
</head>
<body>
<ul>
    <c:forEach var ="trip"  items = "${requestScope.trips}">
        <li>${trip.getDescription()}
            <span>, </span>
            <a href="${pageContext.request.contextPath}/cars?carId=${trip.carDto.id}">${"Автомобиль"}</a>
            <span>, </span>
            <a href="${pageContext.request.contextPath}/drivers?driverId=${trip.driverDto.id}">${"Водитель"}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
