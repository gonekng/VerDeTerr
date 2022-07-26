package com.board.service;

import com.board.domain.SurveyDTO;

public interface SurveyService {
	
	public boolean registerSurvey(SurveyDTO params);
	
	public SurveyDTO getSurveyResult(String id);

}


