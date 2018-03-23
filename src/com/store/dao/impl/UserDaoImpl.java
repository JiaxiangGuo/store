package com.store.dao.impl;

import org.apache.commons.dbutils.QueryRunner;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.DataSourceUtils;


public class UserDaoImpl implements UserDao {
	
	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
		
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
				user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
				user.getState(), user.getCode());
	}
}
