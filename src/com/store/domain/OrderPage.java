package com.store.domain;

import java.io.Serializable;
import java.util.List;

public class OrderPage implements Serializable{
	private Integer currentPage;
	private Integer	pageSize;
	private List<Order> list;
	private Integer totalCount;
	private Integer totalPage;
	
	
	public OrderPage() {
	}
	
	
	public OrderPage(Integer currentPage, Integer pageSize, List<Order> list, Integer totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.list = list;
		this.totalCount = totalCount;
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
	public List<Order> getList() {
		return list;
	}
	public void setList(List<Order> list) {
		this.list = list;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}


	public Integer getTotalPage() {
		return (int) Math.ceil(totalCount*1.0/pageSize);
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
