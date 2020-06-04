<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=utf-8">
    <title>Title: WebSample 1</title>
</head>

<body>

<h1>index.jsp <br></h1>

WebSample1 Hello, World!<br> <br>

WebSample2 Go to main.jsp <a href="main.jsp"> click </a> <br> <br>

WebSample3 Go to WEB-INF <a href="sample3"> click </a> <br> <br>

WebSample4 <a href="controller"> click </a> <br> <br>

<form action="controller" method="post">
    WebSample5 <input type="submit" value="Sample5"/>
</form>
<br><br>

WebSample6 <a href="sample6"> click</a> <br> <br>

Баловство с формой
<form action="inputController" method="post">
    <label>
        <input type="text" name="input1" value="123"/>
    </label>
    <input type="submit" value="Отправить это баловство"/>
</form>
<br>

testDB <a href="testDB"> click</a> <br> <br>


</body>


</html>
