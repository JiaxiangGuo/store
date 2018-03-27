package com.store.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;

public class Cart implements Serializable{
	//存放购物车项的集合
	private LinkedHashMap<String, CartItem> map = new LinkedHashMap<>();
	//总金额
	private Double total = 0.0;
	//添加到购物车
	public LinkedHashMap<String, CartItem> addToCart(CartItem item){
		String pid = item.getProduct().getPid();
		if(map.containsKey(pid)) {
			map.get(pid).setCount(map.get(pid).getCount() + item.getCount());
		}else {
			map.put(pid, item);
		}
		
		//修改金额
		total += item.getSubtotal();
		
		return map;
	}
	
	//从购物车删除
	public void removeFromCart(String id){
		CartItem item = map.remove(id);	
		total -= item.getSubtotal();
	}
	
	//清空购物车
	public void clearCart() {
		map.clear();
		
		total = 0.0;
	}
	
	public LinkedHashMap<String, CartItem> getMap() {
		return map;
	}
	public void setMap(LinkedHashMap<String, CartItem> map) {
		this.map = map;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	//获取购物车项 	 
	public Collection<CartItem> getItems(){
		return map.values();
	}
	
}
