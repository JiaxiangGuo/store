package com.store.service.impl;

import com.store.constant.Constant;
import com.store.dao.ProductDao;
import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.utils.BeanFactory;
import com.store.utils.MailUtils;

public class UserServiceImpl implements UserService {

	/*
	 * 用户注册
	 */
	@Override
	public void regist(User user) throws Exception{
		UserDao dao = (UserDao) new BeanFactory().getBean("UserDao");
		dao.add(user);
		//发送邮件
		//emailMsg邮件内容
		String emailMsg = "欢迎注册，<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+">点此激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
		}
	
	/*
	 * 用户激活
	 */
	@Override
	public User active(String code) throws Exception {
		UserDao dao = (UserDao) new BeanFactory().getBean("UserDao");
		User user = dao.getUser("code", code);
		
		if(user == null) {
			return null;
		}
		
		user.setState(Constant.USER_IS_ACTIVE);//用户状态设置为1
		dao.update(user);
		
		return user;
		
	}
	/*
	 * 用户登入
	 */
	@Override
	public User login(String username) throws Exception {
		//调用dao查询用户
		UserDao dao = (UserDao) new BeanFactory().getBean("UserDao");
		return dao.getUser("username", username);
	
	}
}
