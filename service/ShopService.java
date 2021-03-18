package web.shop.mall.service;

import java.util.List;

import web.shop.mall.domain.CartListVO;
import web.shop.mall.domain.CartVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.OrderDetailVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;
import web.shop.mall.domain.ReplyVO;

public interface ShopService {
	
	// 移댄뀒怨좊━蹂� �긽�뭹 由ъ뒪�듃
	public List<GoodsViewVO> list(int cateCode, int level) throws Exception;
	
	// �긽�뭹議고쉶
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	// �긽�뭹 �냼媛�(�뙎湲�) �옉�꽦
	public void registReply(ReplyVO reply) throws Exception;
	
	// �긽�뭹 �냼媛�(�뙎湲�)由ъ뒪�듃
	public List<ReplyListVO> replyList(int gdsNum) throws Exception;

	// �긽�뭹�냼媛�(�뙎湲�) �궘�젣
	public void deleteReply(ReplyVO reply) throws Exception;
	
	// �븘�씠�뵒 泥댄겕
	public String idCheck(int repNum) throws Exception;
	
	// �긽�뭹 �냼媛�(�뙎湲�) �닔�젙
	public void modifyReply(ReplyVO reply) throws Exception;
	
	// 移댄듃 �떞湲�
	public void addCart(CartListVO cart) throws Exception;
	
	// 移댄듃 由ъ뒪�듃
	public List<CartListVO> cartList(String userId) throws Exception;
	
	// 移댄듃 �궘�젣
	public void deleteCart(CartVO cart)throws Exception;
	
	// 二쇰Ц �젙蹂�
	public void orderInfo(OrderVO order)throws Exception;
	
	// 二쇰Ц �긽�꽭 �젙蹂�
	public void orderInfo_Details(OrderDetailVO orderDetail) throws Exception;
	
	// 移댄듃 鍮꾩슦湲�
	public void cartAllDelete(String userid) throws Exception;
	
	// 二쇰Ц 紐⑸줉
	public List<OrderVO> orderList(OrderVO order) throws Exception;
	
	// 특정 주문 목록
	public List<OrderListVO> orderView(OrderVO order) throws Exception;
	
}
