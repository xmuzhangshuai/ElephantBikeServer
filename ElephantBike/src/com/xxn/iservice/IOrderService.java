package com.xxn.iservice;

import java.util.List;
import java.util.Map;

import com.xxn.entity.Order;

public interface IOrderService {
	
	public int createOrder(Order order);
	public String getOrderInfo(Order order);
	public int updateOrder(Map val,Map query);
	public int getOrderCount(Map<String, String> query);
	public List<Order> findForPage(int start, int end, String sort,String order, Map queryParams);
	public String getBikeid(Order order);
	
	/**
	 * 获取Order表的信息模板
	 * @param val
	 * @param query
	 * @return
	 */
	public Map<String, String> getOrderInfo(Map<String, String> val,Map<String, String> query);
}
