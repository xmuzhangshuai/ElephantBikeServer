package com.xxn.idao;

import java.util.List;

import com.xxn.entity.Activity;

public interface IActivityDao {
	public int createActivty(Activity activity);
	public List<Activity> getHotActivty(Activity activity);
}
