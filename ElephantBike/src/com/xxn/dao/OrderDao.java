package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xxn.butils.DateTool;
import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Order;
import com.xxn.idao.IOrderDao;

public class OrderDao implements IOrderDao{

	@Override
	public int createOrder(Order order) {
		int result = 0;
		String sql = "insert into o_order(orderid,phone,bikeid,starttime) values(?,?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, DateTool.date2String(new Date()));
			pstmt.setString(2, order.getPhone());
			pstmt.setString(3, order.getBikeid());
			pstmt.setString(4, order.getStarttime());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public String getOrderInfo(Order order) {
		String number = "";
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select starttime from o_order where phone = ? and bikeid =? "
				+ "and finishtime is null";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getPhone());
			preparedStatement.setString(2, order.getBikeid());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				number = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return number;
	}
	@Override
	public int updateOrder(Map val, Map query) {
		
		int result = 0;

		String sql = "update o_order set ";
		for (Object object : val.keySet()) {
			String key = object.toString();
			sql += String.format(" %s = ?,", key);
		}

		sql = sql.substring(0, sql.length() - 1);

		sql += " where 1=1 ";

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
		System.out.println(sql);
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		
			int count = 1;
			for (Object object : val.keySet()) {
				String key = object.toString();
				String value = val.get(key).toString();
				pstmt.setString(count, value);
				count++;
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int getOrderCount(Map<String, String> query) {
		int result = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from o_order where 1=1 ";
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
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public String getBikeid(Order order) {
		String number = "";
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select bikeid from o_order where phone = ? "
				+ "and (finishtime is null or paymode is null)";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getPhone());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				number = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return number;
	}

	@Override
	public Map<String, String> getOrderInfo(Map<String, String> val,
			Map<String, String> query) {
		Map<String, String> result = new HashMap<>();
		
		String sql = "select ";
		for (Object object : val.keySet()) {
			String key = object.toString();
			sql += String.format(" %s,", key);
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " from o_order where 1=1 ";
		for (Object object : query.keySet()) {
			String key = object.toString();
			if(null == query.get(key)){
				sql +=String.format(" and %s is null",key);
			}
			else{
				if("not null".equals(query.get(key))){
					sql +=String.format(" and %s is not null",key);
				}
				else{
					String value = query.get(key).toString();
					sql += String.format(" and %s = '%s'", key, value);
				}
			}
		}
		Connection connection = JdbcUtils_DBCP.getConnection();
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				for (Object object : val.keySet()) {
					String key = object.toString();
					String value = resultSet.getString(key);
					result.put(key, value);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return result;
	}

}
