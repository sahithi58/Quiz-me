package com.quizme.service.impl;


import java.util.Iterator;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.quizme.bean.Question;
import com.quizme.dao.QuizDAO;
import com.quizme.dao.impl.QuizDAOImpl;
import com.quizme.service.QuizService;


public class QuizServiceImpl implements QuizService{

	QuizDAO quizD = new QuizDAOImpl();
	
	/**
	 * @author Satya S Vundavalli
	 * @param Question object
	 * 
	 * Description: This method sends the question object to QuizDAO 
	 * interface which will then be saved in the database.
	 */
	
	@Override
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("log4j.properties");
		quizD.addQuestion(question);
	}

	/**
	 * @author Satya S Vundavalli
	 * @param Category name
	 * 
	 * Description: This method sends the category entered by the user to getQuiz 
	 * method in QuizDAO which then returns a list containing questions from
	 * category selelcted. 
	 */
	
	@Override
	public Iterator<Question> getQuiz(String category) {
		// TODO Auto-generated method stub
		List<Question> qList = quizD.getQuiz(category);
		Iterator<Question> iList = qList.iterator();
		return iList;
	}
	
}
