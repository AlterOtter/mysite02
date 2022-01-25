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
				<form id="search_form" action="${pageContext.request.contextPath}/board" method="GET">
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
						<td>${cnt-(page-1)*10-(status.count-1)}</td>
						<c:choose>
							<c:when test="${1 eq vo.depth}">
								<td style="text-align:left;padding-left:0">
									<a href="${pageContext.request.contextPath}/board/view?no=${vo.no}">${vo.title}</a>
								</td>	
							</c:when>
							
							<c:otherwise>
							<td style="text-align:left;padding-left:${20*vo.depth}px">
								<a href="${pageContext.request.contextPath}/board/view?no=${vo.no}">
									<img id="profile" style="text-align:left;width:20px" src="${pageContext.servletContext.contextPath}/assets/images/arrow.png">
									${vo.title}
								</a>
							</td>	
							</c:otherwise>
						</c:choose>
						<td>${vo.userName}</td>
						<td>${vo.hit}</td>
						<td>${vo.regDate}</td>
						<c:if test="${null ne authvo}">
							<c:set var="no" value="${authvo.no}"/>
							<c:if test="${vo.user_no eq authvo.no}">
								<td><a href="${pageContext.servletContext.contextPath}/board/delete/${vo.no}" class="del"  style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
							</c:if>
						</c:if>
					</tr>
					</c:forEach>
				</table>		
				<div class="pager">
					<ul>
				<c:forEach items="${pages}" var="page" varStatus="status">
					<c:choose>
						<c:when test="${param.page eq status.count}">
							<li class="selected"><a href="${page}">${status.count}</a></li>
						</c:when>
						<c:when test="${null eq param.page&& status.count eq 1}">
							<li class="selected"><a href="${page}">${status.count}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${page}">${status.count}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					</ul>
				</div>
				<c:if test="${null ne authvo}">
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board/write" id="new-book">글쓰기</a>
					</div>
				</c:if>				
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>