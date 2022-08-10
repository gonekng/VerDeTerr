package com.board.service;

import org.springframework.stereotype.Service;

import com.board.domain.UserDTO;


@Service
public interface SignUpService {

	public int signUp(UserDTO params);
	
	public int checkId(String id);
	
	public int checkEmail(String email);
	
	public int delete(String id);
	
}
