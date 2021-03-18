<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Gimedini</title>
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
   <input type="email" id="userId" name="userId" required="required" />      
  </div>
  
  <div class="input_area">
   <label for="userPass">패스워드</label>
   <input type="password" id="userPass" name="userPass" required="required" />      
  </div>
       
  <button type="submit" id="signin_btn" name="signin_btn" @click="kakaoLogin">로그인</button>
	<br>
<!-- 	<a href="https://kauth.kakao.com/oauth/authorize?client_id=08160f56f6160067d33c9ce227eb5bb6&redirect_uri=http://localhost:8080/mall/oauth&response_type=code">
		<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
	</a> -->
	<a href="javascript:kakaoLogin();"><img src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png" style="height: 60px; width: auto;" /></a>
	<a href="http://developers.kakao.com/logout">로그아웃</a>

	<script src="http://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript">
		window.Kakao.init("858c4b968bc8341d75678194cfcca062");
		
		function kakaoLogin() {
			window.Kakao.Auth.login({
				scope:'profile, account_email',
				success: function(authObj) {
					console.log(authObj);
					window.Kakao.API.request({
						url:'/v2/user/me',
						success: res =>{
							const kakao_account = res.kakao_account;
							console.log(kakao_account);
							
							
						}
					});
				}
			});
		}
		
		function kakaoLogout(kakaoKey) {
			Kakao.init(kakaoKey); // 초기화
			Kakao.isInitialized();
			
			if(!Kakao.Auth.getAccessToken()){ // 토큰이 있는지 확인(토큰 가져와 보기)
				console.log('Not logged in');
				return;
			}
			Kakao.Auth.logout(function() { // 카카오 로그아웃
				console.log(Kakao.Auth.getAccessToken());
			});
		}
		


	</script>

	<br>
<!-- 	<a href="/logout">로그아웃</a> -->
  
  <c:if test="${msg == false}">
   <p style="color:#f00;">로그인에 실패했습니다.</p>
  </c:if>
  
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
</body>
</html>