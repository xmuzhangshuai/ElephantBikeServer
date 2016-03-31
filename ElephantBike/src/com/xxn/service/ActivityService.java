package com.xxn.service;

import java.util.List;

import com.xxn.dao.ActivityDao;
import com.xxn.entity.Activity;
import com.xxn.idao.IActivityDao;
import com.xxn.iservice.IActivityService;

public class ActivityService implements IActivityService{

	@Override
	public int createActivty(Activity activity) {
		IActivityDao iActivityDao = new ActivityDao();
		return iActivityDao.createActivty(activity);
	}

	@Override
	public List<Activity> getHotActivty(Activity activity) {
		IActivityDao iActivityDao = new ActivityDao();
		return iActivityDao.getHotActivty(activity);
	}

}
