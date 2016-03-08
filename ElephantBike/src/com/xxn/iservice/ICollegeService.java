package com.xxn.iservice;

import java.util.List;

import com.xxn.entity.College;

public interface ICollegeService {
	public int addArea(College college);
	public List<String> getAddrByCollege(College college);
}
