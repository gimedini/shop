package web.shop.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import web.shop.mall.domain.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	 @Override
	 public boolean preHandle(HttpServletRequest req,
	    HttpServletResponse res, Object obj) throws Exception {
	  
	  // 현재의 세션을 불러와 세션에 저장
	  HttpSession session = req.getSession();
	  
	  // 그중 멤버라는 명칭의 세션을 불러와 MemberVO의 형태로 변환
	  // MemberVO형태의 변수인 member에 저장
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  
	  // 로그인 하지않은 경우 로그인 화면으로 이동
	  if(member == null) {
		  res.sendRedirect("../member/signin");
		  return false;
		 }
	  
	  // member에 값이 없는 비로그인상태 or member.verify의 값이 9가 아닐 경우 조건문 실행
	  if(member == null || member.getVerify() != 9) {
	   res.sendRedirect("/"); // 가장 처음 화면으로 되돌리는 역할
	   return false; // false 반환
	  }
	  
	  return true; // preHandle 반환값이 true이면 컨트롤러 진행, false면 진행 멈춤
	 }
}
