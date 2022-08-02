package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 
	 * @param id
	 * @param pw
	 * @param model
	 * @return
	 */
	@GetMapping("/login_proc")
	public String loginProcess(HttpServletRequest request, @RequestParam String id, @RequestParam String pw, Model model) {
		HttpSession session = request.getSession(true);
		UserDTO params = userService.loginCheck(id, pw);
		if (params == null) {
			model.addAttribute("msgLogin", "아이디 혹은 비밀번호 오류");
			return "login";

		} else {
			session.setAttribute("id", params.getId());
		}
		return "main";
	}

	@GetMapping(value = "/main")
	public String openMainpage(Model model) {
		return "main";
	}
	
    @GetMapping(value = "/mypage")
    public String openMypage(HttpSession session, Model model) {
        
        UserDTO params = new UserDTO();
        String myID = (String) session.getAttribute("id");
        String myNickname = "";
        String myEmail = "";
        
        if(myID!=null) {
            params = userService.getUserDetail(myID);
            myNickname = params.getNickname();
            myEmail = params.getEmail();
        }
        
		model.addAttribute("nick", myNickname);
		model.addAttribute("email", myEmail);
		
        List<SurveyOutputDTO> testList = userService.getUserHistory(myID);
        model.addAttribute("testList", testList);
		return "mypage";
	}
    
	@GetMapping("/modify")
	public String modify(Model model) {
		return "modify";
	}
    
	/**
	 * 
	 * @param pw
	 * @param pw2
	 * @param model
	 * @return
	 */
	@GetMapping("/modify_proc")
	public String modifyProcess(HttpSession session, @RequestParam String pw, @RequestParam String pw2, Model model) {
		UserDTO params = userService.getUserDetail((String)session.getAttribute("id"));
		if (!params.getPw().equals(pw)) {
			model.addAttribute("msgModify1", "현재 비밀번호가 틀렸습니다.");
			return "modify";
			
		} else if (pw.equals(pw2)) {
			model.addAttribute("msgModify2", "현재 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
			return "modify";
		}
		else {
			params.setPw(pw2);
			userService.updateUserDetail(params);
			model.addAttribute("msgModify3", "비밀번호 변경이 완료되었습니다.");
			return "main";
		}
	}

}
