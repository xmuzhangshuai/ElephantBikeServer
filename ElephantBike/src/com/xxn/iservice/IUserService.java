package com.xxn.iservice;

import java.util.List;
import java.util.Map;

import com.xxn.entity.User;

public interface IUserService {
	public int getUserExist(User user);
	public int getUserExistByStunum(String stunum);
	public int addUser(User user);
	public Map<String, String> getUserInfo(Map val,Map query);
	public int updateUserState(User user);
	public int completeUserInfo(User user);
	public Map<String, String> getCardURL(User user);
	public int getUserCount(Map queryParams);
	public List<User> findForPage(int start, int end, String sort,String order, Map queryParams);
	public int updateUser(Map val,Map query);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int getNewUserCount(String joindate);
	public int addNewUserCount(String joindate);
	public int updateNewUser(String joindate);
	
}
