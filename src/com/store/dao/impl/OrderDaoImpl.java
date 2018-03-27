package com.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), 
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	@Override
	public void addItem(OrderItem item) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, item.getItemid(), item.getCount(), item.getSubtotal(), item.getProduct().getPid(), item.getOrder().getOid());
	}

}
