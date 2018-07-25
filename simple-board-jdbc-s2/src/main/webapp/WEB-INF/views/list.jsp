<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
 
<html>
<head>
	<meta http-equiv=="Content-Type" content="text/html; charset=UTF-8">	
	<title>Home</title>
</head>
<body>
<h1>
	List
</h1>

<table width="600" cellpadding="0" cellspacing="0" border="1">

<tr>
<th>번호</th>
<th>이름</th>
<th>제목</th>
<th>날짜</th>
<th>조회수</th>
</tr>

<c:forEach items="${list}" var="dto" >
<tr>
<td>${dto.bId}</td>
<td>${dto.bName}</td>
<td>
<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a>
</td>
<td>${dto.bDate}</td>
<td>${dto.bHit}</td>
</tr>
</c:forEach>

<tr>
<td colspan="5"><a href="write_view">글 작성</td>
</tr>

</table>
</body>
</html>
