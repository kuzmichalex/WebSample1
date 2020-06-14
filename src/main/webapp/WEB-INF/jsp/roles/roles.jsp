<%@ taglib prefix="r" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 14.06.2020
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Roles</title>
</head>
<body>
<h1>Roles</h1>
<hr>

<table>
<tr>
    <td>id</td>
    <td>ROLE</td>
</tr>
<r:forEach var="roles" items="${roles}">
    <tr>
        <td>${roles.id}</td>
        <td>${roles.roleName}</td>
    </tr>
</r:forEach>
</table>

</body>
</html>
