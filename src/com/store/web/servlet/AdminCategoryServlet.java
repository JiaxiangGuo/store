package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.utils.BeanFactory;
import com.store.utils.UUIDUtils;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//查找所有分类
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//CategoryService cs = (CategoryService) new BeanFactory().getBean("CategoryService");
		CategoryDao cd = (CategoryDao) new BeanFactory().getBean("CategoryDao");
		List<Category> list = cd.findAll();
		
		request.setAttribute("list", list);
		
		return "/admin/category/list.jsp";
	}
	
	//请求转发到添加页面
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return "/admin/category/add.jsp";
	}
	
	//添加分类
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取分类
		String cname = request.getParameter("cname");
		//生成分类id
		String cid = UUIDUtils.getId();
		//封装分类
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		
		CategoryService cs = (CategoryService) new BeanFactory().getBean("CategoryService");
		cs.add(category);
		response.sendRedirect("/store/adminCategory?method=findAll");
		return null;
	}
}
