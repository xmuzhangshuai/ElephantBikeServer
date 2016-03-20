package com.xxn.iservice;

import com.xxn.entity.Bike;

public interface IBikeService {
	public int addBike(Bike bike);
	public Bike getBikeMN(Bike bike);
	public int addBikeMN(Bike bike);
	public int resetBikeMN(Bike bike);
	public boolean isCanUsed(Bike bike);
	public int updateBikeState(Bike bike);
}
