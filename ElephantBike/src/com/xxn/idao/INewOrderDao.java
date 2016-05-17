package com.xxn.idao;

import java.util.List;
import java.util.Map;

import com.xxn.entity.NewOrder;
import com.xxn.entity.Question;
import com.xxn.entity.TotalData;

public interface INewOrderDao {
	// 新增订单数据表的操作

	public int getNewOrderCount(String date);

	public int addNewOrder(NewOrder newOrder);

	public int updateNewOrder(NewOrder newOrder);
	
	public int getTotalDataCount(Map<String, String> query);
	public List<TotalData> findForPage(int start, int end, String sort,
			String order, Map queryParams);
	
}
