<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 04.07.2023
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователи</title>
</head>
<body>
<%@include file="header.jsp"%>
<ul>
  <c:forEach var ="user"  items = "${requestScope.users}">
      <img height="100" width="80" src="${pageContext.request.contextPath}/images/${user.getImage()}" alt="User image">
    <li>
            ${user.getDescription()}
    </li>
  </c:forEach>
</ul>
</body>
</html>
