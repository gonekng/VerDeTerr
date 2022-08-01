package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserDTO;

@Mapper
public interface UserMapper {

	public int insertUser(UserDTO params);

	public UserDTO selectUserDetail(String id);

	public int updateUser(UserDTO params);

	public int deleteUser(String id);
	
<<<<<<< HEAD
}
=======
	public List<String> selectUserHistory(String id);

	public int selectUserHistoryCount(String id);
	
}
>>>>>>> parent of 66a5053 (메인페이지, 마이페이지 구현 및 UserService, UserServiceImpl, UserController 추가)
