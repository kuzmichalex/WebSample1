<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 13.06.2020
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user.jsp</title>
</head>
<body>
    <h1>user.jsp</h1>
    <hr>
    <table>
        <tr>
            <td>Id</td>
            <td>login</td>
            <td>name</td>
            <td>password</td>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
        </tr>
    </table>
</body>
</html>
