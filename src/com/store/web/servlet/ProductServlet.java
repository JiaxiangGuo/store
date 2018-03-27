package com.store.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;
import com.store.utils.BeanFactory;
import com.store.utils.CookUtils;

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
		ProductService ps = (ProductService) new BeanFactory().getBean("ProductService");
		Product p = ps.getById(pid);
		//将商品写回网页
		request.setAttribute("p", p);
		
		//浏览记录
		//获取指定Cookie
		Cookie c = CookUtils.getCookieByName("ids", request.getCookies());
		
		String ids = "";
		
		//判断cookie是否存在，若不存在，将pid写入cookie
		if(c == null) {
			ids = pid;
			
		}
		//若存在cookie，判断cookie中是否存在商品id存在则移除，然后添加到第一个，不存在则直接加到第一个
		else {
			String[] strArr = c.getValue().split("-");
			LinkedList<String> list = new LinkedList<String>(Arrays.asList(strArr));
			
			if(list.contains(pid)) {
				list.remove(pid);
				list.addFirst(pid);
			}else {
				if(list.size() > 6) {
					list.removeLast();
					list.addFirst(pid);
				}else {
					list.addFirst(pid);
				}
			}
			for(String s:list) {
				ids += (s + "-");
			}
			ids = ids.substring(0, ids.length()-1);
		}
		c = new Cookie("ids", ids);
		c.setMaxAge(3600);
		c.setPath(request.getContextPath()+"/");
		response.addCookie(c);
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
		ProductService ps = (ProductService) new BeanFactory().getBean("ProductService");
		PageBean pb = ps.findByPage(cid, currentPage, pageSize);
		//将商品写回网页
		request.setAttribute("pb", pb);
		
		return "/jsp/product_list.jsp";
	}
}
