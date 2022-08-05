package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.UserDTO;
import com.board.mapper.SignUpMapper;
import com.board.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public SignUpMapper signUpMapper;
	UserMapper userMapper;

	@Override
	public int signUp(UserDTO params) {
		int userId = signUpMapper.selectUser(params.getId());
		String userPw = params.getPw();
		if (userPw.length() >= 6 || userPw.length() < 20) {
			System.out.println("비번적절");
			if (userId == 0) {
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
	public int checkId(String id) {
		int result = signUpMapper.selectUser(id);
		if(result==0) {
			System.out.println("중복된 아이디 없음");
			return 0;
		}else {
			System.out.println("중복된 아이디 있음");
			return result;
		}
		
	}

	@Override
	public int delete(String id) {
		return userMapper.deleteUser(id);
	}
}
