package com.xxn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.xxn.butils.JdbcUtils_DBCP;
import com.xxn.entity.Question;
import com.xxn.idao.IQuestionDao;

public class QuestionDao implements IQuestionDao{

	@Override
	public int addQuestion(Question question) {
		int result = 0;
		String sql = "insert into q_question(phone,bikeid,type,voiceproof) "
				+ "values(?,?,?,?,?,?)";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, question.getPhone());
			pstmt.setString(2, question.getBikeid());
			pstmt.setString(3, question.getType());
			pstmt.setString(4, question.getVoiceproof());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, pstmt, null);
		}
		return result;
	}

}
