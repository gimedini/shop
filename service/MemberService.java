package web.shop.mall.service;

import javax.servlet.http.HttpSession;

import web.shop.mall.domain.MemberVO;

public interface MemberService {


	// 회원가입
	public void signup(MemberVO vo) throws Exception;
		
	// 로그인
	public MemberVO signin(MemberVO vo) throws Exception;
		
	// 로그아웃
	public void signout(HttpSession session) throws Exception;	
	
	// 아이디 중복확인
	public MemberVO idCheck(String userId) throws Exception;
}
