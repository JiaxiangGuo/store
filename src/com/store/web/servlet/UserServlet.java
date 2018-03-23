package com.store.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.store.domain.User;
import com.store.myconventer.MyConventer;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.MD5Utils;
import com.store.utils.UUIDUtils;


/**
 * 和用户相关的Servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1.获取激活码
		String code = request.getParameter("code");
		
		//2.调用service完成激活
		UserService s = new UserServiceImpl();
		
		User user = s.active(code);
		
		
		//3.提示信息
		if(user == null) {
			request.setAttribute("msg", "无此用户，请<a href='http://localhost:8080/store/user?method=registUI'>重新注册</a>");
		}else {
			request.setAttribute("msg", "激活成功");
		}
		return "/jsp/msg.jsp";
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
		//1.0注册转换器
		ConvertUtils.register(new MyConventer(), Date.class);
		
		BeanUtils.populate(user, request.getParameterMap());
		
		//1.1设置用户id
		user.setUid(UUIDUtils.getId());
		//1.2设置激活码
		user.setCode(UUIDUtils.getCode());
		//密码加密
		user.setPassword(MD5Utils.md5(user.getPassword()));
		
		//2.调用service完成注册
		UserService s = new UserServiceImpl();
		s.regist(user);
		
		//3.提示信息，请求转发
		request.setAttribute("msg", "注册成功，请到邮箱激活");
		return "/jsp/msg.jsp";
		
	}
}
