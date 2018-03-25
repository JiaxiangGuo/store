package com.store.domain;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable{
	private List<Product> list;
	private Integer currentPage;
	private Integer pageSize;
	
	private Integer totalPage;
	private Integer totalCount;
	
	
	
	public PageBean() {
	}
	public PageBean(List<Product> list, Integer currentPage, Integer pageSize, Integer totalCount) {
		super();
		this.list = list;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return (int) Math.ceil(totalCount*1.0/pageSize);
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
