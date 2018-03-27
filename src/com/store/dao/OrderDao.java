package com.store.dao;

import com.store.domain.Order;
import com.store.domain.OrderItem;

public interface OrderDao {

	void addOrder(Order order) throws Exception;

	void addItem(OrderItem item) throws Exception;

}
