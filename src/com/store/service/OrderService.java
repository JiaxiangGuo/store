package com.store.service;

import java.util.List;

import com.store.domain.Order;
import com.store.domain.OrderPage;

public interface OrderService {

	void add(Order order) throws Exception;

	OrderPage findByPage(String uid, int currentPage, int pageSize) throws Exception;

	Order getById(String parameter) throws Exception;

	OrderPage findAllByState(String state, int currentPage, int pageSize) throws Exception;


}
