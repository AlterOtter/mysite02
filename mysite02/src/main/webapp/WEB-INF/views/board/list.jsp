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
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board" method="post">
					<input type="hidden" name="a" value="search">
					<input type="text" id="kwd" name="input" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" var="vo"  varStatus="status" >				
					<tr>
						<td>${vo.no}</td>
						<c:choose>
							<c:when test="${1 eq vo.depth}">
								<td style="text-align:left;padding-left:0">
									<a href="/mysite02/board?a=viewform&board_sn=${vo.no}">${vo.title}</a>
								</td>	
							</c:when>
							<c:otherwise>
							<td style="text-align:left;padding-left:${20*vo.depth}px">
								<a href="/mysite02/board?a=viewform&board_sn=${vo.no}">
									<img id="profile" style="text-align:left;width:20px" src="/mysite02/assets/images/arrow.png">
									${vo.title}
								</a>
							</td>	
							</c:otherwise>
						</c:choose>
						<td>${vo.userName}</td>
						<td>${vo.hit}</td>
						<td>${vo.regDate}</td>
						<td><a href="${pageContext.servletContext.contextPath}/board?a=delete&no=${vo.no}" class="del"  style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
					</tr>
					</c:forEach>
				</table>
				<c:forEach items="${pages}" var="page" varStatus="status">
					<a href="${page}">${status.count}</a>
				</c:forEach>
				<div class="bottom">
					<a href="/mysite02/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>