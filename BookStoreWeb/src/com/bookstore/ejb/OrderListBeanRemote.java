package com.bookstore.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.bookstore.model.Orders;

@Remote
public interface OrderListBeanRemote {
	public List<Orders> getRecentOrders();
}
