package web.shop.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;

import web.shop.mall.domain.MemberVO;
import web.shop.mall.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService service;

	@Lazy
	@Autowired
	BCryptPasswordEncoder passEncoder;

	// 회원 가입 get
	@GetMapping(value = "/signup")
	public void getSignup() throws Exception {
		logger.info("get signup");
	}

	// 회원 가입 post
	@PostMapping(value = "/signup")
	public String postSignup(MemberVO vo) throws Exception {
		logger.info("post signup");

		// 입력받은 패스워드를 BVrypt로 암호화 시키고 다시 MemberVO에 넘겨주는 방식
		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);

		service.signup(vo);

		return "redirect:/";
	}

	// 로그인  get
	
	  @GetMapping(value = "/signin") public void getSignin() throws Exception {
	 logger.info("get signin"); 
	 }
	 

	  
	// 로그인 post
	@PostMapping(value = "signin")
	public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("post signin");

		MemberVO login = service.signin(vo);
		HttpSession session = req.getSession();

		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());

		if (login != null && passMatch) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/signin";
		}

		return "redirect:/";
	}

	// 로그아웃
	@GetMapping(value = "/signout")
	public String signout(HttpSession session) throws Exception {
		logger.info("get logout");

		service.signout(session);

		return "redirect:/";
	}

	// 아이디 중복 확인
	@ResponseBody
	@PostMapping(value = "/idCheck")
	public int postIdCheck(HttpServletRequest req) throws Exception {
		logger.info("post idCheck");

		String userId = req.getParameter("userId");
		MemberVO idCheck = service.idCheck(userId);

		int result = 0;

		if (idCheck != null) {
			result = 1;
		}

		return result;
	}


}
