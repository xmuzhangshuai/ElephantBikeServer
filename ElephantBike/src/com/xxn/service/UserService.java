package com.xxn.service;

import java.util.List;
import java.util.Map;

import com.xxn.dao.UserDao;
import com.xxn.entity.User;
import com.xxn.entity.UserData;
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
	public Map<String, String> getUserInfo(Map val, Map query) {
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
	public int getUserCount(Map queryParams) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getUserCount(queryParams);
	}

	@Override
	public List<User> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.findForPage(start, end, sort, order, queryParams);
	}

	@Override
	public int updateUser(Map val, Map query) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.updateUser(val, query);
	}

	@Override
	public int getUserExistByStunum(String stunum) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getUserExistByStunum(stunum);
	}

	@Override
	public int getUserDataCount(Map queryParams) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getUserDataCount(queryParams);
	}

	@Override
	public List<UserData> getUserData(int start, int end, String sort,
			String order, Map queryParams) {
		IUserDao iUserDao = new UserDao();
		return iUserDao.getUserData(start, end, sort, order, queryParams);
	}

	

}
