package com.store.service;

import com.store.domain.Order;
import com.store.domain.OrderPage;

public interface OrderService {

	void add(Order order) throws Exception;

	OrderPage findByPage(String uid, int currentPage, int pageSize) throws Exception;

}
