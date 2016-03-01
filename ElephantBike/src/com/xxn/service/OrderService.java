package com.xxn.service;

import java.util.Map;

import com.xxn.dao.OrderDao;
import com.xxn.entity.Order;
import com.xxn.idao.IOrderDao;
import com.xxn.iservice.IOrderService;

public class OrderService implements IOrderService {

	@Override
	public int createOrder(Order order) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.createOrder(order);
	}

	@Override
	public String getOrderInfo(Order order) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.getOrderInfo(order);
	}

	@Override
	public int updateOrder(Map val, Map query) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.updateOrder(val, query);
	}

	@Override
	public int getOrderCount(Map<String, String> query) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.getOrderCount(query);
	}

	@Override
	public String getBikeid(Order order) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.getBikeid(order);
	}


}
