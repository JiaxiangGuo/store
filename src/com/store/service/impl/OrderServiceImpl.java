package com.store.service.impl;

import java.sql.SQLException;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void add(Order order) throws Exception{
		
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
			
			OrderDao orderdao = (OrderDao) new BeanFactory().getBean("OrderDao");
			//2.向order表中添加一条数据
			orderdao.addOrder(order);
			//3.向orderitem中添加n条数据
			for(OrderItem item : order.getItems()) {
				orderdao.addItem(item);
			}
			//4.事务处理
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

}
