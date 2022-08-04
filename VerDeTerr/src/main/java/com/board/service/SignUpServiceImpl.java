package com.board.service;

import org.hibernate.internal.build.AllowSysOut;
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
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public SignUpMapper signUpMapper;

	@Override
	public int signUp(UserDTO params) {
		UserDTO userId = signUpMapper.selectUser(params.getId());
		String userPw = params.getPw();
		if (userPw.length() >= 6 || userPw.length() < 20) {
			System.out.println("비번적절");
			if (userId == null) {
				// controller에서 받아온 pw를 암호화
				String encodedPassword = passwordEncoder.encode(params.getPw());
				// 암호화된 pw로 pw를 세팅
				params.setPw(encodedPassword);
				return signUpMapper.insertUser(params);
			} else {
				System.out.println("id가 존재함.");
				return 0;
			}
		}
		System.out.println("비번 부적절");
		return 0;

	}

	@Override
	public String checkId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(UserDTO params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
