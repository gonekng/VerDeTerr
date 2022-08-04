package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;

@Mapper
public interface SurveyMapper {
	
	public int insertSurvey(SurveyDTO params);

	public int updateSurvey(SurveyDTO params);
	
	public SurveyOutputDTO selectSurveyOutput(String id);
	
	public SurveyOutputDTO selectSurveyList(String id);
	
}
