package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.UserDTO;
import com.board.mapper.SignUpMapper;

@Service
public class SignUpServiceImpl implements SignUpService{
	
	
	@Autowired
	public SignUpMapper signUpMapper;
	
	@Override
	public int signUp(UserDTO params) {
		System.out.println(params.toString());
		UserDTO user = signUpMapper.selectUser(params.getId());
		if(user==null) {
			System.out.println("id가 없음.");
			System.out.println("회원가입완료");
			return signUpMapper.insertUser(params);
		}else {
			System.out.println("id가 존재함.");
			return 0;
		}
			
	}

}
