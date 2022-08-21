package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.CharacterDTO;
import com.board.service.CharacterService;
import com.board.util.UiUtils;

@Controller
public class CharacterController extends UiUtils {

	@Autowired
	private CharacterService characterService;

	@GetMapping(value = "/character/list")
	public String openCharacterList(Model model) {
		List<CharacterDTO> characterList = characterService.getCharacterList(null);
		int listCount = characterList.size();
		model.addAttribute("characterList", characterList);
		model.addAttribute("listCount", listCount);
		return "character/list";
	};

	// 캐릭터 등록 & 수정 페이지
	@GetMapping("/character/write")
	public String openCharacterWrite(@RequestParam(value="idx",required=false)Long idx, Model model, HttpSession session) {
		CharacterDTO character = new CharacterDTO();
		if(idx!=null) {
			character = characterService.getCharacterDetail(idx);
			System.out.println(character);
			if(character==null) {
				return "redirect:/character/list";
			} 
		}

		model.addAttribute("character", character);
		return "character/write";
	}

	// 신규 캐릭터 등록
	 @PostMapping("/character/register") 
	 public String saveCharacter(@RequestParam(value="idx",required=false)Long idx, CharacterDTO params) {
		 System.out.println("***** idx : " + idx + ", params : " + params);
		 if(idx==null) {
			 characterService.registerCharacter(params);
			 return "redirect:/character/list"; 
		 }
		 characterService.updateCharacter(params);

		 return "redirect:/character/list";
	  }
	 
	 // 게시글 내용 보기
	   @GetMapping(value = "/character/view")
	   public String openCharacterDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
	      if (idx == null) {
	         return "redirect:/character/list";
	      }

	      CharacterDTO character = characterService.getCharacterDetail(idx);
	      if (character == null) {
	       
	         return "redirect:/character/list";
	      }
	      System.out.println("character.idx:"+character.getIdx());
	      model.addAttribute("character", character);

	      return "character/view";
	   }
	 
	  // 게시글 삭제하기
	   @PostMapping("/character/delete")
	   public String deleteCharacterList(@RequestParam(value = "idx", required = false) Long idx) {
		   characterService.deleteCharacter(idx);
		   return "redirect:/character/list";
	   }

}
