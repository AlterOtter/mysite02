<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.6.0.js"></script>
<script>
$(function(){
	$("#join-form").submit(function(event){
		event.preventDefault();
		//이름이 비어 있는지 확인
		
		//이름의 유효성 체크
		if($("#name").val()===''){
			alert('이름이 비어 있습니다');
			$("#name").focus();
			return;
		}
		
		//email 유효성 체크 (Empty 체크)
		if($("#email").val()===''){
			alert('이메일이 비어 있습니다');
			$("#email").focus();
			return;
		}
		
		console.log($("#btn-checkemail").css('display'));
		
		// 중복체크 여부 
		if($("#btn-checkemail").css('display')==='inline-block'){
			alert('중복체크를 안 하였습니다.');
			$("#email").focus();
			return;
		}
		
		//비밀번호 유효성(empty) 체크
		if($("#password").val()===''){
			alert('비밀번호가 비어있습니다.');
			$("#password").focus();
			return;
		}
		
		console.log("ok");
		//유효성 ok
		$("#join-form")[0].submit();
	});
	
	
	
 	$("#btn-checkemail").click(function(){
		 var email = $("#email").val();
	 	 if(email==''){
			 return;
	 	}
	 
	 
	$.ajax({
		url:"${pageContext.request.contextPath}/user/api/checkmail?email="+email,
		type:"get",
		dataType:"json",
		success:function(res){
			if(res.result !== 'success'){
				console.error(res);
				return;
			}
			
			if(res.data){
				alert("존재하는 이메일 입니다. 다른 이메일을 사용해 주세요");
				$("#email").val('').focus();
				return;
			}
			
			$("#img-checkemail").show();
			$("#btn-checkemail").hide();
			
			
		},
		error:function(xhr,status,res){
			console.error(status,res);
		}
	});
 })
	
})

</script>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form:form
					modelAttribute="userVo" 
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
					
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="text-align:left; padding-left:0; color:#f00">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message code="${errors.getFieldError('name').codes[0] }" />
							</c:if>
						</spring:hasBindErrors>
					</p>
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" id="btn-checkemail"value="id 중복체크">
					<img src="${pageContext.request.contextPath }/assets/images/check.png"  id="img-checkemail"hidden="hidden" style="width:14px"/>
					<p style="text-align:left; padding-left:0; color:#f00">
						<form:errors path="email" />
					</p>	
					
					<label class="block-label"><spring:message code="user.join.label.password" /></label>
					<form:password path="password" />
					<p style="text-align:left; padding-left:0; color:#f00">
						<form:errors path="password" />
					</p>	
					
					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여"/>
						<form:radiobutton path="gender" value="male" label="남"/>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
    	<c:import url="/WEB-INF/views/include/nav.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>