package com.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.impl.CategaryServiceImpl;
import com.store.utils.JsonUtil;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.调用CategoryService查询所有的分类，返回值list
		CategoryService cs = new CategaryServiceImpl();
		
		List<Category> clist = cs.findAll();
		
		//2.将返回值写回
		String json = JsonUtil.list2json(clist);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
		
		return null;
	}
}
