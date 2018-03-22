package com.store.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.UUIDUtils;

/**
 * 和用户相关的Servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("add执行了");
		
		return null;
	}
	
	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		return "/jsp/register.jsp";
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1.封装数据
		User user = new User();
		BeanUtils.populate(user, request.getParameterMap());
		
		//1.1设置用户id
		user.setUid(UUIDUtils.getId());
		//1.2设置激活码
		user.setCode(UUIDUtils.getCode());
		
		//2.调用service完成注册
		UserService s = new UserServiceImpl();
		s.regist(user);
		
		//3.提示信息，请求转发
		request.setAttribute("msg", "注册成功，请到邮箱激活");
		return "/jsp/msg.jsp";
		
	}
}
