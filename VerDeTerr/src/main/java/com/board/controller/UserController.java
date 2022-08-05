package com.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/logout")
	public String logoutProcess(HttpSession session, Model model) {
		System.out.println("1111");
		model.addAttribute("msgLogout", "로그아웃되었습니다.");
		session.removeAttribute("id");
		return "main";
	}

	@GetMapping("/findId")
	public String findId() {
		return "findId";
	}
	
	@GetMapping("findPw")
	public String findPw() {
		return "findPw";
	}


	/**
	 * 
	 * @param id
	 * @param pw
	 * @param model
	 * @return
	 */
	@PostMapping("/login_proc")
	public String loginProcess(HttpServletRequest request, UserDTO params, Model model) {
		HttpSession session = request.getSession(true);
		String myID = params.getId();
		String myPW = params.getPw();
		UserDTO user = userService.loginCheck(myID, myPW);
		if (user == null) {
			model.addAttribute("msgLoginError", "아이디 혹은 비밀번호 오류");
			return "main";

		} else {
			model.addAttribute("msgLoginSuccess", "로그인되었습니다. " + myID + "님, 환영합니다!");
			session.setAttribute("id", myID);
		}
		return "main";
	}

	@GetMapping(value = "/main")
	public String openMainpage(Model model) {
		return "main";
	}
	
    @GetMapping(value = "/mypage")
    public String openMypage(HttpSession session, Model model) {
        
        UserDTO params = new UserDTO();
        String myID = (String) session.getAttribute("id");
        String myNickname = "";
        String myEmail = "";
        
        if(myID!=null) {
        	System.out.println("1111");
            params = userService.getUserDetail(myID);
            myNickname = params.getNickname();
            myEmail = params.getEmail();
        }
        
        System.out.println(params.toString());
		model.addAttribute("nick", myNickname);
		model.addAttribute("email", myEmail);
		
        List<SurveyOutputDTO> testList = userService.getUserHistory(myID);
        model.addAttribute("testList", testList);
		return "mypage";
	}
    
	@GetMapping("/identify")
	public String identify(Model model) {
		return "identify";
	}
	
	@GetMapping("/modify")
	public String modify(Model model) {
		return "modify";
	}
	
	/**
	 * 
	 * @param pw
	 * @param model
	 * @return
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/identify_proc")
	public String identifyProcess(HttpServletRequest request, UserDTO params, Model model) {
		HttpSession session = request.getSession(true);
		UserDTO user = userService.getUserDetail((String)session.getAttribute("id"));
		if (!passwordEncoder.matches(params.getPw(),user.getPw())) {
			model.addAttribute("msgIden", "비밀번호가 틀렸습니다.");
			return "identify";
		} else {
			return "redirect:/mypage";
		}
	}
    
	/**
	 * 
	 * @param pw
	 * @param pw2
	 * @param model
	 * @return
	 */
	@PostMapping("/modify_proc")
	public String modifyProcess(HttpSession session, String pw, String pw2, Model model) {
		UserDTO user = userService.getUserDetail((String)session.getAttribute("id"));
		if (!passwordEncoder.matches(pw,user.getPw())) {
			model.addAttribute("msgMod", "현재 비밀번호가 틀렸습니다.");
			return "modify";
			
		} else if (pw.equals(pw2)) {
			model.addAttribute("msgMod", "현재 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
			return "modify";
		}
		else {
			user.setPw(passwordEncoder.encode(pw2));
			userService.updateUserDetail(user);
			model.addAttribute("msgMod", "비밀번호 변경이 완료되었습니다.");
			return "main";
		}
	}
	
<<<<<<< HEAD
	/**
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@PostMapping("/findId_proc")
	public String findUserId(String email, Model model) {
		UserDTO params = userService.findLoginId(email);
		System.out.println("================================");
=======
	@PostMapping("/findId_proc")
	public String findUserId(String email, Model model) {
		System.out.println(email);
		UserDTO params = userService.findLoginId(email);
>>>>>>> d88569dba81f6428a1cd3729d2fc350cdcf36289
		System.out.println(params);
		if(params==null) {
			model.addAttribute("msgFindID","입력하신 이메일로 가입된 아이디가 없습니다.");
			return "/findId";
		} else {
			System.out.println("********************");
			System.out.println(params.getId());
			model.addAttribute("msgFindID", "입력하신 이메일로 가입된 아이디는 "+ params.getId()+"입니다.");
			return "/login";
		}
	}
	
<<<<<<< HEAD
	/**
	 * 
	 * @param id
	 * @param pwHint
	 * @param model
	 * @return
	 */
=======
>>>>>>> d88569dba81f6428a1cd3729d2fc350cdcf36289
	@PostMapping("/findPw_proc")
	public String findUserPw(String id, String pwHint, Model model) {
		UserDTO params = userService.findLoginPw(id, pwHint);
		if(params==null) {
<<<<<<< HEAD
			model.addAttribute("msgFindPW","정보를 잘못입력하셨습니다.");
		} else 
			model.addAttribute("msgFindPW",params.getId()+"님의 비밀번호는 "+ params.getPw()+"입니다.");
=======
			model.addAttribute("msgFindPW","아이디 또는 비밀번호 힌트를 잘못 입력하셨습니다.");
			return "/findPw";
		} else {
			model.addAttribute("msgFindPW",params.getId()+"님의 비밀번호는 "+passwordEncoder.upgradeEncoding(params.getPw())+"입니다.");
>>>>>>> d88569dba81f6428a1cd3729d2fc350cdcf36289
			return "/login";
		}
	}
}
