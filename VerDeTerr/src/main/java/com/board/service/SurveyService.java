package com.board.service;

import java.util.List;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;

public interface SurveyService {
	
	public boolean registerSurvey(SurveyDTO params);
	
	public SurveyDTO getSurveyResult(String id);
	
	public SurveyOutputDTO getSurveyOuput(String id);
	
	public List<SurveyOutputDTO> getSurveyList();


}


