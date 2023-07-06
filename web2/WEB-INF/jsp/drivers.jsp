<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 04.07.2023
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.title}</title>
</head>
<body>
<ul>
  <c:forEach var ="driver"  items = "${requestScope.drivers}">
    <li>${driver.getDescription()} </li>
  </c:forEach>
</ul>
</body>
</html>
