package com.xxn.service;

import java.util.Map;

import com.xxn.dao.UserDao;
import com.xxn.entity.User;
import com.xxn.idao.IUserDao;
import com.xxn.iservice.IUserService;

public class UserService implements IUserService{

	@Override
	public int getUserExist(User user) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getUserExist(user);
	}

	@Override
	public int addUser(User user) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.addUser(user);
	}

	@Override
	public String getUserInfo(String val, Map query) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getUserInfo(val, query);
	}

	@Override
	public int updateUserState(User user) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.updateUserState(user);
	}

	@Override
	public int completeUserInfo(User user) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.completeUserInfo(user);
	}

}
