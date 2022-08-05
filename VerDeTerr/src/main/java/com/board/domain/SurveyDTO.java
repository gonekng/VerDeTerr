package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyDTO {
	
	/** IDX (PK) */
	private Long idx;
	
	/** ID */
	private String id;
	
	/** Answer */
	private String answer1;	private String answer2;	private String answer3;	private String answer4;
	private String answer5;	private String answer6;	private String answer7;	private String answer8;
	private String answer9;	private String answer10;	private String answer11;	private String answer12;
	private String answer13;	private String answer14;	private String answer15;	private String answer16;
	
	/** Constructor */
	public SurveyDTO() {
		super();
	}
	
	public SurveyDTO(String id) {
		super();
		this.id = id;
		this.answer1 = "";		this.answer2 = "";		this.answer3 = "";		this.answer4 = "";
		this.answer5 = "";		this.answer6 = "";		this.answer7 = "";		this.answer8 = "";
		this.answer9 = "";		this.answer10 = "";		this.answer11 = "";		this.answer12 = "";
		this.answer13 = "";		this.answer14 = "";		this.answer15 = "";		this.answer16 = "";
	}
	
	/** toString */
	@Override
	public String toString() {
		return "SurveyDTO [idx=" + idx + ", id=" + id + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3="
				+ answer3 + ", answer4=" + answer4 + ", answer5=" + answer5 + ", answer6=" + answer6 + ", answer7="
				+ answer7 + ", answer8=" + answer8 + ", answer9=" + answer9 + ", answer10=" + answer10 + ", answer11="
				+ answer11 + ", answer12=" + answer12 + ", answer13=" + answer13 + ", answer14=" + answer14
				+ ", answer15=" + answer15 + ", answer16=" + answer16 + "]";
	}
	
}