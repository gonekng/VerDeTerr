package com.board.domain;

import java.time.LocalDateTime;

import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;

	/** 삭제 여부 */
	private Boolean deleteYn;

	/** 등록일 */
	private LocalDateTime insertTime;

	/** 수정일 */
	private LocalDateTime updateTime;

	/** 삭제일 */
	private LocalDateTime deleteTime;
	
	
	
	@Override
	public String toString() {
		return "CommonDTO [paginationInfo=" + paginationInfo + ", deleteYn=" + deleteYn + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", deleteTime=" + deleteTime + "]";
	}

	public PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public Boolean getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(Boolean deleteYn) {
		this.deleteYn = deleteYn;
	}

	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}



}