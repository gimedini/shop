package web.shop.mall.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import web.shop.mall.domain.CartListVO;
import web.shop.mall.domain.CartVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.OrderDetailVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;
import web.shop.mall.domain.ReplyVO;

@Repository
public class ShopDAOImple implements ShopDAO{

	private static final Logger logger = LoggerFactory.getLogger(ShopDAOImple.class);
	
	private static String NAMESPACE = "web.shop.mall.shopMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	
	// 移댄뀒怨좊━蹂� �긽�뭹 由ъ뒪�듃 : 1李⑤텇瑜�
	@Override
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception {
		logger.info("list 1 DB connect : " + cateCode + cateCodeRef);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cateCode", cateCode);
		map.put("cateCodeRef", cateCodeRef);
		return sqlSession.selectList(NAMESPACE + ".list_1", cateCode);
	}

	// 移댄뀒怨좊━蹂� �긽�뭹 由ъ뒪�듃 : 2李⑤텇瑜�
	@Override
	public List<GoodsViewVO> list(int cateCode) throws Exception {
		logger.info("list 2 DB connent : " + cateCode);
		return sqlSession.selectList(NAMESPACE + ".list_2", cateCode);
	}

	// �긽�뭹議고쉶
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		logger.info("goodsView DB connent : " + gdsNum);
		return sqlSession.selectOne("web.shop.mall.adminMapper" + ".goodsView", gdsNum);
	}

	// �긽�뭹 �냼媛�(�뙎湲�) �옉�꽦
	@Override
	public void registerReply(ReplyVO reply) throws Exception {
		logger.info("registerReply DB connect : " + reply);
		sqlSession.insert(NAMESPACE + ".registReply", reply);
		
	}

	@Override
	public List<ReplyListVO> replyList(int gdsNum) throws Exception {
		logger.info("replyList DB connect : " + gdsNum);
		return sqlSession.selectList(NAMESPACE + ".replyList", gdsNum);
	}

	// �긽�뭹 �냼媛�(�뙎湲�) �궘�젣
	@Override
	public void deleteReply(ReplyVO reply) throws Exception {
		logger.info("deleteReply DB connect : " + reply);
		sqlSession.delete(NAMESPACE + ".deleteReply", reply);
	}

	// �븘�씠�뵒 泥댄겕
	@Override
	public String idCheck(int repNum) throws Exception {
		logger.info("idCheck DB connect : " + repNum);
		return sqlSession.selectOne(NAMESPACE + ".replyUserIdCheck", repNum);
	}

	// �긽�뭹 �냼媛�(�뙎湲�) �닔�젙
	@Override
	public void modifyReply(ReplyVO reply) throws Exception {
		logger.info("modifyReply DB connect : " + reply);
		sqlSession.update(NAMESPACE + ".modifyReply", reply);
	}

	// 移댄듃 �떞湲�
	@Override
	public void addCart(CartListVO cart) throws Exception {
		logger.info("addCart DB connect : " + cart);
		sqlSession.insert(NAMESPACE + ".addCart", cart);
		
	}

	// 移댄듃 由ъ뒪�듃
	@Override
	public List<CartListVO> cartList(String userId) throws Exception {
		logger.info("cartList DB connect : " + userId);
		return sqlSession.selectList(NAMESPACE + ".cartList", userId);
	}

	// 移댄듃 �궘�젣
	@Override
	public void deleteCart(CartVO cart) throws Exception {
		logger.info("deleteCart DB connect : " + cart);
		sqlSession.delete(NAMESPACE + ".deleteCart", cart);
	}
	
	// 二쇰Ц �젙蹂�
	@Override
	public void orderinfo(OrderVO order) throws Exception {
		logger.info("orderInfo DB connect : " + order);
		sqlSession.insert(NAMESPACE + ".orderInfo", order);
	}
	
	// 二쇰Ц �긽�꽭 �젙蹂�
	@Override
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception {
		logger.info("orderInfo_Details DB connect : " + orderDetail);
		sqlSession.insert(NAMESPACE + ".orderInfo_Details", orderDetail);
	}

	// 移댄듃 鍮꾩슦湲�
	@Override
	public void cartAllDelete(String userid) throws Exception {
		logger.info("cartAlldeleter DB connect : " + userid);
		sqlSession.delete(NAMESPACE + ".cartAllDelete", userid);
	}

	// 二쇰Ц 紐⑸줉
	@Override
	public List<OrderVO> orderList(OrderVO order) throws Exception {
		logger.info("orderList DB connect : " + order);
		return sqlSession.selectList(NAMESPACE + ".orderList", order);
	}

	
	// 특정 주문 목록
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		logger.info("orderView : " + order);
		return sqlSession.selectList(NAMESPACE + ".orderView", order);
	}

	
	

}
