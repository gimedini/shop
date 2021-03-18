package web.shop.mall.persistence;

import web.shop.mall.domain.MemberVO;

public interface MemberDAO {

	// 회원가입
	public void signup(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberVO signin(MemberVO vo) throws Exception;
	
	// 아이디 중복확인
	public MemberVO idCheck(String userid) throws Exception;
	
}
