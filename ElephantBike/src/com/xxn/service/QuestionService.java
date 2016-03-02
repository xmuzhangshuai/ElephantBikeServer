package com.xxn.service;

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
	
}
