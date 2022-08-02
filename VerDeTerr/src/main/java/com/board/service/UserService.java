package com.board.service;

import org.springframework.stereotype.Service;

import com.board.domain.UserDTO;

@Service
public interface UserService {

	public UserDTO loginCheck(String id, String pw);

	public UserDTO getUserDetail(String ID);
	
	public UserDTO findLoginId(String Email);

	public UserDTO findLoginPw(String id,String PwHint);

}
