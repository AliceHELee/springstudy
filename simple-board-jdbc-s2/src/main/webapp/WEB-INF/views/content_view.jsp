<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
 
<html>
<head>
	<meta http-equiv=="Content-Type" content="text/html; charset=UTF-8">	
	<title>Home</title>
</head>
<body>
<h1>
	Content View
</h1>

<form action="modify" method="post">

<table width="600" cellpadding="0" cellspacing="0" border="1">
<INPUT TYPE="hidden" name="bId" value="${content_view.bId}">
<tr>
	<td>번호</td>
	<td>${content_view.bId}</td>
</tr>
<tr>
	<td>조회</td>
	<td>${content_view.bHit}</td>
</tr>
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
	<input type="submit" value="수정">
	<a href="list">목록보기</a>
	<a href="delete?bId=${content_view.bId}">삭제</a>
	<a href="reply_view?bId=${content_view.bId}">답변</a>
	</td>
</tr>  
</table>
</form>
</body>
</html>
