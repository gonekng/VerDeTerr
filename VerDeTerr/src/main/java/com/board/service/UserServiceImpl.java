package com.board.service;

<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

>>>>>>> parent of 66a5053 (메인페이지, 마이페이지 구현 및 UserService, UserServiceImpl, UserController 추가)
import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;

@Service
<<<<<<< HEAD
public class UserServiceImpl implements UserService{
   
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
=======
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
	
>>>>>>> parent of 66a5053 (메인페이지, 마이페이지 구현 및 UserService, UserServiceImpl, UserController 추가)
}