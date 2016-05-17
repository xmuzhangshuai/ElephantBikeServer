package com.xxn.iservice;

import java.util.List;
import java.util.Map;

import com.xxn.entity.Bike;

public interface IBikeService {
	public int addBike(Bike bike);

	public Bike getBikeMN(Bike bike);

	public int addBikeMN(Bike bike);

	public int resetBikeMN(Bike bike);

	public int isCanUsed(Bike bike);

	public int updateBikeState(Bike bike);

	// 获取条数
	public int getObjectCount(Map queryParams);
	// 获取列表
	public List<Object> findForPage(int start, int end, String sort,
			String order, Map queryParams);
}
