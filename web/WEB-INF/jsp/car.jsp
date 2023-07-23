<%@ page import="dto.CarDto" %>
<%@ page import="java.util.List" %>
<%@ page import="service.CarService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${requestScope.title}</title>
</head>
<body>
<%@include file="header.jsp"%>
<ul>
    <c:forEach var ="car"  items = "${requestScope.cars}">
        <li>${car.getDescription()} </li>
    </c:forEach>
</ul>
</body>
</html>
