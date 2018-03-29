package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.BeanFactory;

import com.store.domain.Product;
import com.store.service.ProductService;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ProductService ps = (ProductService) new com.store.utils.BeanFactory().getBean("ProductService");
		
		List<Product> list = ps.findAll();
		request.setAttribute("list", list);
		return "/admin/product/list.jsp";
	}
}
