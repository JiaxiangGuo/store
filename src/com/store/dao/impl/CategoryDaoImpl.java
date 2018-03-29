package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
	//添加新分类
	@Override
	public void add(Category category) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values (?,?)";
		qr.update(sql, category.getCid(), category.getCname());
	}
	//通过id获取分类
	@Override
	public Category getById(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from category where cid=?";
		
		return qr.query(sql, new BeanHandler<>(Category.class), cid);
	}
	@Override
	public void update(String cid, String cname) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		qr.update(sql, cname, cid);
	}
	@Override
	public void delete(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid = ?";
		qr.update(sql, cid);
	}

}
