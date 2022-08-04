package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserDTO;

@Mapper
public interface SignUpMapper {

	public int insertUser(UserDTO params);
	
	public int selectUser(String id);
		
}
