package com.xxn.service;

import java.util.List;

import com.xxn.dao.CollegeDao;
import com.xxn.entity.College;
import com.xxn.idao.ICollegeDao;
import com.xxn.iservice.ICollegeService;

public class CollegeService implements ICollegeService{

	@Override
	public int addArea(College college) {
		ICollegeDao iCollegeDao = new CollegeDao();
		return iCollegeDao.addArea(college);
	}

	@Override
	public List<String> getAddrByCollege(College college) {
		ICollegeDao iCollegeDao = new CollegeDao();
		return iCollegeDao.getAddrByCollege(college);
	}

	@Override
	public List<College> getDistinctName() {
		ICollegeDao iCollegeDao = new CollegeDao();
		return iCollegeDao.getDistinctName();
	}

	@Override
	public List<String> getAllAddr() {
		ICollegeDao iCollegeDao = new CollegeDao();
		return iCollegeDao.getAllAddr();
	}

}
