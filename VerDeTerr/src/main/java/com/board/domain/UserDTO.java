package com.board.domain;

import java.time.LocalDateTime;


public class UserDTO {
 //implements UserDetails 

	/** 아이디 (PK) */
	private String id;
	
	/** 비밀번호 */
	private String pw;

	/** 비밀번호힌트 */
	private String pwHint;


	/** 이메일 */
	private String email;

	/** 닉네임 */
	private String nickname;

	/** 나이 */
	private int age;

	/** 성별 */
	private String gender;

	/** 유형 */
	private String userType;

	/** 관리자여부 */
	private boolean managerYn;

	/** 가입일자 */
	private LocalDateTime regDate;

<<<<<<< HEAD
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPwHint() {
		return pwHint;
	}

	public void setPwHint(String pwHint) {
		this.pwHint = pwHint;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isManagerYn() {
		return managerYn;
	}

	public void setManagerYn(boolean managerYn) {
		this.managerYn = managerYn;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pw=" + pw + ", pwHint=" + pwHint + ", email=" + email + ", nickname=" + nickname
				+ ", age=" + age + ", gender=" + gender + ", userType=" + userType + ", managerYn=" + managerYn
				+ ", regDate=" + regDate + "]";
	}


//	@Override
//	public String getPassword() {
//		return this.pw;
//	}
//
//	// 시큐리티의 userName
//	// -> 따라서 얘는 인증할 때 id를 봄
//	public String getUsername() {
//		return this.id;
//	}
//
//    // Vo의 userName !
//	public String getUserNickName() {
//		return this.nickname;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(() -> {
//			return "계정별 등록할 권한";
//		});
//		return collectors;
//	}
=======
>>>>>>> parent of 66a5053 (메인페이지, 마이페이지 구현 및 UserService, UserServiceImpl, UserController 추가)
}
