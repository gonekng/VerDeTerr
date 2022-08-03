package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		System.out.println(params.getId() + ", " + params.getPw());
		UserDTO user = userService.loginCheck(params.getId(), params.getPw());
		if (user == null) {
			model.addAttribute("msgLogin", "아이디 혹은 비밀번호 오류");
			return "redirect:/login";

		} else {
			session.setAttribute("id", user.getId());
		}
		return "redirect:/main";
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
	public String modifyProcess(HttpSession session, @RequestParam String pw, @RequestParam String pw2, Model model) {
		UserDTO params = userService.getUserDetail((String)session.getAttribute("id"));
		if (!params.getPw().equals(pw)) {
			model.addAttribute("msgMod", "현재 비밀번호가 틀렸습니다.");
			return "modify";
			
		} else if (pw.equals(pw2)) {
			model.addAttribute("msgMod", "현재 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
			return "modify";
		}
		else {
			params.setPw(pw2);
			userService.updateUserDetail(params);
			model.addAttribute("msgMod", "비밀번호 변경이 완료되었습니다.");
			return "main";
		}
	}
	
	/**
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@GetMapping("/findId_proc")
	public String findUserId(@RequestParam(value="email", required=false) String Email, Model model) {
		UserDTO params = userService.findLoginId(Email);
		System.out.println(params);
		if(params==null) {
			model.addAttribute("msg","입력하신 이메일로 가입된 아이디가 없습니다.");
			return "/findId";
		}else
			System.out.println("********************");
			System.out.println(params.getId());
			model.addAttribute("msg", "입력하신 이메일로 가입된 아이디는 "+ params.getId()+"입니다.");
			return "/login";
	}
	
	/**
	 * 
	 * @param id
	 * @param pwHint
	 * @param model
	 * @return
	 */
	@GetMapping("/findPw_proc")
	public String findUserPw(@RequestParam(value="id") String Id, @RequestParam(value="pwHint", required=false) String PwHint, Model model) {
		UserDTO params = userService.findLoginPw(Id, PwHint);
		if(params==null) {
			model.addAttribute("msg","정보를 잘못입력하셨습니다.");
		} else 
			model.addAttribute("msg",params.getId()+"님의 비밀번호는 "+params.getPw()+"입니다.");
			return "/login";
	}
}
