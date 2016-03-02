package com.xxn.iservice;

import java.util.List;

import com.xxn.entity.Activity;

public interface IActivityService {
	public int createActivty(Activity activity);
	public List<Activity> getHotActivty(Activity activity);
}
