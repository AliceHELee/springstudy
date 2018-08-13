<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	loginForm.jsp
</h1>
<form action="<c:url value="j_spring_security_check" />" method="post">
	ID: <input type="text" name="j_username" id="j_username"><br />
	PW: <input type="text" name="j_password" id="j_password"><br />
	
	<input type="submit" value="전송"> <br />
</form>
<a href="<c:url value='j_spring_security_logout' /> target=_self">Log Out</a>
<!--   http://localhost:8181/security/j_spring_security_logout -->
</body>
</html>
