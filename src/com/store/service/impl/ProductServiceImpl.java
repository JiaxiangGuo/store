package com.store.service.impl;

import java.util.List;

import com.store.dao.ProductDao;
import com.store.dao.impl.ProductDaoImpl;
import com.store.domain.Product;
import com.store.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	//查询最新商品
	@Override
	public List<Product> getNewProduct() throws Exception {
		ProductDao pdao =new ProductDaoImpl();
		return pdao.getNewProduct();
	}

	//查询热门商品
	@Override
	public List<Product> getHotProduct() throws Exception {
		ProductDao pdao =new ProductDaoImpl();
		return pdao.getHotProduct();
	}

}
