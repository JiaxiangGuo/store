package com.store.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

}
