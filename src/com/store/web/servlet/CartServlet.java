package com.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;

/**
 * Servlet implementation class CartServlet
 */

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//获取session中的cart，没有则创建
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	
    /**
     * 添加到购物车 
     */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取pid 和 count
		String pid = request.getParameter("pid");
		Integer count = Integer.parseInt(request.getParameter("count"));
		//根据id获取商品
		ProductService ps = (ProductService) new BeanFactory().getBean("ProductService");
		Product product = ps.getById(pid);
		//封装购物车项
		CartItem item = new CartItem(product, count);
		//将购物车项加入购物车
		getCart(request).addToCart(item);
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	
	//从购物车中删除
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception{
		getCart(request).removeFromCart(request.getParameter("pid"));
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	//清空购物车
	public String clear(HttpServletRequest request, HttpServletResponse response) throws Exception{
		getCart(request).clearCart();
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
}
