package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Message;
import com.xxn.idao.IMessageDao;

public class MessageDao implements IMessageDao{

	@Override
	public int getUnreadMessageCount(Message message) {
		int number = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from m_message where phone = ? && state=?";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, message.getPhone());
			preparedStatement.setInt(2, message.getState());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				number = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return number;
	}

	@Override
	public List<Message> getAllMessage(Message message) {
		String sql = "select * from m_message where phone=? order by id desc";
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Message> resultList = new ArrayList<Message>();
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, message.getPhone());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String phone = resultSet.getString("phone");
				String title = resultSet.getString("title");
				String createtime = resultSet.getString("createtime");
				String content = resultSet.getString("content");
				int state = resultSet.getInt("state");
				Message m = new Message(id, phone, title, createtime, content, state);
				resultList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

	@Override
	public int createUserMessage(Message message) {
		int result = 0;
		String sql = "insert into m_message(phone,title,createtime,content,state)values(?,?,?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, message.getPhone());
			pstmt.setString(2, message.getTitle());
			pstmt.setString(3, message.getCreatetime());
			pstmt.setString(4, message.getContent());
			pstmt.setInt(5, message.getState());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int updateUserMessage(Message message) {
		int result = 0;
		String sql = "update m_message set state = ? where phone = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, message.getState());
			pstmt.setString(2, message.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}
}
