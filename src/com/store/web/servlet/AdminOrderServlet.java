package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.utils.BeanFactory;
import com.store.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.OrderPage;
import com.store.service.OrderService;
/**
 * 后台管理订单相关
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
	//查询订单详情
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		String oid = request.getParameter("oid");
		OrderService os = (OrderService) new BeanFactory().getBean("OrderService");
		Order order = os.getById(oid);
		List<OrderItem> list = order.getItems();
		//将list封装成json
		
		JsonConfig config = JsonUtil.configJson(new String[] {"class", "itemid", "order"});//除去不想封装字段
		JSONArray listJson = JSONArray.fromObject(list, config);
		
		response.getWriter().println(listJson);
		
		return null;
	}
	
	//修改订单状态
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String oid = request.getParameter("oid");
		String state = request.getParameter("state");
		OrderService os = (OrderService) new BeanFactory().getBean("OrderService");
		os.updateState(oid, state);
		response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1&currentPage=1");
		return null;
	}
}
