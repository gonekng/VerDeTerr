package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/access_denied")
	public String accessDenied() {
		return "access_denied";
	}

	@GetMapping("/user_access")
	public String userAccess() {
		System.out.println("user_access 컨트롤러 호출됨");
		return "user_access";
	}

	/**
	 * 
	 * @param id
	 * @param pw
	 * @param model
	 * @return
	 */
	@GetMapping("/login_proc")
	public String loginProcess(@RequestParam String id, @RequestParam String pw, Model model) {
		UserDTO params = userService.loginCheck(id, pw);
		if (params == null) {
			model.addAttribute("msgLogin", "아이디 혹은 비밀번호 오류");
			return "/login";

		} else
			model.addAttribute("loginID", params.getId());
		return "/main";
	}

	@GetMapping(value = "/main")
	public String openMainpage(Model model) {
		return "/main";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
    @GetMapping(value = "/mypage")
    public String openMypage(@RequestParam(name="id") String ID, Model model) {
        
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
        
		model.addAttribute("id", myID);
		model.addAttribute("nick", myNickname);
		model.addAttribute("email", myEmail);
		
        List<SurveyOutputDTO> testList = userService.getUserHistory(myID);
        model.addAttribute("testList", testList);
		return "/mypage";
	}
    
    /**
     * 
     * @param id
     * @return
     */
	@GetMapping("/modify")
	public String modify(@RequestParam(name="id", defaultValue="id10") String ID, Model model) {
		model.addAttribute("id", ID);
		return "modify";
	}
    
	/**
	 * 
	 * @param id
	 * @param pw
	 * @param pw2
	 * @param model
	 * @return
	 */
	@GetMapping("/modify_proc")
	public String modifyProcess(@RequestParam(name="id", defaultValue="id10") String ID, @RequestParam String pw, @RequestParam String pw2, Model model) {
		UserDTO params = userService.getUserDetail(ID);
		if (params.getPw().equals(pw)) {
			model.addAttribute("id", params.getId());
			model.addAttribute("msgModify", "현재 비밀번호가 틀렸습니다.");
			return "/modify";
			
		} else
			userService.updateUserDetail(params);
			return "/main";
	}

}
