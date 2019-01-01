package com.quizme.dao;

import java.util.List;

import com.quizme.bean.Question;


public interface QuizDAO {
	void addQuestion(Question question);
	List<Question> getQuiz(String category);
}
