package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.utils.DataSourceUtils;

import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) throws Exception {
		//插入订单
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), 
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	@Override
	public void addItem(OrderItem item) throws Exception {
		//插入订单项
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, item.getItemid(), item.getCount(), item.getSubtotal(), item.getProduct().getPid(), item.getOrder().getOid());
	}

	@Override
	public List<Order> findBypage(String uid, int currentPage, int pageSize) throws Exception {
		//查询当前页订单
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ? limit ?,?";
		List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), uid, (currentPage-1)*pageSize, pageSize);
		
		sql = "select * from orderitem oi,product p where oi.pid=p.pid and oid=?";
		
		for (Order order : list) {
			String oid = order.getOid();
			List<Map<String, Object>> list2 = qr.query(sql, new MapListHandler(), oid);
			for (Map<String, Object> map : list2) {
				Product p = new Product();
				BeanUtils.populate(p, map);
				
				OrderItem oi = new OrderItem();
				BeanUtils.populate(oi, map);
				
				oi.setProduct(p);
				
				order.getItems().add(oi);
			}
		}
		return list;
	}
	//根据用户id查询订单总条数
	@Override
	public int findTotalCount(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid=?";
		
		return ((Long)qr.query(sql, new ScalarHandler(), uid)).intValue();
	}

}
