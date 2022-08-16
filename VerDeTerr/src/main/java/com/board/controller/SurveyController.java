package com.board.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpSession;

//import org.python.core.io.BufferedReader;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.service.SurveyService;
import com.board.service.UserService;

@Controller
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/survey/surveylist.do")
	public String openSurveyWrite(RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		String myID = (String) session.getAttribute("id");
		if (myID == null) {
			System.out.println("???");
			redirectAttributes.addFlashAttribute("surveyError", "로그인 후 사용 가능합니다.");
			return "redirect:/main";
		} else {
			SurveyDTO survey = surveyService.getSurveyResult(myID);
			System.out.println("survey : " + survey);
			if (survey == null) {
				survey = new SurveyDTO(myID);
			}
			UserDTO user = userService.getUserDetail(myID);
			model.addAttribute("type", user.getUserType());
			model.addAttribute("survey", survey);
			return "survey/surveylist";
		}

	}

	@GetMapping(value = "/survey/surveyresult.do")
	public String getSurveyList(Model model, HttpSession session) {

		String myID = (String) session.getAttribute("id");
		SurveyOutputDTO surveyList = surveyService.getSurveyList(myID);
		System.out.println("!!!!!!!!!!" + surveyList);
		model.addAttribute("surveyList", surveyList);
		UserDTO user = userService.getUserDetail(myID);
		userService.updateUserDetail(user);

		return "survey/surveyresult";
	}

	private static PythonInterpreter intPre;

	@PostMapping(value = "/survey/surveyresult.do")
	public String registerSurvey(final SurveyDTO params, Model model) {

		try {
			System.out.println("Controller surveyresult 시작");
			boolean isRegistered = surveyService.registerSurvey(params);
			System.out.println("Controller isRegistered 받아옴");
			System.out.println("Controller isRegistered params: " + params);
			if (isRegistered == false) {
				System.out.println("정답등록실패");
			}
			System.out.println(params.getId());
			String convertID = params.getId();

			System.setProperty("python.import.site", "false");
			PythonInterpreter intPre = new PythonInterpreter();

			System.out.println(convertID.getClass().getName());

			String t = "src/main/python/python_batch.bat".concat(" " + convertID);

			Process p = Runtime.getRuntime().exec(t);
			System.out.println("python finished");

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			intPre.close();
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 처리과정 문제");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("시스템 문제 발생");
		}

		return "redirect:/survey/surveyresult.do";
	}

}
