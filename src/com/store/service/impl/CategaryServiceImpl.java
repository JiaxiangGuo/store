package com.store.service.impl;

import java.util.List;

import com.store.dao.CategoryDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.domain.Category;
import com.store.service.CategoryService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CategaryServiceImpl implements CategoryService{
	/*
	 * 查询分类列表
	 */
	@Override
	public List<Category> findAll() throws Exception {
		//1.创建缓存管理器
		CacheManager cm = CacheManager.create(CategaryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//2.获取指定的缓存
		Cache cache = cm.getCache("categoryCache");
		//3.通过缓存获取数据,将cache看成一个map集合
		Element element = cache.get("clist");
		List<Category> list = null;
		//4.判断数据
		if(element == null) {
			//1.从数据库中获取
			CategoryDao dao = new CategoryDaoImpl();
			list = dao.findAll();
			//2.将list返回
			cache.put(new Element("clist", list));
		}else {
			list = (List<Category>) element.getObjectValue();
		}
		
		return list;
		//return (List<Category>) element.getObjectValue(); 出现空指针异常
	}

}
