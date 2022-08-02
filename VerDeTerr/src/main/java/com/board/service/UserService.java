package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;

@Service
public interface UserService {

	public UserDTO loginCheck(String id, String pw);

	public UserDTO getUserDetail(String ID);
	
    public List<SurveyOutputDTO> getUserHistory(String ID);

	public int updateUserDetail(UserDTO params);

}
