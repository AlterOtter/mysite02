<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>${siteVo.title}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<div style="align-content: center;align-items: center;">
						<img id="profile" style="width: 500px" src="${pageContext.request.contextPath}${siteVo.profile}">
					</div>
					<h2>${siteVo.welcome}</h2>
					<p>
						${siteVo.description}
						<br><br>
						<a href="${pageContext.request.contextPath}/guest">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		  <c:import url="/WEB-INF/views/include/nav.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>