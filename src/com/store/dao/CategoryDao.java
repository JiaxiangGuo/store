package com.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.store.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void add(Category category) throws Exception;

	Category getById(String cid) throws Exception;

	void update(String cid, String cname) throws Exception;

	void delete(String cid) throws Exception;

}
