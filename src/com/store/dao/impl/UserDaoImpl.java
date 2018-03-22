package com.store.dao.impl;

import org.apache.commons.dbutils.QueryRunner;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.DataSourceUtils;

import javafx.scene.chart.PieChart.Data;

public class UserDaoImpl implements UserDao {
	
	@Override
	public void add(User user) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * private String uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String telephone;
	private Data birthday;
	private String sex;
	private Integer state = 0;
	private String code;
		 */
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
				user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
				user.getState(), user.getCode());
	}
}
