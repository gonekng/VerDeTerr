package com.board.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

//import org.python.core.io.BufferedReader;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
//	@GetMapping(value = "/survey/surveylist.do")
//	public String openSurveyWrite(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
//		String myID = (String) session.getAttribute("id");
//		if(id == null){
//			model.addAttribute("survey",  new SurveyDTO());
//		} else {
//			SurveyDTO survey = surveyService.getSurveyResult(id);
//			System.out.println("survey:"+survey);
//			if (survey == null) {
//				return "redirect:/survey/surveylist.do";
//			}
//			model.addAttribute("survey", survey);
//			
//			
//		}
//		
//		return "survey/surveylist";
//	}
	
	@GetMapping(value = "/survey/surveylist.do")
	public String openSurveyWrite(Model model, HttpSession session) {
		String myID = (String) session.getAttribute("id");
		if(myID == null){
			System.out.println("???");
			model.addAttribute("surveyError", "로그인 후 사용 가능합니다.");
			return "redirect:/main";
		} else {
			SurveyDTO survey = surveyService.getSurveyResult(myID);
			System.out.println("survey : "+survey);
			if(survey == null) {
				survey = new SurveyDTO(myID);
			}
			model.addAttribute("survey", survey);
			return "survey/surveylist";
		}
		
	}
	
	
//	@GetMapping(value = "/survey/surveylist.do")
//	public String openSurveyWrite(Model model) {
//		System.out.println("controller");
//		return "/survey/surveylist";
//	}
	
//	@GetMapping(value="/survey/surveyresult.do")
//	public String getSurveyOutput(@RequestParam(value = "id", required = false) String id, Model model) {
//		if(id == null) {
//			model.addAttribute("survey", new SurveyOutputDTO());
//		} else {
//			SurveyOutputDTO survey = surveyService.getSurveyOuput(id);
//			if (survey == null) {
//				return "redirect:/survey/surveylist.do";
//			}
//			model.addAttribute("id", id);
//		}
//		
//		
//		return "survey/surveyresult";
//	}
	
//	@GetMapping(value="/survey/surveyresult.do")
//	public String getSurveyOutput(Model model) {
//		String id = "mjmjmj";
//		
//		model.addAttribute("id", id);
//		
//		return "survey/surveyresult";
//	}
	
	@GetMapping(value="/survey/surveyresult.do")
	public String getSurveyList(Model model, HttpSession session) {
		
		String myID = (String)session.getAttribute("id");
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
			System.out.println("Controller isRegistered params: "+ params);
			if (isRegistered == false) {
				// TODO => 등록에 실패하였다는 메시지를 전달
				System.out.println("정답등록실패");
			}
			System.out.println(params.getId());
			String convertID = params.getId();
			
			String url = "http://127.0.0.1:5007/hello";
			String sb = "";
		
			
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line = null;

			while ((line = br.readLine()) != null) {
				sb = sb + line + "\n";
				
			}
			System.out.println(sb);
			System.out.println("========br======" + sb.toString());
			if (sb.toString().contains("ok")) {
				System.out.println("test");
				System.out.println("userid");
				
			}
			br.close();

			System.out.println("" + sb.toString());
			
//			Process p = Runtime.getRuntime().exec("src/main/python/test.bat");
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		    String line = null;
//		    
//		    while ((line = br.readLine()) != null) {
//		      System.out.println(line);
//		    }


			
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 처리과정 문제");

		}  catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			e.printStackTrace();
			System.out.println("시스템 문제 발생");
		} 
		
		return "redirect:/survey/surveyresult.do";
	}
	
	
	
	
}
