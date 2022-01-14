<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
			<h1>MySite</h1>
			<ul>
				<li><a href="<%=request.getContextPath() %>/user?a=loginform">로그인</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=joinform">회원가입</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=updateform">회원정보수정</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=logoutform">로그아웃</a><li>
				<li>권순모님 안녕하세요 ^^;</li>
			</ul>
		</div>
</body>
</html>