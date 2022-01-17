<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="/mysite02/user?a=login">
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="<%=request.getAttribute("email")!=null?request.getAttribute("email"):"" %>">
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="<%=request.getAttribute("password")!=null?request.getAttribute("password"):"" %>">
					<p>
						<%if("fail".equals(request.getAttribute("result"))){ %>
							로그인에 실패 하였습니다.
						<%}%>
					</p>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>