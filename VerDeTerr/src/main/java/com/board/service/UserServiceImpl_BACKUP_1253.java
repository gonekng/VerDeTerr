package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserMapper userMapper;

	@Override
	public UserDTO loginCheck(String id, String pw) {
		UserDTO loginMember = userMapper.selectUserDetail(id);
<<<<<<< HEAD

=======
>>>>>>> 8971b040f8ce65fb6b3f49b2c7a831ca556e58b8
		if (loginMember == null) {
			return null;
		} else if (!pw.equals(loginMember.getPw())) {
			return null;
		} else {
			return loginMember;
		}
	}

	@Override
	public UserDTO getUserDetail(String ID) {
		return userMapper.selectUserDetail(ID);
	}
	
    @Override
    public List<SurveyOutputDTO> getUserHistory(String ID) {
        return userMapper.selectUserHistory(ID);
    }

	@Override
	public int updateUserDetail(UserDTO params) {
		return userMapper.updateUser(params);
	}

	@Override
	public UserDTO findLoginId(String Email) {
		UserDTO findUserEmail = userMapper.findId(Email);
		if (findUserEmail==null) {
			return null;
		} else {
			return findUserEmail;
		}
	}
	
	@Override
	public UserDTO findLoginPw(String Id, String PwHint) {
		UserDTO selectId = userMapper.selectUserDetail(Id);
		if(selectId == null) {
			return null;
		} else if (!PwHint.equals(selectId.getPwHint())) {
			return null;
		} else {
			return selectId;
		}
	}
}