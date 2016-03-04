package com.xxn.iservice;

import java.util.Map;

import com.xxn.entity.User;

public interface IUserService {
	public int getUserExist(User user);
	public int addUser(User user);
	public String getUserInfo(String val,Map query);
	public int updateUserState(User user);
	public int completeUserInfo(User user);
	public Map<String, String> getCardURL(User user);
}
