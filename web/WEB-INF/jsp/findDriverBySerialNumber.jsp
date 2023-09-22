<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 23.07.2023
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поиск водителя по серийному номеру</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/findDriverBySerialNumber" method="post" >
    <label for="serialNum">Serial number авто:
        <input type="text" name="serialNum" id="serialNum" required>
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
