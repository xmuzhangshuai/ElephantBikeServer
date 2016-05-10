package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.idao.IWebToolDao;

public class WebToolDao implements IWebToolDao{

	@Override
	public String getURL() {
		String result = "";
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select imageweb from webproject where id = 1";
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
	
	public int clearData(){
		int result = 0;
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		String[] sqlArray ={"delete from b_bike","delete from m_message",
				"delete from o_order","delete from q_question","delete from u_users",
				"delete from w_wallet","delete from wl_walletlist","delete from t_token"};
		for (String sql : sqlArray) {
			try {
				pstmt = connection.prepareStatement(sql);
				result = result + pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		JdbcUtils_DBCP.release(connection, pstmt, null);
		
		return result;
	}
}
