<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그</title>
</head>
<body>
<h1>로그인 페이지입니다</h1>
	<form action="/login" method="post">
		<input type="text" placeholder="Username" name="username"/>
		<input type="password" placeholder="Password" name="password" />
		<Button>로그인</Button>
	</form>
	<br/>
	
	회원가입 하지 않으셨나요?<a href="/joinForm">회원가입</a>
 <a href="/oauth2/authorization/google">구글 로그인</a>
 <a href="/oauth2/authorization/facebook">페이스북 로그인</a>
 <!-- spring yml에 registration정보를 토대로 알아서 쿼리스트링 만들어서 요청주소를 때려준다 -->
 <a href="/oauth2/authorization/naver">네이버 로그인</a>
 <a href="/oauth2/authorization/kakao">카카오톡 로그인</a>  
</body>
</html>