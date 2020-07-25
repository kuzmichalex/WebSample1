<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 13.06.2020
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>users.jsp</title>
</head>
<body>
<h1>users.jsp</h1>
<table>
    <tr>
        <td>id</td>
        <td>login</td>
        <td>name</td>
        <td>password</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
