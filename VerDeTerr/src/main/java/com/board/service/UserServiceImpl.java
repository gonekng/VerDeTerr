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
	public UserMapper userMapper;

	@Override
	public UserDTO loginCheck(String id, String pw) {
		UserDTO loginMember = userMapper.selectUserDetail(id);
		System.out.println(userMapper.selectUserDetail(id));
		System.out.println(loginMember);
		if (loginMember == null) {
			return null;
		} else if (!pw.equals(loginMember.getPw())) {
			System.out.println(loginMember.toString());
			return null;
		} else {
			return loginMember;
		}
	}

	@Override
	public UserDTO getUserDetail(String ID) {
		return userMapper.selectUserDetail(ID);
	}
	
    @Override
    public List<SurveyOutputDTO> getUserHistory(String ID) {
        return userMapper.selectUserHistory(ID);
    }

	@Override
	public int updateUserDetail(UserDTO params) {
		return userMapper.updateUser(params);
	}

}