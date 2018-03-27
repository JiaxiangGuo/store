package com.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;


/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//从数据库中查询最新商品和热门商品，放入request中
		ProductService ps = (ProductService) new BeanFactory().getBean("ProductService");
		List<Product> nProductList = ps.getNewProduct();
		List<Product> hProductList = ps.getHotProduct();
		
		request.setAttribute("nProductList", nProductList);
		request.setAttribute("hProductList", hProductList);
		
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		return null;
	}


}
