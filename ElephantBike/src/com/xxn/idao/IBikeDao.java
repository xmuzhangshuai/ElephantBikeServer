package com.xxn.idao;

import com.xxn.entity.Bike;

public interface IBikeDao {
	/**
	 * 添加单车
	 * @param bike
	 * @return 1 添加成功
	 */
	public int addBike(Bike bike);
	
	/**
	 * 通过单车id来获取m , n 
	 * @param bike 单车id
	 * @return
	 */
	public Bike getBikeMN(Bike bike);
	
	/**
	 *  还车密码正确时候通过单车id来将m,n 各 +1
	 * @param bike bikeid
	 * 
	 * @return
	 */
	public int addBikeMN(Bike bike);
	
	/**
	 * m,n恢复初始状态
	 * @param bike
	 * @return
	 */
	public int resetBikeMN(Bike bike);
	
	public int updateBikeState(Bike bike);
	/**
	 * 判断单车是否可以使用
	 * @param bike
	 * @return
	 */
	public boolean isCanUsed(Bike bike);
	
}
