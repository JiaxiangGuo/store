package com.store.service.impl;

import java.util.List;

import com.store.dao.ProductDao;
import com.store.dao.impl.ProductDaoImpl;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {
	
	//查询最新商品
	@Override
	public List<Product> getNewProduct() throws Exception {
		ProductDao pdao =(ProductDao) new BeanFactory().getBean("ProductDao");
		return pdao.getNewProduct();
	}

	//查询热门商品
	@Override
	public List<Product> getHotProduct() throws Exception {
		ProductDao pdao =(ProductDao) new BeanFactory().getBean("ProductDao");
		return pdao.getHotProduct();
	}
	//通过id获取商品
	@Override
	public Product getById(String pid) throws Exception {
		ProductDao pdao = (ProductDao) new BeanFactory().getBean("ProductDao");
		return pdao.getById(pid);
	}
	//查询分类商品
	@Override
	public PageBean findByPage(String cid, int currentPage, int pageSize) throws Exception {
		ProductDao pdao = (ProductDao) new BeanFactory().getBean("ProductDao");
		//获得商品列表
		List<Product> plist = pdao.findByPage(cid, currentPage, pageSize);
		//获得总条数
		int totalPage = pdao.getTotalCount(cid);
		//返回封装好的pageBean
		return new PageBean(plist, currentPage, pageSize, totalPage);
	}

}
