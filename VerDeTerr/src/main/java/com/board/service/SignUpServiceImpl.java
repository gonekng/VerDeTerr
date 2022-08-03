package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.UserDTO;
import com.board.mapper.SignUpMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpServiceImpl implements SignUpService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SignUpMapper signUpMapper;
	
	@Override
	public int signUp(UserDTO params) {
		UserDTO user = signUpMapper.selectUser(params.getId());
		if(user==null) {
			// controller에서 받아온 pw를 암호화
			String encodedPassword = passwordEncoder.encode(params.getPw());
			// 암호화된 pw로 pw를 세팅
			params.setPw(encodedPassword);
			return signUpMapper.insertUser(params);
		}else {
			System.out.println("id가 존재함.");
			return 0;
		}
			

	}

}
