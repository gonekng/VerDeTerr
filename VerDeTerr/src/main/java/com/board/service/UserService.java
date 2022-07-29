package com.board.service;

import com.board.domain.UserDTO;

public interface UserService {

	public String loginCheck(String ID, String PW);
	
	public UserDTO getUserDetail(String ID);

}