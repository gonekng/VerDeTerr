package com.board.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpSession;

//import org.python.core.io.BufferedReader;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.service.SurveyService;

@Controller
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
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
			model.addAttribute("surveyError", "로그인 후 사용 가능합니다.");
			return "redirect:/main";
		} else {
			SurveyDTO survey = surveyService.getSurveyResult(myID);
			System.out.println("survey:"+survey);
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
	public String getSurveyList(Model model) {
		
		List<SurveyOutputDTO> surveyList = surveyService.getSurveyList();
		model.addAttribute("surveyList", surveyList);

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
			
//			PythonInterpreter intPre = new PythonInterpreter();
//			
//			intPre.execfile("src/main/test.py");
//			intPre.exec("print('test')");
//			
			
			
			
			
//			String convertID = params.getId();
			System.out.println(convertID.getClass().getName());
			String z1= " "+convertID;
			String z2 = "src/main/python/test.bat".concat(z1);
			System.out.println(z2);
		    Process p = Runtime.getRuntime().exec(z2);
		    System.out.println("python finished");
		    
//		    ProcessBuilder builder = new ProcessBuilder((List<String>) new InputStreamReader(p.getInputStream()));
//	        builder.start();
		    
		    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = null;
		    
		    while ((line = br.readLine()) != null) {
		      System.out.println(line);}
			
//		      ArrayList<String> list = new ArrayList<>();
//		      list.add("python");
//		      String convertID = params.getId();
//		      String z1= convertID;
//		      list.add("src/main/python/test2.py");
//		      list.add(z1);
//		      ProcessBuilder pb = new ProcessBuilder(list);
//		      Process p = pb.start();
//		      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		      BufferedReader readerError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//		      String line = null;
//		      while ( (line = reader.readLine()) != null) {
//		        System.out.println(line);
//		      }

//			System.out.println(convertID.getClass().getName());
//			String z1= " "+convertID;
//			String z2 = "src/main/python/test.bat".concat(z1);
//			String z2 = "src/main/python/test.bat";
////			System.out.println(z2);
//		    Process p = Runtime.getRuntime().exec(z2);
//		    System.out.println("python finished");
//		    
////		    ProcessBuilder builder = new ProcessBuilder((List<String>) new InputStreamReader(p.getInputStream()));
////	        builder.start();
//		    
//		    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		    String line = null;
//		    
//		    while ((line = br.readLine()) != null) {
//		      System.out.println(line);}
//			
//			String url = "http://127.0.0.1:5000/tospring";
//			String sb = "";
//			
//			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//			
//
//			BufferedReader br2 = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//
//			String line2 = null;
//
//			while ((line2 = br2.readLine()) != null) {
//				sb = sb + line2 + "\n";
//			}
//			System.out.println("========br======" + sb.toString());
//			if (sb.toString().contains("ok")) {
//				System.out.println("test");
//				
//			}
//			br.close();
//
//			System.out.println("" + sb.toString());
			
			
			
			
			
			
			
			
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
