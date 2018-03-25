package com.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 通过id获取商品
     * @throws Exception 
     */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取商品id
		String pid = request.getParameter("pid");
		//通过id获取商品
		ProductService ps = new ProductServiceImpl();
		Product p = ps.getById(pid);
		//将商品写回网页
		request.setAttribute("p", p);
		
		return "/jsp/product_info.jsp";
	}
}
