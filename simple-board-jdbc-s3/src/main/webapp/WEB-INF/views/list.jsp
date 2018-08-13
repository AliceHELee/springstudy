<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>BBS List</title>
  <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <style>
    #listForm {
      width: 70%;
      margin: 0 auto;     /* 가로로 중앙에 배치 */
      padding-top: 5%;      /* 테두리와 내용 사이의 패딩 여백 */
    }
     
    #loginCheckForm {
      text-align: right;
      padding-right: 15px;
      padding-top: 15px;
    }
     
    #listTitle {
      text-align: center;
    }
     
    #writeLink {
      text-align: right;
    }
     
    /* Bootstrap 수정 */
    .table > thead {
      background-color: #e6ecff;
    }
    .table > thead > tr > th {
      text-align: center;
    }
    .table-hover > tbody > tr:hover {
      background-color: #d9def2;
    }
    .table > tbody > tr > td {
      text-align: center;
    }
    .table > tbody > tr > #title {
      text-align: left;
    }
     
    div > #paging {
      text-align: center;
    }
     
    .hit {
      animation-name: blink;
      animation-duration: 1.5s;
      animation-timing-function: ease;
      animation-iteration-count: infinite;
      /* 위 속성들을 한 줄로 표기하기 */
      /* -webkit-animation: blink 1.5s ease infinite; */
    }
     
    /* 애니메이션 지점 설정하기 */
    /* 익스플로러 10 이상, 최신 모던 브라우저에서 지원 */
    @keyframes blink {
      from {color: white;}
      30% {color: yellow;}
      to {color: red; font-weight: bold;}
      /* 0% {color:white;}
      30% {color: yellow;}
      100% {color:red; font-weight: bold;} */
    }
  </style>
</head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BBS List</title>
</head>
<body>
  <div align="right">
    <!-- Login 검증 -->
    <!-- jstl if문은 else가 없어서 따로 검증해야함. -->
    <c:if test="${id != null}">
      <%@include file="loginOk.jsp" %>
    </c:if>
    <c:if test="${id == null}">
      <%@include file="login.jsp" %>
    </c:if>
  </div>
 
  <div align="center">
    <b>글목록(전체 글:${totalCount})</b>
    <table width="700">
      <tr>
        <td align="right" >
           <a href="/bbs/writeForm.bbs?pageNum=${pageNum}">글쓰기</a>
        </td>
      </tr>
    </table>
   
    <table border="2" width="700">
      <tr>
        <th width="50">번호</th>
        <th width="250">제목</th>
        <th width="100">작성자</th>
        <th width="150">작성일</th>
        <th width="50">조회</th>
      </tr>
      <c:forEach var="article" items="${articles}" varStatus="status">
        <tr align="center" height="30">
          <td>${article.articleNumber}</td>
          <td align="left">
            <c:if test="${article.depth > 0}">
              <img src="" width="${10 * article.depth}" height="16">
              <img src="">
            </c:if>
            <c:if test="${article.depth == 0}">
              <img src="" width="0" height="16">
            </c:if>
            <!-- URL query의 파라미터들은 request에 자동으로 심어지는 듯 하다. -->
            <a href="/bbs/content.bbs?articleNum=${article.articleNumber}&pageNum=${pageNum}">${article.title}</a>
            <c:if test="${article.hit >= 20}">
              <span class="hit">hit!</span>
              <!-- <img src="" border="0" height="16"> -->
            </c:if>
          </td>
          <td>${article.id}</td>
          <td>${article.writeDate}</td>
          <td>${article.hit}</td>
        <tr>
      </c:forEach>
       
      <tr>   
        <td colspan="5" align="center" height="40"> 
          ${pageCode}
        </td>
      </tr>
    </table>
  </div>

  <script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
  <script>
    jQuery(document).ready(function() {
      if(${id== null}) {
        alert("게시판을 이용하시려면 로그인하셔야 합니다.");
        location.href="/bbs";
      }
    });
  </script>
</body>
</html>
