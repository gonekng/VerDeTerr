package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardCriteria;
import com.board.domain.BoardDTO;
import com.board.domain.memberDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		
		if (params.getIdx() == null) {
			
			System.out.println("여기까지 들어옵?");
			memberDTO member=new memberDTO();
			member.setNickname("버즈우주선형");
			
			queryResult = boardMapper.insertBoard(params);
			System.out.println(queryResult);
			
		} else {
			queryResult = boardMapper.updateBoard(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;

		BoardDTO board = boardMapper.selectBoardDetail(idx);
		System.out.println(board);
		System.out.println();
		if (board != null && board.getDeleteYn() == false) {
			System.out.println("if문");
			queryResult = boardMapper.deleteBoard(idx);
			 System.out.println("queryResult:"+queryResult); 
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {
		System.out.println("함수에는 들어가나?");
		List<BoardDTO> boardList = Collections.emptyList();
		//생성자로 초기화 지정했던 부분 이곳에서 실행.
		Criteria criteria=new Criteria(); 
		
		BoardCriteria boardCriteria = new BoardCriteria(params, criteria);
		params.setPostType("개발자형");
		
//		boardCriteria.setBoardDTO(params);
		
//		int boardTotalCount = boardMapper.selectBoardTotalCount(params);
		int boardTotalCount = boardMapper.selectBoardTotalCount(boardCriteria);
		
		System.out.println("boardTotalCount : " + boardTotalCount);
		System.out.println("params.getIdx()" + params.getIdx());
		
		if (boardTotalCount > 0) {
			
			boardList = boardMapper.selectBoardList(boardCriteria);
			System.out.println("boardlist:"+boardList);
			System.out.println("params 뭐 나오지"+params.getIdx());
		}

		return boardList;
	}

}