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
</body>
</html>