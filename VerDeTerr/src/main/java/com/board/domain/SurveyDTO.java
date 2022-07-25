package com.board.domain;

public class SurveyDTO {
	
	@Override
	public String toString() {
		return "SurveyDTO [id=" + id + ", userType=" + userType + ", answer=" + answer + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/** ID (PK)*/
	private String id;
	
	/** USER TYPE*/
	private String userType;
	
	/** Answer*/
	private String answer;
	
    
}
