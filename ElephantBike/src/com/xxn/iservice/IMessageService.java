package com.xxn.iservice;

import java.util.List;

import com.xxn.entity.Message;

public interface IMessageService {
	
	public int getUnreadMessageCount(Message message);
	public List<Message> getAllMessage(Message message);
	public int createUserMessage(Message message);
	public int updateUserMessage(Message message);
	
}
