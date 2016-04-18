package com.xxn.idao;

import java.util.List;

import com.xxn.entity.Message;

public interface IMessageDao {
	/**
	 * 获取未读消息条数 根据手机号码
	 * @param message
	 * @return
	 */
	public int getUnreadMessageCount(Message message);
	/**
	 * 获取活动中心所有消息
	 * @param message
	 * @return
	 */
	public List<Message> getAllMessage(Message message);
	
	/**
	 * 创建用户 新消息中心
	 * @param message
	 * @return
	 */
	public int createUserMessage(Message message);
	
	/**
	 *  更改用户所属消息状态
	 * @param message
	 * @return
	 */
	public int updateUserMessage(Message message);
}
