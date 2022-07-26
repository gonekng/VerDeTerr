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
		System.out.println("ServiceImpl 시작");
		int queryResult = 0;
		System.out.println("ServiceImpl queryResult 받아옴");
		if (params.getId() != null) {
			System.out.println("ServiceImpl getID가 null이 아닌경우");
			System.out.println("ServiceImpl Mapper 실행전: "+params);
			queryResult = surveyMapper.insertSurvey(params);
			System.out.println("ServiceImpl Mapper 실행후");
			
		} else {
			//멤버 테이블 만들어지면 거기서 데이터 유효검사 한 후에 업데이트 쿼리 실행하기
			System.out.println("ServiceImpl getID가 null인경우");
			queryResult = surveyMapper.insertSurvey(params);
//			queryResult = surveyMapper.updateSurvey(params);

		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public SurveyDTO getSurveyResult(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
