package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.domain.SurveyDTO;
import com.board.service.SurveyService;

@Controller
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;

	@GetMapping(value = "/survey/surveylist.do")
	public String openSurveyWrite(Model model) {
		String id = "유저아이디1";
		String userType = "유저타입1";
		String answer = "유저정답1";
		
		model.addAttribute("jmj", id);
		model.addAttribute("INTJ", userType);
		model.addAttribute("typeintj", answer);
		
		return "survey/surveylist";
	}
	
}
