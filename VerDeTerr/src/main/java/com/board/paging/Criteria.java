package com.board.paging;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Criteria {
	
	
	
	@Override
	public String toString() {
		return "Criteria [currentPageNo=" + currentPageNo + ", recordsPerPage=" + recordsPerPage + ", pageSize="
				+ pageSize + ", searchType=" + searchType + "]";
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	//현재 페이지 번호
	private int currentPageNo;
	//페이지당 출력할 데이터 개수 
	private int recordsPerPage;
	//화면 하단에 출력할 페이지 사이즈 
	private int pageSize;
	//검색 키워드 
	private String searchType;
	// 생성자 다른곳에서 인스턴스를 만들어줄때 쟤네들이 초기화가 된다.
	public Criteria() {
		this.currentPageNo=1;
		this.recordsPerPage=10;
		this.pageSize=10;
	}
	
	public int getStartPage() {
		return (currentPageNo-1)*recordsPerPage;
	}
}
