package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.SurveyDTO;
import com.board.service.SurveyService;

@Controller
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;

	@GetMapping(value = "/survey/surveylist.do")
	public String openSurveyWrite(@RequestParam(value = "id", required = false) String id, Model model) {
		if(id == null){
			model.addAttribute("survey", new SurveyDTO());
		} else {
			SurveyDTO survey = surveyService.getSurveyResult(id);
			if (survey == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("survey", survey);
		}
		
		return "survey/surveylist";
	}
	
	@GetMapping(value="/survey/surveyresult.do")
	public String openSurvey() {
		
		return "survey/surveyresult";
	}
	
	@PostMapping(value = "/survey/surveyresult.do")
	public String registerSurvey(final SurveyDTO params) {
		
		try {
			System.out.println("Controller surveyresult 시작");
			boolean isRegistered = surveyService.registerSurvey(params);
			System.out.println("Controller isRegistered 받아옴");
			System.out.println("Controller isRegistered params: "+ params);
			if (isRegistered == false) {
				// TODO => 등록에 실패하였다는 메시지를 전달
				System.out.println("정답등록실패");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 처리과정 문제");

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("시스템 문제 발생");}
		
		return "redirect:/survey/surveyresult.do";
	}
	
}
