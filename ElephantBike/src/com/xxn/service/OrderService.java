package com.xxn.service;

import java.util.List;
import java.util.Map;

import com.xxn.dao.OrderDao;
import com.xxn.entity.Order;
import com.xxn.entity.BikeData;
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

	@Override
	public Map<String, String> getOrderInfo(Map<String, String> val, Map<String, String> query) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.getOrderInfo(val, query);
	}

	@Override
	public List<Order> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.findForPage(start, end, sort, order, queryParams);
	}

	@Override
	public List<BikeData> getBikeData(int start, int end, String sort,
			String order, Map queryParams) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.getBikeData(start, end, sort, order, queryParams);
	}

	@Override
	public int getBikeDataCount(Map queryParams) {
		IOrderDao iOrderDao = new OrderDao();
		return iOrderDao.getBikeDataCount(queryParams);
	}


}
