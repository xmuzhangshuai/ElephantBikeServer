package com.xxn.idao;

import java.util.List;
import java.util.Map;

import com.xxn.entity.Question;

public interface IQuestionDao {
	
	public int addQuestion(Question question);
	public int getQuestionCount(Map<String, String> query);
	public List<Question> findForPage(int start, int end, String sort,
			String order, Map queryParams);
	
}
