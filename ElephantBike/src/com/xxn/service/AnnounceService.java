package com.xxn.service;

import java.util.Map;

import com.xxn.dao.AnnounceDao;
import com.xxn.idao.IAnnounceDao;
import com.xxn.iservice.IAnnounceService;

public class AnnounceService implements IAnnounceService{

	@Override
	public int updateAnnounce(Map<String, String> val, Map<String, String> query) {
		IAnnounceDao iAnnounceDao = new AnnounceDao();
		return iAnnounceDao.updateAnnounce(val, query);
	}

}
