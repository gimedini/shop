<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Gimedini</title>
	
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="../include/header.jsp" %>
		</div>
	</header>

	<nav id="nav">
		<div id="nav_box">
			<%@ include file="../include/nav.jsp" %>
		</div>
	</nav>

	<section id="container">
		<div id="container_box">

<section id="content">
	<form role="form" method="post" autocomplete="off">
		<div class="input_area">
			<label for="userId">아이디</label>
			<input type="email" id="userId" name="userId" placeholder="example@email.com" required="required" />						
			<button type="button" class="idCheck">아이디 확인</button>
		</div>
		<p class="result">
			<span class="msg">아이디를 확인해주세요.</span>
		</p>

		<div class="input_area">
			<label for="userPass">패스워드</label>
			<input type="password" id="userPass" name="userPass" required="required" />						
		</div>

		<div class="input_area">
			<label for="userName">닉네임</label>
			<input type="text" id="userName" name="userName" placeholder="닉네임을 입력해주세요" required="required" />						
		</div>

		<div class="input_area">
			<label for="userPhon">연락처</label>
			<input type="text" id="userPhon" name="userPhon" placeholder="연락처를 입력해주세요" required="required" />						
		</div>

		<button type="submit" id="signup_btn" name="signup_btn" disabled="disabled">회원가입</button>

	</form>			
</section>

		</div>
	</section>

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>		
	</footer>

</div>

<script> 
$(".idCheck").click(function(){
 
 var query = {userId : $("#userId").val()};
 
 $.ajax({
  url : "${pageContext.request.contextPath}/member/idCheck",
  type : "post",
  data : query,
  success : function(data) {
  
   if(data == 1) {
    $(".result .msg").text("사용 불가");
    $(".result .msg").attr("style", "color:#f00");   
    $("#signup_btn").attr("disabled", "disabled");
   } else {
    $(".result .msg").text("사용 가능");
    $(".result .msg").attr("style", "color:#00f");
    $("#signup_btn").removeAttr("disabled");
   }
  }
 });  // ajax 끝
});

$("#userId").keyup(function(){
	 $(".result .msg").text("아이디를 확인해주십시오.");
	 $(".result .msg").attr("style", "color:#000");
	 
	 $("#signup_btn").attr("disabled", "disabled");
	 
	});
</script>
</body>
</html>