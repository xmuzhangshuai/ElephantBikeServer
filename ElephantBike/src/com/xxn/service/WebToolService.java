package com.xxn.service;

import com.xxn.dao.WebToolDao;
import com.xxn.idao.IWebToolDao;
import com.xxn.iservice.IWebToolService;

public class WebToolService implements IWebToolService{

	@Override
	public String getURL() {
		IWebToolDao iWebToolDao = new WebToolDao();
		return iWebToolDao.getURL();
	}

}
