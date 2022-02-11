<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
         <div id="guestbook">
            <form action="${pageContext.request.contextPath}/guest/insert" method="POST">
               <table>
                  <tr>
                     <td>이름</td><td><input type="text" name="name" value="${authvo.name}"></td>
                     <td>비밀번호</td><td><input type="password" name="password" ></td>
                  </tr>
                  <tr>
                     <td colspan=4><textarea name="message" id="content"></textarea></td>
                  </tr>
                  <tr>
                     <td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
                  </tr>
               </table>
            </form>
            <ul>
               <c:forEach items="${list}" var="vo"  varStatus="status" >
              	<li>
                  <table>
                     <tr>
                        <td>${status.count}</td>
                        <td>${vo.name}</td>
                        <td>${vo.reg_date}</td>
                        <td><a href="${pageContext.request.contextPath}/guest/delete/${vo.no}">삭제</a></td>
                     </tr>
                     <tr>
                        <td colspan=4>
                       <% pageContext.setAttribute("newLine", "\n"); %>
                     	${fn:replace(vo.message,newLine,"<br/>")}
                        </td>
                     </tr>
                  </table>
                  <br>
               </li>
              
               </c:forEach>
            </ul>
         </div>
      </div>
    	<c:import url="/WEB-INF/views/include/nav.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
   </div>
</body>
</html>