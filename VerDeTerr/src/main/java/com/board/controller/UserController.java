package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping(value = "/main")
	public String openMainpage(Model model) {
		return "/main";
	}
	
	/**
	 * 
	 * @param ID
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/mypage")
	public String openMypage(@RequestParam(name="id", defaultValue="id10") String ID, Model model) {
		
		UserDTO params = new UserDTO();
		String myID = "";
		String myNickname = "";
		String myEmail = "";
		
		if(ID!=null) {
			params = userService.getUserDetail(ID);
			myID = params.getId();
			myNickname = params.getNickname();
			myEmail = params.getEmail();
		}

		model.addAttribute("i", myID);
		model.addAttribute("n", myNickname);
		model.addAttribute("e", myEmail);
		
		List<SurveyOutputDTO> testList = userService.getUserHistory(myID);
		model.addAttribute("testList", testList);

		return "/mypage";
	}
}