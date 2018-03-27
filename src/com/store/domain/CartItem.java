package com.store.domain;

import java.io.Serializable;

public class CartItem implements Serializable{
	private Integer count;
	private Double subtotal = 0.0;
	private Product product;
	
	public CartItem() {
	}
	
	public CartItem(Product product, Integer count) {
		this.count = count;
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
