<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${content.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							 <% pageContext.setAttribute("newLine", "\n"); %>
                     			${fn:replace(content.contents,newLine,"<br/>")}
							</div>
						</td>
					</tr>
				</table>
				글번호: <input type="text" value="${content.no}"><br>
				그룹번호: <input type="text" value="${content.groupNo}"><br>
				오더번호: <input type="text" value="${content.orderNo}"><br>
				깊이: <input type="text" value="${content.depth}"><br>
				글쓴이번호: <input type="text" value="${content.userVo.no}"><br>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?a=replyform&no=${content.no}">답글달기</a>
					<a href="${pageContext.request.contextPath}/board">글목록</a>
					<a href="${pageContext.request.contextPath}/board?a=modifyform">글수정</a>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>