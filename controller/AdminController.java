package web.shop.mall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import web.shop.mall.domain.CategoryVO;
import web.shop.mall.domain.GoodsVO;
import web.shop.mall.domain.GoodsViewVO;
import web.shop.mall.domain.OrderListVO;
import web.shop.mall.domain.OrderVO;
import web.shop.mall.domain.ReplyListVO;
import web.shop.mall.domain.ReplyVO;
import web.shop.mall.service.AdminService;
import web.shop.mall.utils.MediaUtil;
import web.shop.mall.utils.UploadFileUtils;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger
	= LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService adminService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 관리자 화면
	@GetMapping(value = "/index")
	public void getIndex() throws Exception{
		logger.info("get index call");
	}
	
	// 상품등록
	@GetMapping(value = "/goods/register")
	public void getGoodsRegister(Model model) throws Exception{
		logger.info("get goods register call");
		
		// Category형태의 List변수 category 선언
		List<CategoryVO> category = null;

		// adminService.category() 호출
		category = adminService.category();

		// JSONArray를 이용해 category를 JSON타입으로 변경한 뒤, category라는 명칭으로 모델에 추가
		model.addAttribute("category", JSONArray.fromObject(category));
		
		
	}
	
	@PostMapping(value = "/goods/register")
	public String postGoodsRegister(GoodsVO vo, MultipartFile file) throws Exception, IOException{
		logger.info("post goods register call");
		

		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		logger.info("imgUploadPath = " + imgUploadPath);
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		logger.info("ymdPath = " + ymdPath);
		String fileName = null;

		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
		 fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		 logger.info("저장된 fileName = " + fileName);
		 
		  // gdsImg에 원본 파일 경로 + 파일명 저장
		  vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		  // gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장
		  vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		} else {

		 fileName = "images" + File.separator + "none.png";
		 logger.info("실패한 fileName = " + fileName);
		 
		// 미리 준비된 none.png파일을 대신 출력함
		  
		  vo.setGdsImg(fileName);
		  vo.setGdsThumbImg(fileName);
		}

			
		adminService.register(vo);
		return "redirect:/admin/index";
		
	}
	
	// 상품 목록
	@GetMapping(value = "/goods/list")
	public void getGoodsList(Model model) throws Exception{
		logger.info("get goods list call");
		
		List<GoodsViewVO> list = adminService.goodslist();
		
		model.addAttribute("list", list);
	}
	
	// 상품 상세
	@GetMapping(value = "/goods/view")
	public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception{
		logger.info("get goods view call");
		
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		
		model.addAttribute("goods", goods);
	}
	
	// 상품 수정
	@GetMapping(value = "/goods/modify")
	public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception{
		logger.info("get goods modify 호출");
		
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		model.addAttribute("goods", goods);
		
		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	
	@PostMapping(value = "/goods/modify")
	public String postGoodsModify(GoodsVO vo, MultipartFile file, HttpServletRequest requset) throws Exception {
		 logger.info("post goods modify");
		 
		// 새로운 파일이 등록되었는지 확인
		 if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
		  // 기존 파일을 삭제
		  new File(uploadPath + requset.getParameter("gdsImg")).delete();
		  new File(uploadPath + requset.getParameter("gdsThumbImg")).delete();
		  
		  // 새로 첨부한 파일을 등록
		  String imgUploadPath = uploadPath + File.separator + "imgUpload";
		  String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		  String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
		  
		  vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		  vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		  
		 } else {  // 새로운 파일이 등록되지 않았다면
		  // 기존 이미지를 그대로 사용
		  vo.setGdsImg(requset.getParameter("gdsImg"));
		  vo.setGdsThumbImg(requset.getParameter("gdsThumbImg"));
		  
		 }
		 

		 adminService.goodsModify(vo);
		 
		 return "redirect:/admin/index";
		}
	
	// 상품 삭제
	@PostMapping(value = "/goods/delete")
	public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
		 logger.info("post goods delete");

		 adminService.goodsDelete(gdsNum);
		 
		 return "redirect:/admin/index";
		}
	
	// 주문 목록
	@GetMapping(value = "/shop/orderList")
	public void getOrderList(Model model) throws Exception {
	 logger.info("get order list");
	   
	 List<OrderVO> orderList = adminService.orderList();
	 
	 model.addAttribute("orderList", orderList);
	}

	// 주문 상세 목록
	@GetMapping(value = "/shop/orderView")
	public void getOrderList(@RequestParam("n") String orderId,
	      OrderVO order, Model model) throws Exception {
	 logger.info("get order view");
	 
	 order.setOrderId(orderId);  
	 List<OrderListVO> orderView = adminService.orderView(order);
	 
	 model.addAttribute("orderView", orderView);
	}
	
	// 주문 상세 목록 - 상태 변경
	@PostMapping(value = "/shop/orderView")
	public String delivery(OrderVO order) throws Exception {
	 logger.info("post order view");
	   
	 adminService.delivery(order);
	 
	 // 상품 수량 조절
	 List<OrderListVO> orderView = adminService.orderView(order); 

	 GoodsVO goods = new GoodsVO();
	   
	 for(OrderListVO i : orderView) {
	  goods.setGdsNum(i.getGdsNum());
	  goods.setGdsStock(i.getCartStock());
	  adminService.changeStock(goods);
	 }

	 return "redirect:/admin/shop/orderView?n=" + order.getOrderId();
	}
	
	// 모든 소감(댓글)
	@GetMapping(value = "/shop/allReply")
	public void getAllReply(Model model) throws Exception {
	 logger.info("get all reply");
	   
	 List<ReplyListVO> reply = adminService.allReply();
	 
	 model.addAttribute("reply", reply);
	}
	
	// 모든 소감(댓글)
	@PostMapping(value = "/shop/allReply")
	public String postAllReply(ReplyVO reply) throws Exception {
			logger.info("post all reply");
					
			adminService.deleteReply(reply.getRepNum());
			
			return "redirect:/admin/shop/allReply";
		}	
	
	@RequestMapping("/goods/display")
	public ResponseEntity<byte[]> display(String fileName) throws IOException {
		logger.info("display() 호출");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadPath + fileName;
		in = new FileInputStream(filePath);
		
		// 파일 확장자
		String extension = 
				filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info("파일 확장자 : " + extension);
		
		// 응답 헤더(response header)에 Content-Type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.geMediaType(extension));

		
		// 데이터 전송
		entity = new ResponseEntity<byte[]> (
					IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 헤더
					HttpStatus.OK
				);

		return entity;
	}
	
	@RequestMapping("/shop/display")
	public ResponseEntity<byte[]> displays(String fileName) throws IOException {
		logger.info("display() 호출");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadPath + fileName;
		in = new FileInputStream(filePath);
		
		// 파일 확장자
		String extension = 
				filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info("파일 확장자 : " + extension);
		
		// 응답 헤더(response header)에 Content-Type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.geMediaType(extension));

		
		// 데이터 전송
		entity = new ResponseEntity<byte[]> (
					IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 헤더
					HttpStatus.OK
				);

		return entity;
	}
	
	// ck 에디터에서 파일 업로드
	@PostMapping(value = "/goods/ckUpload")
	public void postCKEditorImgUpload(HttpServletRequest req, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
	 logger.info("post CKEditor img upload");
	 
	 // 랜덤 문자 생성
	 UUID uid = UUID.randomUUID();
	 
	 OutputStream out = null;
	 PrintWriter printWriter = null;
	   
	 // 인코딩
	 response.setCharacterEncoding("utf-8");
	 response.setContentType("text/html;charset=utf-8");
	 
	 try {
	  
	  String fileName = upload.getOriginalFilename(); // 파일 이름 가져오기
	  byte[] bytes = upload.getBytes();
	  
	  String defaultPath = req.getSession().getServletContext().getRealPath("/");
	  
	  // 업로드 경로
	  String ckUploadPath = defaultPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	  
	  out = new FileOutputStream(new File(ckUploadPath));
	  out.write(bytes);
	  out.flush(); // out에 저장된 데이터를 전송하고 초기화
	  
	  String callback = req.getParameter("CKEditorFuncNum");
	  printWriter = response.getWriter();
	  String fileUrl = "/ckUpload/" + uid + "_" + fileName; // 작성화면
	  
	  // 업로드시 메시지 출력
	  printWriter.println("<script type='text/javascript'>"
	     + "window.parent.CKEDITOR.tools.callFunction("
	     + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
	     +"</script>");
	  
	  printWriter.flush();
	  
	 } catch (IOException e) { e.printStackTrace();
	 } finally {
	  try {
	   if(out != null) { out.close(); }
	   if(printWriter != null) { printWriter.close(); }
	  } catch(IOException e) { e.printStackTrace(); }
	 }
	 
	 return; 
	}
	

	
	
} // end AdminController
