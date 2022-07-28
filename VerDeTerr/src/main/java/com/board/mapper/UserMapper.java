package com.board.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserVo;

@Mapper
public interface UserMapper {
    // 로그인

	public UserVo getUserAccount(String ID);


}