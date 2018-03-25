package com.store.service;

import java.util.List;

import com.store.domain.Product;

public interface ProductService {

	List<Product> getNewProduct() throws Exception;

	List<Product> getHotProduct() throws Exception;

	Product getById(String pid) throws Exception;

}
