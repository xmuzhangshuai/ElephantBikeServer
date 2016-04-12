package com.xxn.service;

import java.util.List;
import java.util.Map;

import com.xxn.dao.QuestionDao;
import com.xxn.entity.Question;
import com.xxn.idao.IQuestionDao;
import com.xxn.iservice.IQuestionService;

public class QuestionService implements IQuestionService{

	@Override
	public int addQuestion(Question question) {
		IQuestionDao iQuestionDao = new QuestionDao(); 
		return iQuestionDao.addQuestion(question);
	}

	@Override
	public int getQuestionCount(Map<String, String> query) {
		IQuestionDao iQuestionDao = new QuestionDao(); 
		return iQuestionDao.getQuestionCount(query);
	}

	@Override
	public List<Question> findForPage(int start, int end, String sort,
			String order, Map queryParams) {
		IQuestionDao iQuestionDao = new QuestionDao(); 
		return iQuestionDao.findForPage(start, end, sort, order, queryParams);
	}
	
}
