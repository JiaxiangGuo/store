package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.ProductDao;
import com.store.dao.impl.ProductDaoImpl;
import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.impl.CategaryServiceImpl;
import com.store.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//从数据库中查询最新商品和热门商品，放入request中
		ProductService ps = new ProductServiceImpl();
		List<Product> nProductList = ps.getNewProduct();
		List<Product> hProductList = ps.getHotProduct();
		
		request.setAttribute("nProductList", nProductList);
		request.setAttribute("hProductList", hProductList);
		
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		return null;
	}


}
