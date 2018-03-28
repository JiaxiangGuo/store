package com.store.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.OrderPage;
import com.store.domain.User;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.UUIDUtils;

/**
 * 订单相关
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private Object attribute;

	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1.判断用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return "/jsp/login.jsp";
		}
		//2.封装数据order
		Order order = new Order();
		
		//2.1获取session中的购物车信息
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Collection<CartItem> items = cart.getItems();
		//2.2封装orderitem
		for(CartItem item : items) {
			OrderItem oitem = new OrderItem();
			oitem.setCount(item.getCount());
			oitem.setItemid(UUIDUtils.getId());
			oitem.setOrder(order);
			oitem.setProduct(item.getProduct());
			order.getItems().add(oitem);
		}
		//2.3封装order
		order.setOid(UUIDUtils.getId());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setUser(user);
		
		//3.调用servic 添加订单
		OrderService os = (OrderService) new BeanFactory().getBean("OrderService");
		
		os.add(order);
		
		request.setAttribute("order", order);
		request.getSession().setAttribute("cart", null);
		
		return "/jsp/order_info.jsp"; 
	}
	//分页查询订单
	public String findAllPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1.判断用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return "/jsp/login.jsp";
		}
		//获取用户id
		String uid = user.getUid();
		//获取当前页
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//设置每页订单数量
		int pageSize = 3;
		//调用orderorderservice查询当前页订单，返回pagebean
		OrderService os = (OrderService) new BeanFactory().getBean("OrderService");
		OrderPage orderpage = os.findByPage(uid, currentPage, pageSize);
		
		request.setAttribute("orderpage", orderpage);
		
		
		return "/jsp/order_list.jsp";
	}
}
