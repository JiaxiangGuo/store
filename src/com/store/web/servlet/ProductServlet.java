package com.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;

/**
 * 商品相关servlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * 通过id获取商品
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
	  /**
     * 获取分类商品详情
     */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取商品id
		String cid = request.getParameter("cid");
		//获取当前页
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//设置每页展示商品条数
		int pageSize = 6;
		//通过id获取商品
		ProductService ps = new ProductServiceImpl();
		PageBean pb = ps.findByPage(cid, currentPage, pageSize);
		//将商品写回网页
		request.setAttribute("pb", pb);
		
		return "/jsp/product_list.jsp";
	}
}
