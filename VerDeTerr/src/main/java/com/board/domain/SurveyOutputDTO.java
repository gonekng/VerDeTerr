package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyOutputDTO {
	
	@Override
	public String toString() {
		return "SurveyOutputDTO [id=" + id + ", useranswer=" + useranswer + ", usertype=" + usertype + "]";
	}

	/**MBTI_ML_output Table*/
	/** ID (PK)*/
	private String id;
	
	private String useranswer;
	
	private String usertype;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getUseranswer() {
		return useranswer;
	}

	public void setUseranswer(String useranswer) {
		this.useranswer = useranswer;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	

}
