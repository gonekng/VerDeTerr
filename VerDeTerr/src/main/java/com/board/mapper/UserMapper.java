package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserDTO;

@Mapper
public interface UserMapper {

	public int insertUser(UserDTO params);

	public UserDTO selectUserDetail(String id);

	public int updateUser(UserDTO params);

	public int deleteUser(String id);
	
}
