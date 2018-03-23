package com.store.service.impl;

import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.utils.MailUtils;

public class UserServiceImpl implements UserService {

	/*
	 * 用户注册
	 */
	@Override
	public void regist(User user) throws Exception{
		UserDao dao = new UserDaoImpl();
		dao.add(user);
		//发送邮件
		//emailMsg邮件内容
		String emailMsg = "欢迎注册，<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+">点此激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
		}

	@Override
	public User active(String code) throws Exception {
		UserDao dao = new UserDaoImpl();
		User user = dao.getUserByCode(code);
		
		if(user == null) {
			return null;
		}
		
		dao.update(user);
		
		return user;
		
	}
}
