<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Вход</title>
</head>
<body>
<form action = "/login" method="post" enctype="multipart/form-data">
  <label for="email">Email:
    <input type="text" name="email" id="email" required>
  </label><br>
  <label for="password">Password:
    <input type="text" name="password" id="password" required>
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
<form action="/registration" method="get">
  <button type="submit">Еще не зарегестрированы?</button>
</form>
</body>
</html>
