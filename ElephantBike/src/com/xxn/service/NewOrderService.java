package com.xxn.service;

import java.util.List;
import java.util.Map;

import com.xxn.dao.NewOrderDao;
import com.xxn.entity.NewOrder;
import com.xxn.entity.TotalData;
import com.xxn.idao.INewOrderDao;
import com.xxn.iservice.INewOrderService;

public class NewOrderService implements INewOrderService{

	@Override
	public int getNewOrderCount(String date) {
		INewOrderDao iNewOrderDao = new NewOrderDao();
		return iNewOrderDao.getNewOrderCount(date);
	}

	@Override
	public int addNewOrder(NewOrder newOrder) {
		INewOrderDao iNewOrderDao = new NewOrderDao();
		return iNewOrderDao.addNewOrder(newOrder);
	}

	@Override
	public int updateNewOrder(NewOrder newOrder) {
		INewOrderDao iNewOrderDao = new NewOrderDao();
		return iNewOrderDao.updateNewOrder(newOrder);
	}

	@Override
	public int getTotalDataCount(Map<String, String> query) {
		INewOrderDao iNewOrderDao = new NewOrderDao();
		return iNewOrderDao.getTotalDataCount(query);
	}

	@Override
	public List<TotalData> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		INewOrderDao iNewOrderDao = new NewOrderDao();
		return iNewOrderDao.findForPage(start, end, sort, order, queryParams);
	}
	
	

}
