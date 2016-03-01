package com.xxn.idao;

import java.util.Map;

import com.xxn.entity.User;

public interface IUserDao {
	
	/**
	 * 根据phone判断该用户是否已经注册
	 * @param user
	 * @return
	 */
	public int getUserExist(User user);
	
	/**
	 * 加入用户表
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * 更新用户状态
	 * @param user
	 * @return
	 */
	public int updateUserState(User user);
	/**
	 * 根据查询条件获取用户某个字段信息
	 * @param val
	 * @param query
	 * @return
	 */
	public String getUserInfo(String val,Map query);
}
