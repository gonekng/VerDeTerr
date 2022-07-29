package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/write.do")
	public String openBoardWrite(Model model) {
		
		String title = "제목입니다.";
		String content = "내용입니다.";
		String writer = "강지원";

		model.addAttribute("t", title);
		model.addAttribute("c", content);
		model.addAttribute("w", writer);
		return "/write";
		
	}
	
	@GetMapping(value = "/index.do")
	public String openIndex(Model model) {
		return "/index";
	}

}