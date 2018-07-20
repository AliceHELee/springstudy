<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Index</title>
</head>
<body>
<h1>
	POST 방식 전송 
</h1>

<form action="student" method="post">
	Post Student ID: <input type="text" name="id"> </br>
	Post Student PW: <input type="text" name="pw"> </br>
	<input type="submit" value="전송">

<h1>
	GET 방식 전송 
</h1>

<form action="student" method="get">
	Post Student ID: <input type="text" name="id"> </br>
	Post Student PW: <input type="text" name="pw"> </br>
	<input type="submit" value="전송">	
</form>
</body>
</html>
