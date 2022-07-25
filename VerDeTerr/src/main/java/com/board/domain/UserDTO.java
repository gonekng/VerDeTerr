package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	/** 아이디 (PK) */
	private String id;

	/** 비밀번호 */
	private String pw;

	/** 비밀번호힌트 */
	private String pwConfirm;

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

}
