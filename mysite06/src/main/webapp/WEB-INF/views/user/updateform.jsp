<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="POST" action="${pageContext.request.contextPath}/user/update">
					<input type="hidden" name="no" value="${userInfo.no}">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text"  value="${userInfo.name}" required>
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${userInfo.email}" required>
					<input type="button" value="id 중복체크">
					<label class="block-label" for="password">비밀 번호</label>
					<input id="password" name="password" type="password"  value="" >
				
					
					<fieldset>
						<legend>성별</legend>
						<c:set var="str" value="male"></c:set>
						<c:choose>
							<c:when test="${userInfo.gender eq str}">
									<label>여</label> <input type="radio" name="gender" value="female">
									<label>남</label> <input type="radio" name="gender" value="male"  checked>
							</c:when>
							<c:otherwise>
									<label>여</label> <input type="radio" name="gender" value="female" checked>
									<label>남</label> <input type="radio" name="gender" value="male" >
							</c:otherwise>
						</c:choose>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox"  checked name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
    	<c:import url="/WEB-INF/views/include/nav.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>