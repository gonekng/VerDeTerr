package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserDTO;

@Mapper
public interface UserMapper {

	public int insertUser(UserDTO params);

	public UserDTO selectUserDetail(String id);

	public int updateUser(UserDTO params);

	public int deleteUser(String id);

	public List<String> selectUserHistory(String id);

	public int selectUserHistoryCount(String id);

}