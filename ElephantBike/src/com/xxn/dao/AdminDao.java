package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tencent.common.MD5;
import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Admin;
import com.xxn.idao.IAdminDao;

public class AdminDao implements IAdminDao{

	@Override
	public int login(Admin admin) {
		int count = 0;
		String sql = "select count(*) from d_dxdc where account=? and password = ?";
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, MD5.MD5Encode(admin.getAccount()));
			pstmt.setString(2, MD5.MD5Encode(admin.getPassword()));
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return count;
	}

}
