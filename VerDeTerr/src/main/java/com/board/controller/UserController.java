package com.board.controller;

import com.board.domain.UserVo;
import com.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public String root() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

//    /**
//     * 회원가입 폼
//     * @return
//     */
//    @GetMapping("/signUp")
//    public String signUpForm() {
//        return "signUp";
//    }
	/**
	 * 로그인 실패 폼
	 * 
	 * @return
	 */
	@GetMapping("/access_denied")
	public String accessDenied() {
		return "access_denied";
	}

//    /**
//     * 회원가입 진행
//     * @param userVo
//     * @return
//     */
//    @PostMapping("/signUp")
//    public String signUp(UserVo userVo) {
//        userService.joinUser(userVo);
//        return "redirect:/login";
//    }

	
//	  유저 페이지
//	 * 
//	 @Param model
//	 @param authentication
//	 @return
	
	@GetMapping("/user_access")
	public String userAccess(Model model, Authentication authentication) {
		// Authentication 객체를 통해 유저 정보를 가져올 수 있다.
		System.out.println("모델 : " + model.toString());
		System.out.println("오뗀 : " + authentication.toString());
		UserVo userVo = (UserVo) authentication.getPrincipal(); // userDetail 객체를 가져옴
		model.addAttribute("info", userVo.getUsername() + "의 " + userVo.getUserNickName() + "님"); // 유저 아이디
		return "user_access";
	}

}
