# mysite


#versions
1. mysite02 : mvc, model 2, servlet 기반
2. mysite03 : spring mvc fw 기반, xml comfiguration
3. mysite04 : spring mvc fw 기반, java configuration 1
4. mysite05 : spring mvc fw 기반, java configuration 2 (web.xml x)
5. mysite06 : spring boot 기반, java configuration


//========================================================================================================================

	EL 태그

//========================================================================================================================


//================================================Servlet=================================================================
package jstlel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _01Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값
		int iVal = 10;
		long lVal = 10;
		float fVal = 3.14f;
		boolean bVal = true;
		String sVal = "가나다라마바사";
		
		// 객체
		Object obj = null;
		UserVo userVo = new UserVo();
		userVo.setNo(10L);
		userVo.setName("둘리");
		
		// map
		Map<String, Object> map = new HashMap<>();
		map.put("ival", iVal);
		map.put("lval", lVal);
		map.put("fval", fVal);
		map.put("bval", bVal);
		
		
		request.setAttribute("ival", iVal);
		request.setAttribute("lval", lVal);
		request.setAttribute("fval", fVal);
		request.setAttribute("bval", bVal);
		request.setAttribute("sval", sVal);
		
		request.setAttribute("obj", obj);
		request.setAttribute("user", userVo);
		
		request.setAttribute("m", map);
		request
			.getRequestDispatcher("/WEB-INF/views/01.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

//==============================================VIEW==========================================================================

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL 연습</h1>
	
	<h4>값출력</h4>
	${ival } <br/>
	${lval } <br/>
	${fval } <br/>
	${bval } <br/>
	${sval } <br/>
	
	<h4>객체 출력</h4>
	---${obj }--- <br/>
	${user.no } <br/>
	${user.name } <br/>

	<h4>map의 값 출력</h4>
	${m.ival } <br/>
	${m.lval } <br/>
	${m.fval } <br/>
	${m.bval } <br/>
	
	<h4>산술연산</h4>
	${3*4+6/2 } <br/>
	${ival + 10 } <br/>
	
	<h4>관계연산</h4>
	${ival == 10 } <br/>
	${ival < 5 } <br/>
	${obj == null } <br/>
	${empty obj } <br/>
	${obj != null } <br/>
	${not empty obj } <br/>

	<h4>논리연산</h4>	
	${ival == 10 && lval < 100 } <br/>
	${ival == 10 || lval < 100 } <br/>

	<h4>요청 파라미터</h4>	
	---${param.a + 10 }--- <br/>
	---${param.email }--- <br/>
			
			
</body>
</html>
//=============================================================================================




	Scope
  





//=================================================================================================
1. scope 범위
객체가 존재하는 범위


2.객체가 오래 지속되는 순서
Application(Context) Scope > Session Scope > Request Scope> Page Context


3.EL 이 이름으로 객체 찾는 순서
Application(context) scope <Session Scope < Request Scope < Page Context

주의: 같은 이름으로 여러 범위에 객체를 저장 할 경우 주의 필요 


//===============================================================================================





//===============================================================================================
package jstlel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _03Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserVo> list = new ArrayList<>();
		
		UserVo vo1 = new UserVo();
		vo1.setNo(1L);
		vo1.setName("둘리1");
		list.add(vo1);

		UserVo vo2 = new UserVo();
		vo2.setNo(2L);
		vo2.setName("둘리2");
		list.add(vo2);

		UserVo vo3 = new UserVo();
		vo3.setNo(3L);
		vo3.setName("둘리3");
		list.add(vo3);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/03.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
//========================================================================================================


//======================================================================================================


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>JTSL(forEach Tag) Test</h4>
	<c:set var="count" value="${fn:length(list) }" />
		
	<c:forEach items="${list }" var="vo" varStatus="status">
		  [${count-status.index }] [${status.index }:${status.count }] [${vo.no } : ${vo.name }] <br/>
	</c:forEach>
	
</body>
</html>



//==========================================================================================================
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


//=====================================================================================================
if else문 
<c:choose>
		<c:when test="${empty authvo}">
				<li><a href="${pageContext.request.contextPath}/user?a=loginform">로그인</a><li>
				<li><a href="${pageContext.request.contextPath}/user?a=joinform">회원가입</a><li>
				<li>로그인을 해주세요</li>
		</c:when>
		<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/user?a=updateform">회원정보수정</a><li>
					<li><a href="${pageContext.request.contextPath}/user?a=logout">로그아웃</a><li>
					<li>${authvo.name}님 안녕하세요^^;</li>
		</c:otherwise>
</c:choose>


///=========================================================================================================
for문
  <c:forEach items="${list}" var="vo"  varStatus="status" >
              	<li>
                  <table>
                     <tr>
                        <td>${status.count}</td>
                        <td>${vo.name}</td>
                        <td>${vo.reg_date}</td>
                        <td><a href="${pageContext.request.contextPath}/guest?a=deleteform&no=${vo.no}">삭제</a></td>
                     </tr>
                     <tr>
                        <td colspan=4>
                     	${vo.message}
                        </td>
                     </tr>
                  </table>
                  <br>
               </li>
              
  </c:forEach>
  //====================================================================================================
  <p>
  <c:set var="str" value="fail" />
		<c:if test="${result eq str}">
		로그인에 실패하셨습니다.
	</c:if>
  </p>
        