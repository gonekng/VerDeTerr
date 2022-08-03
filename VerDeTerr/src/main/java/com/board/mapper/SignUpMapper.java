package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserDTO;

@Mapper
public interface SignUpMapper {

	public int insertUser(UserDTO params);
	
	public UserDTO selectUser(String id);
		
}
