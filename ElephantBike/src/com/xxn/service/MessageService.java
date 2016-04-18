package com.xxn.service;

import java.util.List;

import com.xxn.dao.MessageDao;
import com.xxn.entity.Message;
import com.xxn.idao.IMessageDao;
import com.xxn.iservice.IMessageService;

public class MessageService implements IMessageService{

	@Override
	public int getUnreadMessageCount(Message message) {
		IMessageDao iMessageDao = new MessageDao();
		return iMessageDao.getUnreadMessageCount(message);
	}

	@Override
	public List<Message> getAllMessage(Message message) {
		IMessageDao iMessageDao = new MessageDao();
		return iMessageDao.getAllMessage(message);
	}

	@Override
	public int createUserMessage(Message message) {
		IMessageDao iMessageDao = new MessageDao();
		return iMessageDao.createUserMessage(message);
	}

	@Override
	public int updateUserMessage(Message message) {
		IMessageDao iMessageDao = new MessageDao();
		return iMessageDao.updateUserMessage(message);
	}

}
