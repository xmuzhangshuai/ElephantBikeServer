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
import com.xxn.entity.Wallet;
import com.xxn.idao.IWalletDao;

public class WalletDao implements IWalletDao{

	@Override
	public int createWallet(Wallet wallet) {
		int result = 0;
		String sql = "insert into w_wallet(phone,balance,usedcount) values(?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, wallet.getPhone());
			pstmt.setFloat(2, wallet.getBalance());
			pstmt.setInt(3, wallet.getUsedcount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int rechargeWallet(Wallet wallet) {
		int result = 0;
		String sql = "update w_wallet set balance = balance+? where phone=?";
		System.out.println(sql);
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, wallet.getBalance());
			pstmt.setString(2, wallet.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int addWalletList(Wallet wallet) {
		int result = 0;
		String sql = "insert into wl_walletlist(phone,fee,fee_time) values(?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, wallet.getPhone());
			pstmt.setFloat(2, wallet.getFee());
			pstmt.setString(3, wallet.getFee_time());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getBalancelist(Wallet wallet, int count) {
		List<Map<String, Object>> reslist = new ArrayList<>();
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select fee,fee_time from wl_walletlist where phone = ? order by fee_time desc"
				+ " limit "+ (count*BikeConstants.BAL_COUNT)+","+BikeConstants.BAL_COUNT;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, wallet.getPhone());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Map<String, Object> res = new HashMap<>();
				float fee = resultSet.getFloat(1);
				String fee_time = resultSet.getString(2);
				res.put("fee", fee);
				res.put("fee_time", fee_time);
				reslist.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return reslist;
	}

	@Override
	public float getBalance(Wallet wallet) {
		float number = 0.0f;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select balance from w_wallet where phone = ?";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, wallet.getPhone());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				number = resultSet.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		return number;
	}

	@Override
	public int batchRecharge(float val,String[] userphones) {
		int result = 0;
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		for (String userphone : userphones) {
			String sql = "update w_wallet set balance = balance+? where phone=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setFloat(1, val);
				pstmt.setString(2, userphone);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		JdbcUtils_DBCP.release(connection, pstmt, null);
		return result;
	}

}
