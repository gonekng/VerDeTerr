package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardCriteria;
import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {

	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long idx);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long idx);

	// BoardCriteria 에 boardDTO, Criteria 둘다 넣어 놓고, 꺼내고 싶은거 꺼내 쓰면 된다. 
	public List<BoardDTO> selectBoardList(BoardCriteria boardCriteria);//BoardDTO params

	public int selectBoardTotalCount(BoardCriteria boardCriteria );//BoardDTO params
	
	public int restore(BoardDTO params);
	
	
	

}