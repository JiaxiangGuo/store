package com.store.dao;

import java.util.List;

import com.store.domain.Product;

public interface ProductDao {

	List<Product> getNewProduct() throws Exception;

	List<Product> getHotProduct() throws Exception;

	Product getById(String pid)throws Exception;

	List<Product> findByPage(String cid, int currentPage, int pageSize) throws Exception;

	int getTotalCount(String cid) throws Exception;

}
