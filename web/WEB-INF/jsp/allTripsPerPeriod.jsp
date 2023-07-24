<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 20.07.2023
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Все рейсы за конкретный период</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/allTripsPerPeriod" method="post" >
  <label for="startDate">Введите начальную дату периода:
    <input type="datetime-local" name="startDate" id="startDate" required>
  </label><br>
  <label for="endDate">Введите начальную дату периода:
    <input type="datetime-local" name="endDate" id="endDate" required>
  </label><br>
  <button type="submit">Send</button>
</form>
</body>
</html>
