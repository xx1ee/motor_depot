<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 24.07.2023
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поиск пользователя по email и паролю</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/findUser" method="post">
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
