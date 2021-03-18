package web.shop.mall.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.shop.mall.domain.MemberVO;
import web.shop.mall.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {

	private final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO dao;

	// 회원가입
	@Override
	public void signup(MemberVO vo) throws Exception {
		logger.info("signup 호출 : " + vo);
		dao.signup(vo);
	}

	// 로그인
	@Override
	public MemberVO signin(MemberVO vo) throws Exception {
		logger.info("signin 호출 : " + vo);
		return dao.signin(vo);
	}

	// 로그아웃
	@Override
	public void signout(HttpSession session) throws Exception {
		logger.info("signout 호출 : " + session);
		session.invalidate();
		
	}

	// 아이디 중복 확인
	@Override
	public MemberVO idCheck(String userId) throws Exception {
		logger.info("idCheck() call : " + userId);
		return dao.idCheck(userId);
	}
	
	
	
}
