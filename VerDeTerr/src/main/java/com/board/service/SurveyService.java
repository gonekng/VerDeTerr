package com.board.service;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;

public interface SurveyService {
	
	public boolean registerSurvey(SurveyDTO params);
	
	public SurveyDTO getSurveyResult(String id);
	
	public SurveyOutputDTO getSurveyOuput(String id);
	
	public SurveyOutputDTO getSurveyList(String id);


}


