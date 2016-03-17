package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.College;
import com.xxn.idao.ICollegeDao;

public class CollegeDao implements ICollegeDao{

	@Override
	public int addArea(College college) {
		int result = 0;
		String sql = "insert into c_college(latlng,college) values(?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, college.getlatlng());
			pstmt.setString(2, college.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public List<String> getAddrByCollege(College college) {
		List<String> res = new ArrayList<>();
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select latlng from c_college where college = ?";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, college.getName());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String latlng = resultSet.getString(1);
				res.add(latlng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return res;
	}

	@Override
	public List<College> getDistinctName() {
		
		List<College> res = new ArrayList<>();
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select distinct(college),collegeid from c_college;";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString(1);
				String collegeid = resultSet.getString(2);
				College college = new College("", name, collegeid);
				res.add(college);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return res;
	}
}
