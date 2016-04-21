package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Token;
import com.xxn.idao.ITokenDao;

public class TokenDao implements ITokenDao{

	@Override
	public int createToken(Token token) {
		int result = 0;
		String sql = "insert into t_token(phone,token,logintime)values(?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,token.getPhone());
			pstmt.setString(2, token.getToken());
			pstmt.setString(3, token.getLogintime());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}
	
	@Override
	public int updateToken(Token token) {
		int result = 0;
		String sql = "update t_token set token=?,logintime=? where phone = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, token.getToken());
			pstmt.setString(2, token.getLogintime());
			pstmt.setString(3, token.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public String getToken(Token token) {
		String result="";
		Connection connection = JdbcUtils_DBCP.getConnection();
		String sql = "select token from t_token where phone = ?";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, token.getPhone());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("token");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return result;
	}


}
