package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xxn.butils.DateTool;
import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Bike;
import com.xxn.idao.IBikeDao;

public class BikeDao implements IBikeDao{

	@Override
	public int addBike(Bike bike) {
		int result = 0;
		String sql = "insert into b_bike(bikeid,state,college,usedtime,m,n,lastpass)values(?,?,?,?,?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bike.getBikeid());
			pstmt.setInt(2, bike.getState());
			pstmt.setString(3, bike.getCollege());
			pstmt.setString(4, bike.getUsedtime());
			pstmt.setInt(5, 1);
			pstmt.setInt(6, 2);
			pstmt.setString(7, "");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public Bike getBikeMN(Bike bike) {
		Bike res = null;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select m,n,lastpass from b_bike where bikeid = ?";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bike.getBikeid());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int m = resultSet.getInt(1);
				int n = resultSet.getInt(2);
				String lasspass = resultSet.getString(3);
				res = new Bike(bike.getBikeid(),lasspass, m, n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return res;
	}

	@Override
	public int addBikeMN(Bike bike) {
		int result = 0;
		String sql = "update b_bike set m = m+1, n=n+1,lastpass=? where bikeid = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bike.getLastpass());
			pstmt.setString(2, bike.getBikeid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int resetBikeMN(Bike bike) {
		int result = 0;
		String sql = "update b_bike set m =1, n=2 where bikeid = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bike.getBikeid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int isCanUsed(Bike bike) {
		int count = -1 ;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select state from b_bike where bikeid = ? ";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bike.getBikeid());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("state");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return count;
	}

	@Override
	public int updateBikeState(Bike bike) {
		int result = 0;
		String sql = "update b_bike set state = ?,lastusedtime=? where bikeid = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bike.getState());
			pstmt.setString(2, DateTool.dateToStringYMD(new Date()));
			pstmt.setString(3, bike.getBikeid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

}
