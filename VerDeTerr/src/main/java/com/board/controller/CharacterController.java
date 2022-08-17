package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.domain.CharacterDTO;
import com.board.service.CharacterService;
import com.board.util.UiUtils;

@Controller
public class CharacterController extends UiUtils {

	@Autowired
	private CharacterService characterService;

	@GetMapping(value="/character/list")
	public String openCharacterList(Model model) {
	      List<CharacterDTO> characterList = characterService.getCharacterList(null);
	      int listCount = characterList.size();
	      model.addAttribute("characterList", characterList);
	      model.addAttribute("listCount", listCount);
	      return "character/list";
	};

}
