package cn.no7player.common.bean;

import java.util.List;

public class PageResult<T> {

	private List<T> datas;
	private Integer totalRows = 0;
	private Integer currentPage = 1;
	private Integer pageSize = 10;

	public PageResult(List<T> datas, int totalRows, int currentPage,
					  int pageSize) {
		this.datas = datas;
		this.totalRows = totalRows;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public int getTotalPage(){
		return (totalRows+pageSize-1)/pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	@Override
	public String toString() {
		return "PageResult{" +
				"datas=" + datas +
				", totalRows=" + totalRows +
				", currentPage=" + currentPage +
				", pageSize=" + pageSize +
				'}';
	}
}
