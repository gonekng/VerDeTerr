package com.board.service;

import java.util.List;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;

public interface UserService {

	public String loginCheck(String ID, String PW);
	
	public UserDTO getUserDetail(String ID);
	
	public List<SurveyOutputDTO> getUserHistory(String ID);

}