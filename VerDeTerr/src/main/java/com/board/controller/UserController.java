package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("/login_proc")
	public String loginProcess(@RequestParam String id, @RequestParam String pw, Model model) {
		UserDTO params = userService.loginCheck(id, pw);
		if (params == null) {
			model.addAttribute("msg", "아이디 혹은 비밀번호 오류");
			return "/login";

		} else
			model.addAttribute("info", params.getId());
		return "/login_proc";
	}

	@GetMapping(value = "/main.do")
	public String openMainpage(Model model) {
		return "/main";
	}

	@GetMapping(value = "/mypage.do")
	public String openMypage(Model model, String ID) {

		UserDTO params = userService.getUserDetail(ID);
		String myID = params.getId();
		String myNickname = params.getNickname();
		String myEmail = params.getEmail();

		model.addAttribute("i", myID);
		model.addAttribute("n", myNickname);
		model.addAttribute("e", myEmail);
		return "/mypage";
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
