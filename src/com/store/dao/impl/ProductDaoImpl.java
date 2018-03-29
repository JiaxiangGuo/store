package com.store.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.ProductDao;
import com.store.domain.Product;
import com.store.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {
	
	//查询最新商品
	@Override
	public List<Product> getNewProduct() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from product order by pdate limit 9";
		
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}
	
	//查询热门商品
	@Override
	public List<Product> getHotProduct() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from product where is_hot=1 order by pdate limit 9";
		
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}
	//通过id获取商品
	@Override
	public Product getById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from product where pid=?";
		
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}
	//查询分类商品
	@Override
	public List<Product> findByPage(String cid, int currentPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid, (currentPage-1)*pageSize, pageSize);
	}
	//查询分类商品总条数
	@Override
	public int getTotalCount(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		return ((Long)qr.query(sql,new ScalarHandler(), cid)).intValue();
	}

	@Override
	public List<Product> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

}
