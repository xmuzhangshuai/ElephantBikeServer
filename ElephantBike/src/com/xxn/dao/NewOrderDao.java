package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.NewOrder;
import com.xxn.entity.Question;
import com.xxn.entity.TotalData;
import com.xxn.idao.INewOrderDao;

public class NewOrderDao implements INewOrderDao{

	@Override
	public int getNewOrderCount(String date) {
		int result = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from no_neworder where date = ?";
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, date);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return result;
	}

	@Override
	public int addNewOrder(NewOrder newOrder) {
		int result = 0;
		String sql = "insert into no_neworder(date,count,totalfee) values(?,?,?)";
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, newOrder.getDate());
			pstmt.setInt(2, newOrder.getCount());
			pstmt.setFloat(3, newOrder.getTotalfee());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int updateNewOrder(NewOrder newOrder) {
		int result = 0;
		String sql = "update no_neworder set count = count + ?,totalfee=totalfee+? where date = ?";
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, newOrder.getCount());
			pstmt.setFloat(2, newOrder.getTotalfee());
			pstmt.setString(3, newOrder.getDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int getTotalDataCount(Map<String, String> query) {
		int result = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from "
				+ "(select date,count,totalfee,tmp.totaluser,count/tmp.totaluser as averusercount,"
				+ "totalfee/tmp.totaluser as averuserfee,tmp2.totalbike,count/tmp2.totalbike as averbikecount,"
				+ "totalfee/tmp2.totalbike as averbikefee "
				+ "from no_neworder,(select count(*) as totaluser from u_users)tmp,(select count(*) as totalbike from b_bike)tmp2) aa "
				+ "left join (select amount,joindate from nu_newusers)bb on aa.date=bb.joindate where 1=1 ";
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
	public List<TotalData> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		String sql = "select * from "
				+ "(select date,count,totalfee,tmp.totaluser,count/tmp.totaluser as averusercount,"
				+ "totalfee/tmp.totaluser as averuserfee,tmp2.totalbike,count/tmp2.totalbike as averbikecount,"
				+ "totalfee/tmp2.totalbike as averbikefee "
				+ "from no_neworder,(select count(*) as totaluser from u_users)tmp,(select count(*) as totalbike from b_bike)tmp2) aa "
				+ "left join (select amount,joindate from nu_newusers)bb on aa.date=bb.joindate where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
		sql += " order by " + sort + " " + order + "  limit " + start
				+ " ," + end;

		System.out.println(sql);
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<TotalData> resultList = new ArrayList<TotalData>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String date = resultSet.getString("date");
				int count =  resultSet.getInt("count");
				float totalfee =  resultSet.getFloat("totalfee");
				int totaluser = resultSet.getInt("totaluser");
				float averusercount = resultSet.getFloat("averusercount");
				float averuserfee = resultSet.getFloat("averuserfee");
				int newuser = resultSet.getInt("amount");
				int newvip = 0;
				int totalbike = resultSet.getInt("totalbike");
				float averbikecount = resultSet.getFloat("averbikecount");
				float averbikefee = resultSet.getFloat("averbikefee");
				TotalData totalData = new TotalData(date, count, totalfee,
						totaluser, averusercount, averuserfee, newuser, 
						newvip, totalbike, averbikecount, averbikefee);
				resultList.add(totalData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

}
