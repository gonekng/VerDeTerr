package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeDTO {

	/**MBTI_Type Table*/
	
	private String userType;
	
	private String category;

	private String feature;

	private String job;
	
	private String goodMatch;

	private String badMatch;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGoodMatch() {
		return goodMatch;
	}

	public void setGoodMatch(String goodMatch) {
		this.goodMatch = goodMatch;
	}

	public String getBadMatch() {
		return badMatch;
	}

	public void setBadMatch(String badMatch) {
		this.badMatch = badMatch;
	}

	@Override
	public String toString() {
		return "TypeDTO [userType=" + userType + ", category=" + category + ", feature=" + feature + ", job=" + job
				+ ", goodMatch=" + goodMatch + ", badMatch=" + badMatch + "]";
	}
	

}
