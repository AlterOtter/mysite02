<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var start= -1;
var render = function(vo){
	var html=
	"<li data-no="+vo.no+">"+
		"<strong>"+vo.name+"</strong>"+
		"<p>"+vo.message+"<br>"+
		"</p>"+
		"<strong></strong>"+
		"<a href='' data-no="+vo.no+">삭제</a>"+ 
	"</li>";
	return html;
	
}
//var getlist = function(start_num)
$(function(){
	$.ajax({
		url:"${pageContext.request.contextPath}/guestbook/api/list2/"+start,
		type:"get",
		dataType:"json",
		contentType:"application/json",
		success:function(res){
			
			var html='';
			res.data.forEach(function(vo){
				start=vo.no;
				html+=render(vo);				
			});
			
			$("#list-guestbook").prepend(html);
			return;
		},
		error:function(xhr,status,res){
			return;
		}
	});
});

$(window).scroll(function(){
	console.log("scroll");

	var $window=$(this);
	var $document = $(document);
	
	var windowHeight=$window.height();
	var documentHight=$document.height();
	var scrollTop=$window.scrollTop();
	
	if(scrollTop + windowHeight == documentHight){
		$.ajax({
			url:"${pageContext.request.contextPath}/guestbook/api/list2/"+start,
			type:"get",
			dataType:"json",
			contentType:"application/json",
			success:function(res){
				
				var html='';
				res.data.forEach(function(vo){
					start=vo.no;
					html+=render(vo);				
				});
				
				$("#list-guestbook").append(html);
				return;
			},
			error:function(xhr,status,res){
				return;
			}
		});
	}
 });
 
$(function(){
	var dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen:false,
		modal:true,
		buttons:{
			"삭제":function(){
				var no = $("#hidden-no").val();
				var password=$("#password-delete").val();
		
				var url = "${pageContext.request.contextPath}/guestbook/api/delete/"+no;
				
				$.ajax({
					url:url,
					type:"DELETE",
					dataType:'json',
					contentType:"application/x-www-form-urlencoded",
					data:$.param({password}),
					success:function(res){
						if(res.result==='success'){
							$("#list-guestbook li[data-no='"+res.data + "']").remove();
							dialogDelete.dialog('close');
							return;
						}else{
							$(".validateTips.error").show();
							$("#password-delete").val("").focus();
							return;
						}		
					},
					error:function(xhr,status,res){
						console.log(res);
						return;
					}
					
				});
			},
			"취소":function(){
				$("#password-delete").val("");
				$("hidden-no").val("");
				$(".validateTips.error").hide();
				$("#dialog-delete-form").dialog('close');
			}
		},
		close:function(){
			$("#password-delete").val("");
			$("hidden-no").val("");
			$(".validateTips error").hide();
		}
	});
	
	//글삭제 버튼 Click 이벤트 처리 (Live Event)
	$(document).on("click","#list-guestbook li a",function(e){
		e.preventDefault();
		var no = $(this).data('no');
		$("#hidden-no").val(no);
		dialogDelete.dialog('open');
	});
	
	//동적으로 가져오기때문에 안먹음
	$("#list-guestbook li a").click(function(e){
		e.preventDefault();
		console.log("clicked");
	});
	
});
$(function(){	
	
	var message_dialog=function(title,message,callback_function){
		$("#dialog p").text(message)
		$( "#dialog" ).attr("title",title).dialog({
			modal:true,
			button:{
				"확인":function(){
					$(this).dialog("close");
				}
			},
			close:callback_function
		});
	}
	
	
	$("#add-form").submit(function(e){
		e.preventDefault();
		
		if($("#input-name").val()===''){
			message_dialog("다시 확인해주세요","이름을 입력해 주세요.",function(){
				$("#input-name").focus();				
			});
			return;
		}
		
		
		if($("#input-password").val( )===''){
			message_dialog("다시 확인해주세요","비밀번호 입력해 주세요.",function(){
				$("#input-password").focus();				
			});
			return;
		}
		
		if($("#tx-content").val()===''){
			message_dialog("다시 확인해주세요","내용 입력해 주세요.",function(){
				$("#tx-content").focus();				
			});
			return;
		}
		
		
		var vo = {};
		vo.name=$("#input-name").val();
		vo.password=$("#input-password").val();
		vo.message=$("#tx-content").val();
		
		console.log(vo);
		$.ajax({
			url:"${pageContext.request.contextPath}/guestbook/api/add",
			type:"post",
			data:JSON.stringify(vo),
			dataType:"json",
			contentType:"application/json",
			success:function(res){
				
				var html= render(res.data);
				$("#list-guestbook").prepend(html);
				return;
			},
			error:function(xhr,status,res){
				return;
			}
		});
		
		
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
					
				
									
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/include/nav.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>