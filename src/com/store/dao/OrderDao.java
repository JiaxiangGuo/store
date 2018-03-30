package com.store.dao;

import java.util.List;

import com.store.domain.Order;
import com.store.domain.OrderItem;

public interface OrderDao {

	void addOrder(Order order) throws Exception;

	void addItem(OrderItem item) throws Exception;

	List<Order> findBypage(String uid, int currentPage, int pageSize) throws Exception;

	int findTotalCount(String uid) throws Exception;

	Order getById(String oid) throws Exception;

	List<Order> findAllByState(String state, int currentPage, int pageSize) throws Exception;

	int findTotalCountBystate(String state) throws Exception;

	void updateState(String oid, String state) throws Exception;

}
