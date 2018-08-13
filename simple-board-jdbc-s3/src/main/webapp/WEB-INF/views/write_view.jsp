<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
 
<html>
<head>
	<meta http-equiv=="Content-Type" content="text/html; charset=UTF-8">	
	<title>Home</title>
</head>
<body>
<h1>
	Write View
</h1>

<form action="write" method="post">

<table width="600" cellpadding="0" cellspacing="0" border="1">
<tr>
	<td>이름</td>
	<td><input type="text" name="bName" value="${content_view.bName}"></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="bTitle" value="${content_view.bTitle}"></td>
</tr>
<tr>
	<td>내용</td>
	<td><textarea rows="10" name="bContent">${content_view.bContent}</textarea></td>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value="입력">
	<a href="list">목록보기</a>
	</td>
</tr>  
</table>
</form>

</body>
</html>
