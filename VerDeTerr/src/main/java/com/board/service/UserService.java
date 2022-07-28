package com.board.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.board.domain.UserVo;
import com.board.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserMapper userMapper;

    @Override
    public UserVo loadUserByUsername(String ID) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        UserVo userVo = userMapper.getUserAccount(ID);
        if (userVo== null) {
        	throw new UsernameNotFoundException("Not Found account.");
        }else {
        return userVo;
        }
    }
}
