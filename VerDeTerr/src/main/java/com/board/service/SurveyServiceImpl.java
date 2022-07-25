package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.SurveyDTO;
import com.board.mapper.SurveyMapper;


@Service
public class SurveyServiceImpl implements SurveyService {
	
	@Autowired
	private SurveyMapper surveyMapper;

	@Override
	public boolean registerSurvey(SurveyDTO params) {
		int queryResult = 0;

		if (params.getId() == null) {
			queryResult = surveyMapper.insertSurvey(params);
		} else {
			queryResult = surveyMapper.updateSurvey(params);
		}

		return (queryResult == 1) ? true : false;
	}
	
	

}
