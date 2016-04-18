package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xxn.butils.DateTool;
import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Order;
import com.xxn.entity.Question;
import com.xxn.idao.IQuestionDao;

public class QuestionDao implements IQuestionDao{

	@Override
	public int addQuestion(Question question) {
		int result = 0;
		String sql = "insert into q_question(phone,bikeid,type,voiceproof,issolved,createtime,needfrozen) "
				+ "values(?,?,?,?,?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, question.getPhone());
			pstmt.setString(2, question.getBikeid());
			pstmt.setString(3, question.getType());
			pstmt.setString(4, question.getVoiceproof());
			pstmt.setInt(5, question.getIssolved());
			pstmt.setString(6, question.getCreatetime());
			pstmt.setString(7, question.getNeedfrozen());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

	@Override
	public int getQuestionCount(Map<String, String> query) {
		int result = 0;
		Connection connection = null;
		connection = JdbcUtils_DBCP.getConnection();
		String sql = "select count(*) from q_question where 1=1 ";
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
	public List<Question> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		String sql = " select tmp.* from ( "
				+ " select * from q_question where 1=1 ";
		for (Object object : queryParams.keySet()) {
			String key = object.toString();
			String value = queryParams.get(key).toString();
			sql += String.format(" and  %s like '%%%s%%' ", key, value);
		}
		sql += " order by " + sort + " " + order + " ) tmp limit " + start
				+ " ," + end;

		Connection connection = JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Question> resultList = new ArrayList<Question>();
		try {
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String phone = resultSet.getString("phone");
				String bikeid =  resultSet.getString("bikeid");
				String type =  resultSet.getString("type");
				String voiceproof = resultSet.getString("voiceproof");
				int issolved = resultSet.getInt("issolved");
				String createtime = resultSet.getString("createtime");
				String needfrozen = resultSet.getString("needfrozen");
				String dealtime = resultSet.getString("dealtime");
				Question question = new Question(id, phone, bikeid, type, voiceproof,
						issolved, createtime, needfrozen,dealtime);
				resultList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, resultSet);
		}
		return resultList;
	}

	@Override
	public int dealQuestion(String id) {
		int result = 0;
		String sql = "update q_question set issolved= ?,dealtime=? where id =?";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, DateTool.dateToString(new Date()));
			pstmt.setString(3, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

}
