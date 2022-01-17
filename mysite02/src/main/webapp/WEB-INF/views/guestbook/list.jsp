<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ page import = "com.poscoict.mysite.vo.GuestbookVO, java.util.ArrayList, com.poscoict.mysite.dao.guest_dao" %> 
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<% 
	ArrayList<GuestbookVO> volist = (ArrayList<GuestbookVO>)request.getAttribute("list");
	%>
   <div id="container">
     <jsp:include page="/WEB-INF/views/include/header.jsp" />
      <div id="content">
         <div id="guestbook">
            <form action="/mysite02/guest" method="POST">
               <input type="hidden" name="a" value="insert">
               <table>
                  <tr>
                     <td>이름</td><td><input type="text" name="name"></td>
                     <td>비밀번호</td><td><input type="password" name="password"></td>
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
            <% for(int i=0;i<volist.size();i++){ %>
               <li>
                  <table>
                     <tr>
                        <td><%=i+1 %></td>
                        <td><%=volist.get(i).getName() %></td>
                        <td><%=volist.get(i).getReg_date() %></td>
                        <td><a href="<%=request.getContextPath() %>/guest?a=deleteform&no=<%=volist.get(i).getNo() %>">삭제</a></td>
                     </tr>
                     <tr>
                        <td colspan=4>
                     	<%=volist.get(i).getMessage().replaceAll("\n", "<br>") %>
                        </td>
                     </tr>
                  </table>
                  <br>
               </li>
               <%} %>
            </ul>
         </div>
      </div>
    	<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
   </div>
</body>
</html>