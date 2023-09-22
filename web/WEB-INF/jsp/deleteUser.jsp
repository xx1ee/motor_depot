<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 24.07.2023
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить пользователя по email и password</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/deleteUser" method="post">
    <label for="email">Email:
        <input type="text" name="email" id="email" required>
    </label><br>
    <label for="password">Password:
        <input type="text" name="password" id="password" required>
    </label>
    <button type="submit">Send</button>
</form>
</body>
</html>
