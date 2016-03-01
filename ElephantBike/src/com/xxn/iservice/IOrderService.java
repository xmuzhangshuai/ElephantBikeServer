package com.xxn.iservice;

import java.util.Map;

import com.xxn.entity.Order;

public interface IOrderService {
	
	public int createOrder(Order order);
	public String getOrderInfo(Order order);
	public int updateOrder(Map val,Map query);
	public int getOrderCount(Map<String, String> query);
	public String getBikeid(Order order);

}
