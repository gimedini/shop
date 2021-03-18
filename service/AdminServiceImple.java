package web.shop.mall.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import web.shop.mall.domain.CategoryVO;
import web.shop.mall.domain.GoodsVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;
import web.shop.mall.persistence.AdminDAO;

@Service
public class AdminServiceImple implements AdminService{

	private static final Logger logger =
			LoggerFactory.getLogger(AdminService.class);
	
	@Inject
	AdminDAO dao;
	
	// 카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		logger.info("category() call");
		return dao.category();
	}

	// 상품등록
	@Override
	public void register(GoodsVO vo) throws Exception {
		logger.info("register() call : " + vo);
		dao.register(vo);
	}

	// 상품목록
	@Override
	public List<GoodsViewVO> goodslist() throws Exception {
		logger.info("goodslist() call");
		return dao.goodslist();
	}

	// 상품상세
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		logger.info("goodsView() call : " + gdsNum);
		return dao.goodsView(gdsNum);
	}

	// 상품 수정
	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		logger.info("상품 수정 호출 goodsModify()");
		dao.goodsModify(vo);
	}

	// 상품 삭제
	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		logger.info("상품 delete() 호출");
		dao.goodsDelete(gdsNum);
	}

	// 주문 목록
	@Override
	public List<OrderVO> orderList() throws Exception {
		logger.info("orderList() call");
		return dao.orderList();
	}

	// 특정 주문 목록
	@Override
	public List<OrderListVO> orderView(OrderVO order) throws Exception {
		logger.info("orderView() call : " + order);
		return dao.orderView(order);
	}

	// 배송 상태
	@Override
	public void delivery(OrderVO order) throws Exception {
		logger.info("delivery() call : " + order);
		dao.delivery(order);
	}

	// 상품 수량 조절
	@Override
	public void changeStock(GoodsVO goods) throws Exception {
		logger.info("changeStock() call : " + goods);
		dao.changeStock(goods);
	}

	// 모든소감(댓글)
	@Override
	public List<ReplyListVO> allReply() throws Exception {
		logger.info("allReply() call");
		return dao.allReply();
	}

	// 소감(댓글) 삭제
	@Override
	public void deleteReply(int repNum) throws Exception {
		logger.info("deleteReply() call : " + repNum);
		dao.deleteReply(repNum);
	}

}
