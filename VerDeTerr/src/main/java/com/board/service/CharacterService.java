package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.CharacterDTO;

@Service
public interface CharacterService {

	public int registerCharacter(CharacterDTO params);

	public CharacterDTO getCharacterDetail(Long idx);
	
	public int updateCharacter(CharacterDTO params);

	public int deleteCharacter(Long idx);

	public List<CharacterDTO> getCharacterList(String type);

}