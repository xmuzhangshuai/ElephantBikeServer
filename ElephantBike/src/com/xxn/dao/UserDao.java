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
import com.xxn.entity.User;
import com.xxn.entity.Wallet;
import com.xxn.idao.IUserDao;
import com.xxn.idao.IWalletDao;

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

	@Override
	public int completeUserInfo(User user) {
		int result = 0;
		String sql = "update u_users set idcardaddr = ? , stucardaddr = ? where phone = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getIdcardaddr());
			pstmt.setString(2, user.getStucardaddr());
			pstmt.setString(3, user.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public Map<String, String> getCardURL(User user) {
		Map<String, String> result = new HashMap<>();
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select idcardaddr,stucardaddr from u_users where phone=?";
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getPhone());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String idcard = resultSet.getString(1);
				String stucard = resultSet.getString(2);
				result.put("idcard", idcard);
				result.put("stucard", stucard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return result;
	}
	
	@Override
	public int getURLExist(User user) {
		int result = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from u_users where phone=? and userstate=0 "
				+ "and idcardaddr is not null and stucardaddr is not null";
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getPhone());
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
	public int getNewUserCount(String joindate) {
		int result = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from nu_newusers where joindate = ?";
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, joindate);
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
	public int addNewUserCount(String joindate) {
		int result = 0;
		String sql = "insert into nu_newusers(joindate,amount) values(?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, joindate);
			pstmt.setInt(2, 1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int updateNewUser(String joindate) {
		int result = 0;
		String sql = "update nu_newusers set amount = amount + 1 where joindate = ?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, joindate);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int getUserCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from u_users"
				+ " where 1 = 1";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			if(key.equals("userstate"))
				if(value.equals("1"))
					sql += String.format(" and  %s ='%s' ", key, value);
				else 
					sql += String.format(" and  %s <>1 ", key);
			else
				sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
		System.out.println(sql);
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
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

	@Override
	public List<User> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		String sql = " select tmp.* from ( " + " select * from u_users where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			if(key.equals("userstate"))
				if(value.equals("1"))
					sql += String.format(" and  %s ='%s' ", key, value);
				else 
					sql += String.format(" and  %s <>1 ", key);
			else
				sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
		sql += " order by " + sort + " " + order + " ) tmp limit " + start
				+ " ," + end;

		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<User> resultList = new ArrayList<User>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String phone = resultSet.getString("phone");
				String wxid = resultSet.getString("wxid");
				String idcardaddr = resultSet.getString("idcardaddr");
				String stucardaddr = resultSet.getString("stucardaddr");
				String userstate = resultSet.getString("userstate");
				String college = resultSet.getString("college");
				String registerdate = resultSet.getString("registerdate");
				String vip = "",vipdate="";
				//获取余额
				IWalletDao iWalletDao = new WalletDao();
				float balance = iWalletDao.getBalance(new Wallet(phone));
				User user = new User(id, phone, wxid, idcardaddr, stucardaddr, 
						userstate, college, registerdate, vip, vipdate, balance);
				resultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

}
