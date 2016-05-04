package com.xxn.idao;

import java.util.List;
import java.util.Map;

import com.xxn.entity.User;
import com.xxn.entity.UserData;

public interface IUserDao {

	/**
	 * 根据phone判断该用户是否已经注册
	 * 
	 * @param user
	 * @return
	 */
	public int getUserExist(User user);

	public int getUserExistByStunum(String stunum);

	/**
	 * 加入用户表
	 * 
	 * @param user
	 * @return
	 */
	public int addUser(User user);

	/**
	 * 更新用户状态
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserState(User user);

	public int completeUserInfo(User user);

	/**
	 * 根据查询条件获取用户某个字段信息
	 * 
	 * @param val
	 * @param query
	 * @return
	 */
	public Map<String, String> getUserInfo(Map val, Map query);

	/**
	 * 根据用户手机号码获取通过审核通过的证件URL
	 * 
	 * @param user
	 * @return idcard,stucard
	 */
	public Map<String, String> getCardURL(User user);

	// 获取条数
	public int getUserCount(Map queryParams);

	// 获取列表
	public List<User> findForPage(int start, int end, String sort,
			String order, Map queryParams);

	// 新增用户数据表的操作

	public int getNewUserCount(String joindate);

	public int addNewUserCount(String joindate);

	public int updateNewUser(String joindate);

	/**
	 * ----------------------------------------------------------------
	 */
	public int updateUser(Map val, Map query);

	/**
	 * 数据分析
	 */
	// 获取条数
	public int getUserDataCount(Map queryParams);

	// 获取列表
	public List<UserData> getUserData(int start, int end, String sort,
			String order, Map queryParams);

}
