package com.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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

	@Override
	public User getUserByCode(String code) throws SQLException {
		//查询用户是否存在
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from user where code=? limit 1";
		
		return qr.query(sql, new BeanHandler<>(User.class), code);
		
	}

	@Override
	public void update(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "update user set password=?,state=? where uid=?;";
		
		qr.update(sql, user.getPassword(),user.getState(), user.getUid());
		
	}


}
