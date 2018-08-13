<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>BBS Content</title>
  <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <style>
    #contentForm {
      width: 40%;
      margin: 0 auto;
      padding-top: 5%;
    }
 
    .table > thead > tr > th, .table > tbody > tr > th {
      background-color: #e6ecff;
      text-align: center;
    }
  </style>
</head>
<body>
  <form action="/bbs/replyForm.bbs" method="post">
    <div id="contentForm">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <input type="hidden" name="depth" value="${article.depth}">
        <input type="hidden" name="pos" value="${article.pos}">
        <input type="hidden" name="groupId" value="${article.groupId}">
         
        <div>
          <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>글쓴이</th>
              <td>${article.id}</td>
              <th>조회수</th>
              <td>${article.hit}</td>
            </tr>
            <tr>
              <th>제목</th>
              <td>${article.title}</td>
              <th>날짜</th>
              <td>${article.writeDate}</td>
            </tr>
            <tr>
              <th colspan="2">다운로드</th>
              <td colspan="2">
                <!-- 파일 다운로드 기능 -->
                <a href="">${article.fileName}</a>
                <%-- <a href="/bbs/download.bbs?fileName=${article.fileName}">${article.fileName}</a> --%>
              </td>
            </tr>
          </thead>
          <tbody>
            <tr height="200" valign="top">
              <td colspan="4">${article.content}</td>
            </tr>
            <tr>
              <th>첨부</th>
              <td colspan="3">
                <c:if test="${article.fileName == null}">없음</c:if>
                <c:if test="${article.fileName != null}">${article.fileName}</c:if>
              </td>
            </tr>
          </tbody>
        </table>
         
          <div id="btns" class="btn-group btn-group-sm" role="group" aria-label="...">
          <c:if test="${id != null}">
            <input type="submit" class="btn btn-default" value="답글달기">
            <c:if test="${id == article.id}">
              <input type="button" class="btn btn-default" value="수정하기" onclick="document.location.href='/bbs/updateForm.bbs?articleNumber=${article.articleNumber}&pageNum=${pageNum}'">
              <input type="button" class="btn btn-default" value="삭제하기" onclick="document.location.href='/bbs/delete.bbs?articleNumber=${article.articleNumber}&pageNum=${pageNum}'">
            </c:if>
            <c:if test="${id != article.id}">
              <input type="button" class="btn btn-default" value="수정하기" disabled="disabled">
              <input type="button" class="btn btn-default" value="삭제하기" disabled="disabled">
            </c:if>
          </c:if>
          <c:if test="${id == null}">
            <input type="submit" class="btn btn-default" value="답글달기" disabled="disabled">
            <input type="button" class="btn btn-default" value="수정하기" disabled="disabled">
            <input type="button" class="btn btn-default" value="삭제하기" disabled="disabled">
          </c:if>
          <input type="button" class="btn btn-default" value="목록으로" onclick="document.location.href='/bbs/list.bbs?pageNum=${pageNum}'">
        </div>
        </div>
    </div>
  </form>
 
  <script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
  <script>
    jQuery(document).ready(function() {
      if(${id== null}) {
        alert("게시판을 이용하시려면 로그인하셔야 합니다.");
        location.href="/bbs/login.bbs";
      }
    });
  </script>
</body>
</html>