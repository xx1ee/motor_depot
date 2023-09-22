<%--
  Created by IntelliJ IDEA.
  User: kochetova
  Date: 19.07.2023
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
  <c:if test="${not empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/logout" method="post">
      <button>Logout</button>
    </form>
  </c:if>
</div>
