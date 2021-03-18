package web.shop.mall.persistence;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.shop.mall.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO{

	private final Logger logger = LoggerFactory.getLogger(MemberDAOImple.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	// 매퍼
	private static String NAMESPACE = "web.shop.mall.memberMapper";

	// 회원가입
	@Override
	public void signup(MemberVO vo) throws Exception {
		logger.info("DB signup : " + vo);
		sqlSession.insert(NAMESPACE + ".signup", vo);
	}

	// 로그인
	@Override
	public MemberVO signin(MemberVO vo) throws Exception {
		logger.info("DB 로그인 성공 : " + vo);
		return sqlSession.selectOne(NAMESPACE + ".signin", vo);
	}

	// 아이디 중복 확인
	@Override
	public MemberVO idCheck(String userid) throws Exception {
		logger.info("Id 중복 확인 " + userid);
		return sqlSession.selectOne(NAMESPACE + ".idCheck", userid);
	}
	
	
}
