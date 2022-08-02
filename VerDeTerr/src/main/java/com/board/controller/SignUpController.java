package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.UserDTO;
import com.board.service.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}

	/**
	 * 
	 * @param id
	 * @param pw
	 * @param pwHint
	 * @param model
	 * @return
	 */
	@GetMapping("/signup_proc")
	public String signUpProcess(@RequestParam String id, @RequestParam(name="pw1") String pw, @RequestParam String pwHint, Model model) {
		UserDTO params = new UserDTO();
		System.out.println(id + pw + pwHint);
		params.setId(id);
		params.setPw(pw);
		params.setPwHint(pwHint);
		System.out.println("11111");
		int result = signUpService.signUp(params);
		System.out.println("2222222");
		if(result==1) {
			return "signupFinish";
		}
		else {
			model.addAttribute("msg", "회원가입 오류");
			return "signup";
		}
	}
	
}
