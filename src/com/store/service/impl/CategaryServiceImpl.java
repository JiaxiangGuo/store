package com.store.service.impl;

import java.util.List;

import com.store.dao.CategoryDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.domain.Category;
import com.store.service.CategoryService;

public class CategaryServiceImpl implements CategoryService{
	/*
	 * 查询分类列表
	 */
	@Override
	public List<Category> findAll() throws Exception {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findAll();
	}

}
