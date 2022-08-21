package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.CharacterDTO;
import com.board.mapper.CharacterMapper;

@Service
public class CharacterServiceImpl implements CharacterService{

	@Autowired
	public CharacterMapper characterMapper;
	
	@Override
	public int registerCharacter(CharacterDTO params) {
		return characterMapper.insertCharacter(params);
	};

	@Override
	public CharacterDTO getCharacterDetail(Long idx) {
		return characterMapper.selectCharacterDetail(idx);
	};
	
	@Override
	public int updateCharacter(CharacterDTO params) {
		return characterMapper.updateCharacter(params);
	};

	@Override
	public int deleteCharacter(Long idx) {
		return characterMapper.deleteCharacter(idx);
	};

	@Override
	public List<CharacterDTO> getCharacterList(String type){
		List<CharacterDTO> characterList = Collections.emptyList();

		int characterCount = characterMapper.selectCharacterTotalCount(type);
		if(characterCount > 0) {
			characterList = characterMapper.selectCharacterList(type);
		}
		return characterList;
	};
}
