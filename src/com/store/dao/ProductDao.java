package com.store.dao;

import java.util.List;

import com.store.domain.Product;

public interface ProductDao {

	List<Product> getNewProduct() throws Exception;

	List<Product> getHotProduct() throws Exception;

}
