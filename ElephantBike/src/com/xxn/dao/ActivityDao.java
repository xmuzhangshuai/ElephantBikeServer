package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.constants.BikeConstants;
import com.xxn.entity.Activity;
import com.xxn.idao.IActivityDao;

public class ActivityDao implements IActivityDao{

	@Override
	public int createActivty(Activity activity) {
		return 0;
	}

	@Override
	public List<Activity> getHotActivty(Activity activity) {
		List<Activity> reslist = new ArrayList<>();
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select imageurl,linkurl from h_hotactivity where isunder = ? ";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, activity.getIsunder());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String imageurl = resultSet.getString(1);
				String linkurl = resultSet.getString(2);
				Activity ac = new Activity(imageurl, linkurl, activity.getIsunder());
				reslist.add(ac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return reslist;
	}

}
