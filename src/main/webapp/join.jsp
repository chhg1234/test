<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		let isMidValid = false;
		let isMpwValid = false;
		
		$("#btn").on("click",function(e){
			e.preventDefault();
			
			let mid = $("#mid").val().trim();
			
			if(mid == ""){
				return;
			}
			
			$.ajax({
				url : "/day038/JoinServlet",
				type : "POST",
				data : { condition : "JOIN",
					mid : mid },
				success : function(result){
					if(result == "true"){
						$("#msg").text('사용가능한 아이디입니다.').css('color','green');
						
						//$("#submit").prop('disabled',false);
						isMidValid = true;
					}
					else{
						$("#msg").text('사용불가능한 아이디입니다.').css('color','red');
						
						//$("#submit").prop('disabled',true);
						isMidValid = false;
					}
					checkSubmit();
				}
			});
		});
		
		$("#mpwCheck").on("input",function(){
			let mpw01 = $("#mpw").val();
			let mpw02 = $("#mpwCheck").val();
			
			if(mpw01 == mpw02){
				$("#msgCheck").text('비밀번호가 일치합니다.').css('color','green');
				
				//$("#submit").prop('disabled',false);
				isMpwValid = true;
			}
			else{
				$("#msgCheck").text('비밀번호가 불일치합니다.').css('color','red');
				
				//$("#submit").prop('disabled',true);
				isMpwValid = false;
			}
			
			checkSubmit();
		});
		
		function checkSubmit(){
			if(isMidValid && isMpwValid){
				$("#submit").prop('disabled',false);
			}
			else{
				$("#submit").prop('disabled',true);
			}
		}
	});
</script>
</head>
<body>

	<form action="join.do" method="POST">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="mid" name="mid" required> <button id="btn">중복확인</button>
				<div id="msg"></div></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="mpw" name="mpw" required></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" id="mpwCheck" required>
				<div id="msgCheck"></div></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="회원가입" id="submit" disabled></td>
			</tr>
		</table>
	</form>
	
	<a href="mainPage.do">메인페이지</a>

</body>
</html>