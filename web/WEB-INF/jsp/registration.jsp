<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 06.07.2023
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
    <label for="email">Email:
        <input type="text" name="email" id="email" required>
    </label><br>
    <label for="password">Password:
        <input type="text" name="password" id="password" required>
    </label><br>
    <select name="role" id="role">:
        <option value="USER">USER</option>
        <option value="ADMIN">ADMIN</option>
    </select><br>
    <label for="image">Image:
        <input type="file" name="image" id="image">
    </label>
    <button type="submit">Send</button>
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
