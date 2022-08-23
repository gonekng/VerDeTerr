package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.domain.CharacterDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.TypeDTO;
import com.board.domain.UserDTO;
import com.board.service.CharacterService;
import com.board.service.SurveyService;
import com.board.service.UserService;

@Controller
public class SurveyResultController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private UserService userService;
	
	@Autowired	
	private CharacterService characterService;
	
	@GetMapping(value = "/survey/surveyresult")
	public String getSurveyList(Model model, HttpSession session) {

		String myID = (String) session.getAttribute("id");
		SurveyOutputDTO surveyList = surveyService.getSurveyList(myID);
		System.out.println("***********" + surveyList);
		model.addAttribute("surveyList", surveyList);
		
		UserDTO user = userService.getUserDetail(myID);
		System.out.println("***********" + user);
		if(user!=null) {

			userService.updateUserDetail(user);
			user = userService.getUserDetail(myID);
			System.out.println("***********" + user);
			
			String myType = user.getUserType();
			TypeDTO typeInfo = surveyService.getTypeInfo(user.getUserType());
			System.out.println("***********" + typeInfo);
			model.addAttribute("typeInfo", typeInfo);

			List<CharacterDTO> characterList = characterService.getCharacterList(myType);
			model.addAttribute("character", characterList);
			System.out.println("***********" + characterList);
			
		}
		return "survey/surveyresult";
	}	
}
