package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.User;
import com.xxn.idao.IUserDao;

public class UserDao implements IUserDao {

	@Override
	public int getUserExist(User user) {
		int number = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from u_users where phone = ?";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getPhone());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				number = resultSet.getInt(1);
			}
			return number;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return number;
	}

	@Override
	public int addUser(User user) {
		int result = 0;
		String sql = "insert into u_users(phone,userstate,registerdate) values(?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getPhone());
			pstmt.setString(2, user.getUserstate());
			pstmt.setString(3, user.getRegisterdate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public String getUserInfo(String val, Map query) {
		String result = "";
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select "+ val + " from u_users where 1=1 ";
		for (Object object : query.keySet()) {
			String key = object.toString();
			if(null == query.get(key)){
				sql +=String.format(" and %s is null",key);
			}
			else{
				String value = query.get(key).toString();
				sql += String.format(" and %s = '%s'", key, value);
			}
		}
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public int updateUserState(User user) {
		int result = 0;
		String sql = "update u_users set userstate = ? where phone = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getUserstate());
			pstmt.setString(2, user.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

}
