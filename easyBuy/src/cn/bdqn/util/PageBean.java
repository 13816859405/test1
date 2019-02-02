package cn.bdqn.util;

import java.util.List;

public class PageBean<T> {
	
	private int pageNo=1;
	private int pageSize=8;
	private int totalCount;
	private int totalPages;
	private List<T> pageList;
	public int getPageNo() {
		return pageNo;
	}
	//����ҳ��
	public void setPageNo(int pageNo) {
		if(pageNo<1){
			this.pageNo=1;
		}else if(pageNo>totalPages){
			this.pageNo=totalPages;
		}else{
			this.pageNo = pageNo;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//�����ܼ�¼�����Զ�������ҳ��
		this.totalPages=(totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
	}
	//ֻ��
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	
	
}
