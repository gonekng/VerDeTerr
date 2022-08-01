package com.board.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;

@Service
public class UserServiceimpl implements UserService{
	
	@Autowired
	public UserMapper userMapper;
	
	@Override
	public UserDTO loginCheck(String id, String pw) {
		UserDTO loginMember = userMapper.selectUserDetail(id);
		System.out.println(userMapper.selectUserDetail(id));
		System.out.println(loginMember);
		if (loginMember == null) {
			return null;
		}
		else if(!pw.equals(loginMember.getPw())) {
			System.out.println(loginMember.toString());
			return null;
		}
		else{
			return loginMember;
		} 
	}
}
