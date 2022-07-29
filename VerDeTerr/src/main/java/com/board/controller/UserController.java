package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.domain.UserDTO;
import com.board.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

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