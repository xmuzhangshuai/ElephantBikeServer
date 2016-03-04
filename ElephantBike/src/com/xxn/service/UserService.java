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

	@Override
	public Map<String, String> getCardURL(User user) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getCardURL(user);
	}

	@Override
	public int getNewUserCount(String joindate) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getNewUserCount(joindate);
	}

	@Override
	public int addNewUserCount(String joindate) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.addNewUserCount(joindate);
	}

	@Override
	public int updateNewUser(String joindate) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.updateNewUser(joindate);
	}

	@Override
	public int getURLExist(User user) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getURLExist(user);
	}

	

}
