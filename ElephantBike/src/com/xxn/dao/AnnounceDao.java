package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.idao.IAnnounceDao;

public class AnnounceDao implements IAnnounceDao{

	@Override
	public int updateAnnounce(Map<String, String> val, Map<String, String> query) {
		int result = 0;
		String sql = "update cm_contentmanage set ";
		for (Object object : val.keySet()) {
			String key = object.toString();
			sql += String.format(" %s = ?,", key);
		}

		sql = sql.substring(0, sql.length() - 1);
		sql += " where 1=1 ";
		for (Object object : query.keySet()) {
			String key = object.toString();
			String value = query.get(key).toString();
			sql += String.format(" and %s = '%s'", key, value);
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
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

}
