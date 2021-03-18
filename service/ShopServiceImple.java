package web.shop.mall.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import web.shop.mall.domain.CartListVO;
import web.shop.mall.domain.CartVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.OrderDetailVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;
import web.shop.mall.domain.ReplyVO;
import web.shop.mall.persistence.ShopDAO;

@Service
public class ShopServiceImple implements ShopService{
	
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImple.class);
	
	@Inject
	private ShopDAO dao;

	@Override
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception {
		logger.info("list() call : " + cateCode);
		int cateCodeRef = 0;  // 移댄뀒怨좊━ 李몄“ 肄붾뱶. �뾾�뼱�룄 臾닿�
		 
		if(level == 1) {  // lavel 1 = 1李� 遺꾨쪟.
		  
		cateCodeRef = cateCode;
		return dao.list(cateCode, cateCodeRef);
		// �몢媛�吏� 紐⑤몢 cateCode濡� �빐�룄 臾닿�
		  
		 } else {  // lavel 2 = 2李� 遺꾨쪟
		  
		return dao.list(cateCode);
		  
		 }
	}

	// �긽�뭹議고쉶
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		logger.info("goodsView() call : " + gdsNum);
		return dao.goodsView(gdsNum);
	}

	// �긽�뭹 �냼媛�(�뙎湲�)�옉�꽦
	@Override
	public void registReply(ReplyVO reply) throws Exception {
		logger.info("registReply() call : " + reply);
		dao.registerReply(reply);
	}

	// �긽�뭹 �냼媛�(�뙎湲�) 由ъ뒪�듃
	@Override
	public List<ReplyListVO> replyList(int gdsNum) throws Exception {
		logger.info("replyList() call : " + gdsNum);
		return dao.replyList(gdsNum);
	}

	// �긽�뭹 �냼媛�(�뙎湲�) �궘�젣
	@Override
	public void deleteReply(ReplyVO reply) throws Exception {
		logger.info("deleteReply() call : " + reply);
		dao.deleteReply(reply);
	}

	// �븘�씠�뵒 泥댄겕
	@Override
	public String idCheck(int repNum) throws Exception {
		logger.info("idCheck() call : " + repNum);
		return dao.idCheck(repNum);
	}

	// �긽�뭹 �냼媛�(�뙎湲�) �닔�젙
	@Override
	public void modifyReply(ReplyVO reply) throws Exception {
		logger.info("modifyReply() call : " + reply);
		dao.modifyReply(reply);
	}

	// 移댄듃 �떞湲�
	@Override
	public void addCart(CartListVO cart) throws Exception {
		logger.info("addCart() call : " + cart);
		dao.addCart(cart);
	}

	// 移댄듃 由ъ뒪�듃
	@Override
	public List<CartListVO> cartList(String userId) throws Exception {
		logger.info("cartList() call : " + userId);
		return dao.cartList(userId);
	}

	// 移댄듃 �궘�젣
	@Override
	public void deleteCart(CartVO cart) throws Exception {
		logger.info("deleteCart() call : " + cart);
		dao.deleteCart(cart);
	}

	// 二쇰Ц �젙蹂�
	@Override
	public void orderInfo(OrderVO order) throws Exception {
		logger.info("orderInfo : " + order);
		dao.orderinfo(order);
	}
	
	// 二쇰Ц �긽�꽭 �젙蹂�
	@Override
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception {
		logger.info("orderDetails() call : " + orderDetail);
		dao.orderInfo_Details(orderDetail);
	}

	// 移댄듃 鍮꾩슦湲�
	@Override
	public void cartAllDelete(String userid) throws Exception {
		logger.info("cartAllDelete() call : " + userid);
		dao.cartAllDelete(userid);
	}

	@Override
	public List<OrderVO> orderList(OrderVO order) throws Exception {
		logger.info("orderList() call");
		return dao.orderList(order);
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		logger.info("orderView() call");
		return dao.orderView(order);
	}

	
	
}
