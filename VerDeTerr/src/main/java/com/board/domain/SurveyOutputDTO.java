package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyOutputDTO {

	@Override
	public String toString() {
		return "SurveyOutputDTO [idx=" + idx + ", id=" + id + ", testdate=" + testdate + ", useranswer=" + useranswer
				+ ", usertype=" + usertype + "]";
	}

	/**MBTI_ML_output Table*/
	/** IDX (PK)*/
	private int idx;
	
	private String id;
	
	private LocalDateTime testdate;
	
	private String useranswer;
	
	private String usertype;

}
