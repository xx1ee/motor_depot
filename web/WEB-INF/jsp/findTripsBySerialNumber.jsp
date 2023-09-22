<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 20.07.2023
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Все рейсы водителя</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/findTripsBySerialNumber" method="post" >
    <label for="serialNum">Serial number водителя:
        <input type="text" name="serialNum" id="serialNum" required>
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
