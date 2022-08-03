package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.UserDTO;
import com.board.mapper.SignUpMapper;
import com.board.service.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;
	private SignUpMapper signUpMapper;

	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}

	@PostMapping("/signup_proc")
	public String signUpProcess(UserDTO params,  Model model) {
		
		System.out.println(params);
		System.out.println("11111");
		int result = signUpService.signUp(params);
		System.out.println("2222222");
		if (result == 1) {
			return "main";
		} else {
			model.addAttribute("msg", "회원가입 오류");
			return "signup";
		}
	}
}
