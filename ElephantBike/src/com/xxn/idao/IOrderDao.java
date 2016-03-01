package com.xxn.idao;

import java.util.Map;

import com.xxn.entity.Order;

public interface IOrderDao {
	/**
	 * 扫描获取解锁密码，订单开始
	 * @param order
	 * @return
	 */
	public int createOrder(Order order);
	
	public String getOrderInfo(Order order);
	
	public int updateOrder(Map val,Map query);
	
	public int getOrderCount(Map<String, String> query);
	
	/**
	 * 获取未完成订单的单车编号
	 * @param order
	 * @return
	 */
	public String getBikeid(Order order);
	
}
