package com.store.dao;

import java.sql.SQLException;

import com.store.domain.User;

public interface UserDao {

	void add(User user) throws Exception;

	User getUser(String key, String value) throws Exception;

	void update(User user) throws SQLException;

}
