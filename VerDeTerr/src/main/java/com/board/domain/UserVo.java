package com.board.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVo implements UserDetails{

	private String ID;
	private String PW;
	private String PWConfirm;
	private String Email;
	private String NickName;
	private String Age;
	private String Gender;
	private String UserType;
	private Boolean ManagerYn;
	private LocalDateTime RegDate;


    @Override
    public String getPassword() {
        return this.PW;
    }

    // 시큐리티의 userName
    // -> 따라서 얘는 인증할 때 id를 봄
    public String getUsername() {
        return this.ID;
    }

//    // Vo의 userName !
    public String getUserNickName(){
        return this.NickName;
    }


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
    public Collection <? extends GrantedAuthority > getAuthorities() {
		Collection < GrantedAuthority > collectors = new ArrayList<>();
		collectors.add(() -> {
			return "계정별 등록할 권한";
		});
        return collectors;
    }




}
