<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>

	<form action="write.do" method="POST">
		<input type="hidden" name="writer" value="${userInfo}">
		<table border="1">
			<tr>
				<td>제목</td><td>내용</td><td>작성자</td>
			</tr>
			<tr>
				<td><input type="text" name="title" required></td>
				<td><input type="text" name="content" required></td>
				<td>${userNameInfo}</td>
			</tr>
			<tr>
				<td colspan="3" align="right"><input type="submit" value="글 작성"></td>
			</tr>
		</table>		
	</form>
	
	<a href="mainPage.do">메인페이지</a>

</body>
</html>