package com.store.service.impl;

import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.User;
import com.store.service.UserService;

public class UserServiceImpl implements UserService {

	/*
	 * 用户注册
	 */
	@Override
	public void regist(User user) throws Exception{
		// TODO Auto-generated method stub
		UserDao dao = new UserDaoImpl();
		dao.add(user);
		}
}
