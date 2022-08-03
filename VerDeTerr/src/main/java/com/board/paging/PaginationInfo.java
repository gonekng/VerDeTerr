package com.board.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationInfo {

	@Override
	public String toString() {
		return "PaginationInfo [criteria=" + criteria + ", totalRecordCount=" + totalRecordCount + ", totalPageCount="
				+ totalPageCount + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", firstRecordIndex="
				+ firstRecordIndex + ", lastRecordIndex=" + lastRecordIndex + ", hasPreviousPage=" + hasPreviousPage
				+ ", hasNextPage=" + hasNextPage + "]";
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	/** 페이징 계산에 필요한 파라미터들이 담긴 클래스 */
	private Criteria criteria;

	/** 전체 데이터 개수 */
	private int totalRecordCount;

	/** 전체 페이지 개수 */
	private int totalPageCount;

	/** 페이지 리스트의 첫 페이지 번호 */
	private int firstPage;

	/** 페이지 리스트의 마지막 페이지 번호 */
	private int lastPage;

	/** SQL의 조건절에 사용되는 첫 RNUM */
	private int firstRecordIndex;

	/** SQL의 조건절에 사용되는 마지막 RNUM */
	private int lastRecordIndex;

	/** 이전 페이지 존재 여부 */
	private boolean hasPreviousPage;

	/** 다음 페이지 존재 여부 */
	private boolean hasNextPage;

	public PaginationInfo(Criteria criteria) {
		if (criteria.getCurrentPageNo() < 1) {
			criteria.setCurrentPageNo(1);
		}
		if (criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100) {
			criteria.setRecordsPerPage(10);
		}
		if (criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
			criteria.setPageSize(10);
		}

		this.criteria = criteria;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;

		if (totalRecordCount > 0) {
			calculation();
		}
	}

	private void calculation() {

		/* 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장) */
		totalPageCount = ((totalRecordCount - 1) / criteria.getRecordsPerPage()) + 1;
		if (criteria.getCurrentPageNo() > totalPageCount) {
			criteria.setCurrentPageNo(totalPageCount);
		}

		/* 페이지 리스트의 첫 페이지 번호 */
		firstPage = ((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;

		/* 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장) */
		lastPage = firstPage + criteria.getPageSize() - 1;
		if (lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}

		/* SQL의 조건절에 사용되는 첫 RNUM */
		firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();

		/* SQL의 조건절에 사용되는 마지막 RNUM */
		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();

		/* 이전 페이지 존재 여부 */
		hasPreviousPage = firstPage != 1;

		/* 다음 페이지 존재 여부 */
		hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
	}

}



/*
1.criteria:페이지 번호 계산에 필요한 Criteria 클래스의 멤버 변수들에 대한 정보를 가지는 변수입니다. 
2.totalrecordcount:전체 데이터의 개수를 의미합니다.
3.totalpagecount:전체 페이지개수를 의미. 예를 들어, 전체 데이터의 개수가 300개이고, 
페이지당 출력할 데이터의 개수가 10개 라고 가정했을 때, 30페이지를 의미합니다. 

4.firstpage:pageSize 10 이고, currentPageNo이 3이라고 가정했을 때 1페이지를 의미 합니댜.
5.lastpage:pageSize가 10 이고, currentPageNo이 3 이라고, 가정했을 때 10페이지를 의미 합니다. 
6.firstRecordlndex: Criteria 클래스의 getStartPage 메서드를 대체해서 LIMIT 구문의 첫 번째 값에 사용되는 변수. 
7.lastRecordindex:오라클과 같이 LIMIT 구문이 존재하지 않고, 인라인 뷰(FROM 절 서브 쿼리)를 사용해야 하는 데이터베이스에서
사용됩니다. 우리는 mysql을 기반으로 진행하기 때문에 사용x 
8.hasPreviousPage:이전 페이지가 존재하는 지를 구분하는 용도로 사용됩니다. 
예를 들어, currentPageNo이 13이라면, 이전 페이지에 해당하는 1~10 까지의 페이지가 있기 때문에 true 가 됩니다. 
만약 currentPageNo이 1~10 사이라면 false가 됩니다.
9.다음 페이지가 존재하는 지를 구분하는 용도로 사용됩니다.
예를들어, pageSize가 10일때 lastPage가 25이고, currentPageNo이 13이라면, 11~20페이지 사이에 있기 때문에 true가
됩니다 만약 currentPageNo이 21~25 사이라면 false가 됩니다. 




*/