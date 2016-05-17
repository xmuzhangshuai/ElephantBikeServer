package com.xxn.idao;

import java.util.List;
import java.util.Map;

import com.xxn.entity.Bike;
import com.xxn.entity.User;

public interface IBikeDao {
	/**
	 * 添加单车
	 * 
	 * @param bike
	 * @return 1 添加成功
	 */
	public int addBike(Bike bike);

	/**
	 * 通过单车id来获取m , n
	 * 
	 * @param bike
	 *            单车id
	 * @return
	 */
	public Bike getBikeMN(Bike bike);

	/**
	 * 还车密码正确时候通过单车id来将m,n 各 +1
	 * 
	 * @param bike
	 *            bikeid
	 * 
	 * @return
	 */
	public int addBikeMN(Bike bike);

	/**
	 * m,n恢复初始状态
	 * 
	 * @param bike
	 * @return
	 */
	public int resetBikeMN(Bike bike);

	public int updateBikeState(Bike bike);

	/**
	 * 判断单车是否可以使用
	 * 
	 * @param bike
	 * @return
	 */
	public int isCanUsed(Bike bike);

	// 获取条数
	public int getObjectCount(Map queryParams);
	// 获取列表
	public List<Object> findForPage(int start, int end, String sort,
			String order, Map queryParams);
}
