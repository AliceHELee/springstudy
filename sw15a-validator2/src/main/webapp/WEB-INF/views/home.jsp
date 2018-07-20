<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" % pageEncoding="UTF-8" %>
<html>
<head>
	<title>crt</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  
<form action="create">
이름:	<input type="text" name="name" value="${student.name }"> </br>
아이디:<input type="text" name="id" value="${student.id }"> </br>

<input type="submit value="전송">
</form>
 </P>
</body>
</html>
