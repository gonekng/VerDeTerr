package com.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

	@PostMapping("/signup_proc")
	public String signUpProcess(HttpServletRequest request, UserDTO params,  Model model) {
		HttpSession session = request.getSession(true);
		System.out.println("컨트롤러에 넘어온 파람스 : "+params);
		String myID = params.getId();
		String myPW = params.getPw();
		String myHint = params.getPwHint();
		int result = 0;
		if(myID!="" && myPW!="" && myHint!="") {
			result = signUpService.signUp(params);
		}

		if (result == 1) {
			session.setAttribute("id", myID);
			model.addAttribute("msgSignupSuccess", "회원가입 되었습니다. " + myID + "님, 환영합니다!");
		} else {
			model.addAttribute("msgSignupError", "회원가입 오류");
		}
		return "main";
	}
}
