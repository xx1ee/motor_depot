<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 24.07.2023
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Найти все рейсы из конкретного города</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/allTripsDepartOf" method="post">
    <label for="depart">Город отправления:
        <input type="text" name="depart" id="depart" required>
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
