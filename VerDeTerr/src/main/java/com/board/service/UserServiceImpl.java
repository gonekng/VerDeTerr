package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public String loginCheck(String ID, String PW) {
		return null;
	}
	
	@Override
	public UserDTO getUserDetail(String ID) {
		return userMapper.selectUserDetail(ID);
	}
	
	@Override
	public List<SurveyOutputDTO> getUserHistory(String ID) {
		return userMapper.selectUserHistory(ID);
	}
	
}