package web.shop.mall.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import web.shop.mall.domain.CartListVO;
import web.shop.mall.domain.CartVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.MemberVO;
import web.shop.mall.domain.OrderDetailVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;
import web.shop.mall.domain.ReplyVO;
import web.shop.mall.service.ShopService;
import web.shop.mall.utils.MediaUtil;

@Controller
@RequestMapping("/shop/*")
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Inject
	ShopService shopService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 移댄뀒怨좊━蹂� �긽�뭹 由ъ뒪�듃
	 @GetMapping(value = "/list")
	 public void getList(@RequestParam("c") int cateCode,
	      @RequestParam("l") int level, Model model) throws Exception {
	  logger.info("get list call");
	  
	  List<GoodsViewVO> list = null;
	  list = shopService.list(cateCode, level);
	 
	  model.addAttribute("list", list);
	  
	 }
	 
	// �긽�뭹 議고쉶
	 @GetMapping(value = "/view")
	 public void getView(@RequestParam("n") int gdsNum, Model model) throws Exception{
		 logger.info("get view call");
	 
		 GoodsViewVO view = shopService.goodsView(gdsNum);
		 model.addAttribute("view", view);
		 
			/*
			 * List<ReplyListVO> reply = shopService.replyList(gdsNum);
			 * model.addAttribute("reply", reply);
			 */
	 }
	 
	// �긽�뭹 議고쉶 - �냼媛�(�뙎湲�) �옉�꽦
	/*
	 * @PostMapping(value = "/view") public String registReply(ReplyVO reply,
	 * HttpSession session) throws Exception { logger.info("regist reply");
	 * 
	 * MemberVO member = (MemberVO)session.getAttribute("member");
	 * reply.setUserId(member.getUserId());
	 * 
	 * shopService.registReply(reply);
	 * 
	 * return "redirect:/shop/view?n=" + reply.getGdsNum(); }
	 */
	 
	// �긽�뭹 �냼媛�(�뙎湲�) �옉�꽦
	 @ResponseBody
	 @PostMapping(value = "/view/registReply")
	 public void registReply(ReplyVO reply,  HttpSession session) throws Exception {
		 logger.info("regist reply");
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 reply.setUserId(member.getUserId());
		 
		 shopService.registReply(reply);
		} 
	 
	// �긽�뭹 �냼媛�(�뙎湲�) 紐⑸줉
	 @ResponseBody
	 @GetMapping(value = "/view/replyList")
	 public List<ReplyListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
	  logger.info("get reply list");
	    
	  List<ReplyListVO> reply = shopService.replyList(gdsNum);
	  
	  return reply;
	 } 
	 
	// �긽�뭹 �냼媛�(�뙎湲�) �궘�젣
	 @ResponseBody
	 @PostMapping(value = "/view/deleteReply")
	 public int getReplyList(ReplyVO reply,  HttpSession session) throws Exception {
		 logger.info("post delete reply");

		 int result = 0;
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 String userId = shopService.idCheck(reply.getRepNum());
		   
		 if(member.getUserId().equals(userId)) {
		  
		  reply.setUserId(member.getUserId());
		  shopService.deleteReply(reply);
		  
		  result = 1;
		 }
		 
		 return result; 
		}
	 
	// �긽�뭹 �냼媛�(�뙎湲�) �닔�젙
	 @ResponseBody
	 @PostMapping(value = "/view/modifyReply")
	 public int modifyReply(ReplyVO reply, HttpSession session) throws Exception {
		 logger.info("modify reply");
		 
		 int result = 0;
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 String userId = shopService.idCheck(reply.getRepNum());
		 
		 if(member.getUserId().equals(userId)) {
		  
		  reply.setUserId(member.getUserId());
		  shopService.modifyReply(reply);
		  result = 1;
		 }
		 
		 return result;
		} 
	 
	// 移댄듃 �떞湲�
	 @ResponseBody
	 @PostMapping(value = "/view/addCart")
	 public int addCart(CartListVO cart, HttpSession session) throws Exception {
		 
		 int result = 0;
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 
		 if(member != null) {
		  cart.setUserId(member.getUserId());
		  shopService.addCart(cart);
		  result = 1;
		 }
		 
		 return result;
		}
	 
	// 移댄듃 紐⑸줉
	 @GetMapping(value = "/cartList")
	 public void getCartList(HttpSession session, Model model) throws Exception {
	  logger.info("get cart list");
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  String userId = member.getUserId();
	  
	  List<CartListVO> cartList = shopService.cartList(userId);
	  
	  model.addAttribute("cartList", cartList);
	  
	 }
	 
	// 移댄듃 �궘�젣
	 @ResponseBody
	 @PostMapping(value = "/deleteCart")
	 public int deleteCart(HttpSession session,
		     @RequestParam(value = "chbox[]") List<String> chArr, CartVO cart) throws Exception {
		 logger.info("delete cart");
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 String userId = member.getUserId();
		 
		 int result = 0;
		 int cartNum = 0;
		 
		 
		 if(member != null) {
		  cart.setUserId(userId);
		  
		  for(String i : chArr) {   
		   cartNum = Integer.parseInt(i);
		   cart.setCartNum(cartNum);
		   shopService.deleteCart(cart);
		  }   
		  result = 1;
		 }  
		 return result;  
		}
	 
	// 二쇰Ц 
	 @PostMapping(value = "/cartList")
	 public String order(HttpSession session, OrderVO order, OrderDetailVO orderDetail) throws Exception {
		 logger.info("post order call");
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");  
		 String userId = member.getUserId();
		 
		 Calendar cal = Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		 String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		 String subNum = "";
		 
		 for(int i = 1; i <= 6; i ++) {
		  subNum += (int)(Math.random() * 10);
		 }
		 
		 String orderId = ymd + "_" + subNum;
		 
		 order.setOrderId(orderId);
		 order.setUserId(userId);
		  
		 shopService.orderInfo(order);
		 
		 orderDetail.setOrderId(orderId);   
		 shopService.orderInfo_Details(orderDetail);
		 
		 shopService.cartAllDelete(userId);
		 
		 
		 return "redirect:/shop/orderList";  
		}
	 
	 // 二쇰Ц 紐⑸줉
	 @GetMapping(value = "/orderList")
	 public void getOrderList(HttpSession session, OrderVO order, Model model) throws Exception {
		 logger.info("get order list");
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 String userId = member.getUserId();
		 
		 order.setUserId(userId);
		 
		 List<OrderVO> orderList = shopService.orderList(order);
		 
		 model.addAttribute("orderList", orderList);
		}
	 
	 // 주문 상세 목록
	 @GetMapping(value = "/orderView")
	 public void getOrderList(HttpSession session,
		      @RequestParam("n") String orderId,
		      OrderVO order, Model model) throws Exception {
		 logger.info("get order view");
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 String userId = member.getUserId();
		 
		 order.setUserId(userId);
		 order.setOrderId(orderId);
		 
		 List<OrderListVO> orderView = shopService.orderView(order);
		 
		 model.addAttribute("orderView", orderView);
		}
	 
	 @RequestMapping("display")
		public ResponseEntity<byte[]> display(String fileName) throws IOException {
			logger.info("display() call");
			
			ResponseEntity<byte[]> entity = null;
			InputStream in = null;
			
			String filePath = uploadPath + fileName;
			in = new FileInputStream(filePath);
			
			// �뙆�씪 �솗�옣�옄
			String extension = 
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info("�뙆�씪 �솗�옣�옄 : " + extension);
			
			// �쓳�떟 �뿤�뜑(response header)�뿉 Content-Type �꽕�젙
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.geMediaType(extension));

			
			// �뜲�씠�꽣 �쟾�넚
			entity = new ResponseEntity<byte[]> (
						IOUtils.toByteArray(in), // �뙆�씪�뿉�꽌 �씫�� �뜲�씠�꽣
						httpHeaders, // �쓳�떟 �뿤�뜑
						HttpStatus.OK
					);

			return entity;
		}
		
		
}
