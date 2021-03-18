package web.shop.mall.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import web.shop.mall.domain.CategoryVO;
import web.shop.mall.domain.GoodsVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;

@Repository
public class AdminDAOImple implements AdminDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImple.class);
	
	@Inject
	private SqlSession sqlSession;

	// 매퍼
	private static String NAMESPACE = "web.shop.mall.adminMapper";
	
	// 카테고리
	public List<CategoryVO> category() throws Exception {
		logger.info("category DB success");
		return sqlSession.selectList(NAMESPACE + ".category");
	}

	// 상품 등록
	@Override
	public void register(GoodsVO vo) throws Exception {
		logger.info("registe DB success");
		sqlSession.insert(NAMESPACE + ".register", vo);
	}

	// 상품 목록
	@Override
	public List<GoodsViewVO> goodslist() throws Exception {
		logger.info("goodslist DB success");
		return sqlSession.selectList(NAMESPACE + ".goodslist");
	}

	// 상품 상세
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		logger.info("상품 상세 DB success : " + gdsNum);
		return sqlSession.selectOne(NAMESPACE + ".goodsView", gdsNum);
	}

	// 상품 수정
	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		logger.info("상품수정 DB 연결 성공 : " + vo);
		sqlSession.update(NAMESPACE + ".goodsModify", vo);
	}

	// 상품 삭제
	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		logger.info("상품삭제 DB 연결 성공 : " + gdsNum);
		sqlSession.delete(NAMESPACE + ".goodsDelete", gdsNum);
	}

	// 주문 목록
	@Override
	public List<OrderVO> orderList() throws Exception {
		logger.info("orderList DB connect success");
		return sqlSession.selectList(NAMESPACE + ".orderList");
	}

	// 특정 주문 목록
	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		logger.info("orderView DB connect : " + order);
		return sqlSession.selectList(NAMESPACE + ".orderView", order);
	}

	// 배송 상태
	@Override
	public void delivery(OrderVO order) throws Exception {
		logger.info("delivery DB : " + order);
		sqlSession.update(NAMESPACE + ".delivery", order);
	}

	// 상품 수량 조절
	@Override
	public void changeStock(GoodsVO goods) throws Exception {
		logger.info("changeStock DB connect : " + goods);
		sqlSession.update(NAMESPACE + ".changeStock", goods);
	}

	// 모든 소감(댓글)
	@Override
	public List<ReplyListVO> allReply() throws Exception {
		logger.info("allReply DB connect");
		return sqlSession.selectList(NAMESPACE + ".allReply");
	}

	// 소감(댓글) 삭제
	@Override
	public void deleteReply(int repNum) throws Exception {
		logger.info("deleteReply DB connect");
		sqlSession.delete(NAMESPACE + ".deleteReply", repNum);
	}
	
	
	

	
}
