package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.utils.BeanFactory;
import com.store.domain.Order;
import com.store.domain.OrderPage;
import com.store.service.OrderService;
/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//通过状态查询订单
	public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取订单状态
		String state = request.getParameter("state");
		//获取当前页
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//设置每页订单条数
		int pageSize = 6;
		//调用orderorderservice查询当前页订单，返回pagebean
		OrderService os = (OrderService) new BeanFactory().getBean("OrderService");
		OrderPage orderPage = os.findAllByState(state, currentPage, pageSize);
		
		request.setAttribute("orderPage", orderPage);
		return "/admin/order/list.jsp";
	}
}
