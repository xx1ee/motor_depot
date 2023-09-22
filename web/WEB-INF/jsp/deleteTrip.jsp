<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 27.07.2023
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить рейс по id</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/deleteTrip" method="post">
    <label for="id">Id:
        <input type="text" name="id" id="id" required>
    </label>
    <button type="submit">Send</button>
</form>
</body>
</html>
