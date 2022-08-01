package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/access_denied")
	public String accessDenied() {
		return "access_denied";
	}

	@GetMapping("/user_access")
	public String userAccess() {
		System.out.println("user_access 컨트롤러 호출됨");
		return "user_access";
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

}
