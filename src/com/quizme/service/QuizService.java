package com.quizme.service;

import java.util.Iterator;


import com.quizme.bean.Question;

public interface QuizService {
	public void addQuestion(Question question);
	public Iterator<Question> getQuiz(String category);
}
