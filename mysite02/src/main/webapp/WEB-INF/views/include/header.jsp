<%@page import="com.poscoict.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="header">
		<style>
	    a {
	    	text-decoration-line: none;
	    }
		</style>		
			<% UserVo authvo = (UserVo)session.getAttribute("authvo");  %>
			<h1><a href="/mysite02/main">MySite</a></h1>
			<ul>
				<li><a href="<%=request.getContextPath() %>/user?a=loginform">로그인</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=joinform">회원가입</a><li>
				<li><a href="<%=request.getContextPath() %>/user?a=updateform">회원정보수정</a><li>
				<%if(authvo==null){%>
				<li>로그인을 해주세요</li>
				<%}else{ %>
				<li><a href="<%=request.getContextPath() %>/user?a=logout">로그아웃</a><li>
				<li><%=authvo.getName()+"님 안녕하세요^^;  " %></li>
				<%} %>
			</ul>
		</div>