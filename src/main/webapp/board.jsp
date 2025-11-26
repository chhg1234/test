<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.ArrayList" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>
<script type="text/javascript">
	function del(){
		let ans = confirm('정말 삭제하시겠습니까?');
		if(ans == true){
			document.checkForm.action.value = "deleteBoard.do"; // ★
			document.checkForm.submit();
		}
		else{
			return;
		}
	}
	
	function edit(rid,bid){
		// [b] 요소 불러오기
		let elem = document.getElementById('reply_'+rid);
		
		// [c] 원래댓글내용 만들기에 활용됨
		let apple = elem.innerText;
		apple = apple.replace(" 댓글변경 댓글삭제","");
		
		/*
		[a] 새로운댓글내용
		<form action="controller.jsp" method="POST">
			<input type="hidden" name="command" value="UPDATEREPLYCONTENT">
			<input type="hidden" name="bid" value="">
			<input type="hidden" name="rid" value="">
			<input type="text" name="content" required value="기존내용">
		</form>
		*/
		// [a] txt 변수 ▶ 새로운댓글내용 만들기에 활용됨
		let txt = '<form action="updateReplyContent.do" method="POST">';
		txt += '<input type="hidden" name="bid" value="'+bid+'">';
		txt += '<input type="hidden" name="rid" value="'+rid+'">';
		txt += '<input type="text" name="content" required value="'+apple+'">';
		txt += '<input type="submit" value="댓글변경">';
		txt += '</form>';
		
		// [b] 불러온 요소에 새로운 HTML([a]에서 만든 것) 작성
		elem.innerHTML = txt;
	}
	
	function check(rid,bid){
		let ans = confirm('정말 삭제하시겠습니까?');
		if(ans){
			location.href='deleteReply.do?rid='+rid+'&bid='+bid;
		}
	}
</script>
	
	<form name="checkForm" action="updateTitle.do" method="POST">
		<input type="hidden" name="bid" value="${board.bid}">
		<table border="1">
			<tr>
				<td>글 번호</td><td>제목</td><td>내용</td><td>작성자</td>
			</tr>
			<tr>
				<td>${board.bid}</td><td><input type="text" name="title" value="${board.title}" required></td><td>${board.content}</td>
				<td><c:if test="${empty board.writerName}">
					탈퇴한 사용자
				</c:if>
				<c:if test="${not empty board.writerName}">
					${board.writerName}
				</c:if></td>
			</tr>
				<c:if test="${not empty userInfo and (userMrole eq 'ADMIN' or userInfo eq board.writer)}">
				<tr>
					<td colspan="4" align="right"><input type="submit" value="제목 변경">&nbsp;<input type="button" value="글 삭제" onclick="del()"></td>
				</tr>
				</c:if>
		</table>		
	</form>
	
	<hr>
	
	<c:if test="${not empty userInfo}">
	<form action="insertReply.do" method="POST">
		<input type="hidden" name="bid" value="${board.bid}">
		<input type="hidden" name="writer" value="${userInfo}">
		${userNameInfo} ▶ <input type="text" name="content" required>
		<input type="submit" value="댓글 작성">
	</form>
	</c:if>
	<c:if test="${empty userInfo}">
		<input type="text" value="댓글을 작성하려면 로그인이 필요합니다." disabled size="35">
	</c:if>
	<div id="reply">
		<ul>
			<c:forEach var="data" items="${replyDatas}">
			<li>
				<c:if test="${empty data.writerName}">
					탈퇴한 사용자
				</c:if>
				<c:if test="${not empty data.writerName}">
					${data.writerName}
				</c:if>
				님 <span id='reply_${data.rid}'>${data.content}
				<c:if test="${not empty userInfo and (userMrole eq 'ADMIN' or userInfo eq data.writer)}">
					<button onclick='edit(${data.rid},${data.bid})'>댓글변경</button> <button onclick="check(${data.rid},${data.bid})">댓글삭제</button>
				</c:if>
				</span>
			</li>
			</c:forEach>
		</ul>
	</div>
	
	<hr>
	
	<a href="mainPage.do">메인페이지</a>

</body>
</html>