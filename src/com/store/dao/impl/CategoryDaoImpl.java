package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {
	/*
	 * 查询分类列表，返回一个list
	 */
	@Override
	public List<Category> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from category";
		
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

}
