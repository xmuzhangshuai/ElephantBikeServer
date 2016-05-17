package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xxn.butils.DateTool;
import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.User;
import com.xxn.entity.UserData;
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
		Connection connection = JdbcUtils_DBCP.getConnection();
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
	public Map<String, String> getUserInfo(Map val, Map query) {
		Map<String, String> result = new HashMap<>();
		String sql = "select ";
		for (Object object : val.keySet()) {
			String key = object.toString();
			sql += String.format(" %s,", key);
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " from u_users where 1=1 ";
		for (Object object : query.keySet()) {
			String key = object.toString();
			String value = query.get(key).toString();
			sql += String.format(" and %s = '%s'", key, value);
		}
		System.out.println(sql);
		Connection connection = JdbcUtils_DBCP.getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
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
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public int updateUserState(User user) {
		int result = 0;
		String sql = "update u_users set userstate = ? where phone = ?";
		Connection connection = JdbcUtils_DBCP.getConnection();
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
		String sql = "update u_users set name=?,stunum=?,stucardaddr=?,college=? where phone = ?";
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getStunum());
			pstmt.setString(3, user.getStucardaddr());
			pstmt.setString(4, user.getCollege());
			pstmt.setString(5, user.getPhone());
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
		String sql = "select stucardaddr,college,name,stunum from u_users where phone=?";
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getPhone());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String stucard = resultSet.getString("stucardaddr");
				String college = resultSet.getString("college");
				String name = resultSet.getString("name");
				String stunum = resultSet.getString("stunum");
				result.put("stucard", stucard);
				result.put("college", college);
				result.put("name", name);
				result.put("stunum", stunum);
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
		Connection connection = JdbcUtils_DBCP.getConnection();
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
		Connection connection = JdbcUtils_DBCP.getConnection();
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
		String sql = "select count(*) from u_users" + " where 1 = 1";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			if (key.equals("userstate"))
				if (value.equals("1") || value.equals("3")
						|| value.equals("-1"))
					sql += String.format(" and  %s ='%s' ", key, value);
				else
					// sql += String.format(" and  (%s =0 or %s =2) ", key,key);
					sql += String.format(" and  %s =2 ", key);
			else
				sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
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
		String sql = " select tmp.* from ( "
				+ " select * from u_users where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			if (key.equals("userstate"))
				if (value.equals("1") || value.equals("3")
						|| value.equals("-1"))
					sql += String.format(" and  %s ='%s' ", key, value);
				else
					sql += String.format(" and %s =2 ", key);
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
				String name = resultSet.getString("name");
				String stunum = resultSet.getString("stunum");
				String idcardaddr = resultSet.getString("idcardaddr");
				String stucardaddr = resultSet.getString("stucardaddr");
				String userstate = resultSet.getString("userstate");
				String college = resultSet.getString("college");
				String registerdate = resultSet.getString("registerdate");
				String vip = resultSet.getString("isvip");
				String vipdate = resultSet.getString("vipdate");
				// 获取余额
				IWalletDao iWalletDao = new WalletDao();
				float balance = iWalletDao.getBalance(new Wallet(phone));
				User user = new User(id, phone, name, stunum, idcardaddr,
						stucardaddr, userstate, college, registerdate, vip,
						vipdate, balance);
				resultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

	@Override
	public int updateUser(Map val, Map query) {
		int result = 0;
		String sql = "update u_users set ";
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

	@Override
	public int getUserExistByStunum(String stunum) {
		int number = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from u_users where stunum = ? and userstate=1";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stunum);
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
	public int getUserDataCount(Map queryParams) {
		int count = 0;
		String sql = "select count(*) from (select phone as tel from u_users ) as aa "
				+ "left join ( select phone,count(*) as today from wl_walletlist where phone in (select phone from u_users) and fee_time > ? and fee<0 group by phone)as bb on tel=bb.phone "
				+ "left join ( select phone,count(*) as history from wl_walletlist where phone in (select phone from u_users) and fee_time < ? and fee<0 group by phone)as cc on tel=cc.phone "
				+ "left join ( select phone,sum(fee) as todaymoney from wl_walletlist where phone in (select phone from u_users) and fee_time > ? and fee<0 group by phone)as dd on tel=dd.phone "
				+ "left join ( select phone,sum(fee) as historymoney from wl_walletlist where phone in (select phone from u_users) and fee_time < ? and fee<0 group by phone)as ff on tel=ff.phone"
				+ " where 1 = 1";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(sql);
			String today = DateTool.dateToStringYMD(new Date());
			pstmt.setString(1, today);
			pstmt.setString(2, today);
			pstmt.setString(3, today);
			pstmt.setString(4, today);
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
	public List<UserData> getUserData(int start, int end, String sort,
			String order, Map queryParams) {
		String sql = " select tel,today,history,todaymoney,historymoney from "
				+ "(select phone as tel from u_users ) as aa "
				+ "left join ( select phone,count(*) as today from wl_walletlist "
				+ "where phone in (select phone from u_users) and fee_time > ? and fee<0 group by phone)as bb on tel=bb.phone "
				+ "left join ( select phone,count(*) as history from wl_walletlist where phone in (select phone from u_users) and fee_time < ? and fee<0 group by phone)as cc on tel=cc.phone "
				+ "left join ( select phone,sum(fee) as todaymoney from wl_walletlist where phone in (select phone from u_users) and fee_time > ? and fee<0 group by phone)as dd on tel=dd.phone "
				+ "left join ( select phone,sum(fee) as historymoney from wl_walletlist where phone in (select phone from u_users) and fee_time < ? and fee<0 group by phone)as ff on tel=ff.phone "
				+ "where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
		sql += " order by " + sort + " " + order + "  limit " + start
				+ " ," + end;

		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<UserData> resultList = new ArrayList<UserData>();
		try {
			pstmt = connection.prepareStatement(sql);
			String todayStr = DateTool.dateToStringYMD(new Date());
			pstmt.setString(1, todayStr);
			pstmt.setString(2, todayStr);
			pstmt.setString(3, todayStr);
			pstmt.setString(4, todayStr);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String tel = resultSet.getString("tel");
				int today = resultSet.getInt("today");
				int history = resultSet.getInt("history");
				float todaymoney = resultSet.getFloat("todaymoney");
				float historymoney = resultSet.getFloat("historymoney");
				UserData userData = new UserData(tel, today, history, todaymoney, historymoney);
				resultList.add(userData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

}
