<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=utf-8">
    <title>Title: WebSample 1</title>
</head>

<body>

<h1>index.jsp <br></h1>
<hr>

<a href="${pageContext.request.contextPath}/users">all users</a> <br>
<a href="${pageContext.request.contextPath}/users/1">users id = 1</a> <br>
<a href="${pageContext.request.contextPath}/users/search?id=3">users id > 3</a> <br>

<hr>

<a href="${pageContext.request.contextPath}/roles">all roles</a> <br>
<a href="${pageContext.request.contextPath}/roles/create?roleName=new_role">create Role</a> <br>

<hr>
<form method = "PUT" action = "${pageContext.request.contextPath}/roles/create">
                <label accesskey="roleName">
                    <input name="roleName" value="test_role">
                </label>
                <input type = "submit" value = "create"/>
</form>

<hr>
<a href="${pageContext.request.contextPath}/statistics">methods invocation statistics</a>
</body>


</html>
