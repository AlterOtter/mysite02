<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
   <div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
      <div id="content">
         <div id="guestbook" class="delete-form">
            <form method="POST" action="${pageContext.request.contextPath}/guest/delete">
               <input type='hidden' name="no" value="${no}">
               <label>비밀번호</label>
               <input type="password" name="password">
               <input type="submit" value="확인">
            </form>
            <a href="">방명록 리스트</a>
         </div>
      </div>
         	<c:import url="/WEB-INF/views/include/nav.jsp"/>
			<c:import url="/WEB-INF/views/include/footer.jsp"/>
   </div>
</body>
</html>