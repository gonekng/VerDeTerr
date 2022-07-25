package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.SurveyDTO;

@Mapper
public interface SurveyMapper {
	
	public int insertSurvey(SurveyDTO params);

	public int updateSurvey(SurveyDTO params);

}
