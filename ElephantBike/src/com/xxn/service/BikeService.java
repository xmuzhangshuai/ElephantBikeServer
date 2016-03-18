package com.xxn.service;

import com.xxn.dao.BikeDao;
import com.xxn.entity.Bike;
import com.xxn.idao.IBikeDao;
import com.xxn.iservice.IBikeService;

public class BikeService implements IBikeService{

	@Override
	public int addBike(Bike bike) {
		IBikeDao iBikeDao = new BikeDao();
		return iBikeDao.addBike(bike);
	}

	@Override
	public Bike getBikeMN(Bike bike) {
		IBikeDao iBikeDao = new BikeDao();
		return iBikeDao.getBikeMN(bike);
	}

	@Override
	public int addBikeMN(Bike bike) {
		IBikeDao iBikeDao = new BikeDao();
		return iBikeDao.addBikeMN(bike);
	}

	@Override
	public int resetBikeMN(Bike bike) {
		IBikeDao iBikeDao = new BikeDao();
		return iBikeDao.resetBikeMN(bike);
	}

}
