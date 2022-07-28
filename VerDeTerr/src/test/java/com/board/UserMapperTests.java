package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.UserVo;
import com.board.mapper.UserMapper;

@SpringBootTest
public class UserMapperTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testOfLogin() {
		UserVo result = userMapper.getUserAccount("dhtmddms");
		System.out.println(result);
		if(result.getID()==null){
			System.out.println("가입되지 않은 아이디 입니다.");
		}else if(result.getPassword().equals("1234")) {
			System.out.println("로그인 성공입니다.");
		}else {
			System.out.println("로그인 실패입니다.");
		}
		
	}
}
