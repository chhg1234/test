<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, model.dto.BoardDTO" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$("#search").on("input",function(){
			let keyword = $("#search").val();
			let condition = $("#condition").val();
			
			$.ajax({
				url : "/day038/SearchServlet",
				type : "GET",
				data : { keyword : keyword,
					condition : condition },
				dataType : "json",
				success : function(result){
					//console.log("확인1");
					//console.log(result);
					//console.log(typeof result);		
					
					let txt = "<tr><td>글 번호</td><td>제목</td><td>작성자</td></tr>";
					
					$.each(result, function(index, value){
						txt += "<tr><td><a href='boardPage.do?bid="+value.bid+"'>"+value.bid+"</a></td>";
						txt += "<td>"+value.title+"</td><td>";
						if(value.writer == null){
							txt += "탈퇴한 사용자";
						}
						else{
							txt += value.writerName;
						}
						txt += "</td></tr>";
					});
					
					$("#table").html(txt);
				},
				error : function(){
					console.log("확인2");
				}
			});
		});
	});
</script>
</head>
<body>

<div id="login">
<c:if test="${not empty userInfo}">
	<h1>${userInfo}님, 안녕하세요! :D</h1>
	<a href="logout.do">로그아웃</a>
	<a href="mypage.do">마이페이지</a>
	<a href="writePage.do">글작성</a>
</c:if>
<c:if test="${empty userInfo}">
	<form action="login.do" method="POST">
		<input type="text" name="mid" placeholder="아이디를 입력하세요." required>
		<input type="password" name="mpw" placeholder="비밀번호를 입력하세요." required>
		<input type="submit" value="로그인">
	</form>
	<a href="joinPage.do">회원가입</a>
</c:if>
</div>

<br>
<form action="controller.jsp" method="GET">
	<input type="hidden" name="command" value="MAINPAGE">
	<select name="condition" id="condition">
		<option value="TITLE">제목으로 검색</option>
		<option value="WRITER">작성자로 검색</option>
	</select>
	<input type="text" name="keyword" id="search" required>
	<input type="submit" value="글 검색">
</form>
<br>

<table id="table" border="1">
	<tr>
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
	</tr>
	<c:forEach var="data" items="${datas}">
	<tr>
		<td><a href="boardPage.do?bid=${data.bid}">${data.bid}</a></td>
		<td>${data.title}</td>
		<td>
		<c:if test="${empty data.writerName}">
			탈퇴한 사용자
		</c:if>
		<c:if test="${not empty data.writerName}">
			${data.writerName}
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>