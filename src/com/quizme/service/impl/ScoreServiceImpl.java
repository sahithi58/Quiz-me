package com.quizme.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.quizme.bean.Score;
import com.quizme.bean.User;
import com.quizme.dao.ScoreDAO;
import com.quizme.dao.impl.ScoreDAOImpl;
import com.quizme.service.ScoreService;


public class ScoreServiceImpl implements ScoreService{

	static Logger logger = Logger.getLogger(ScoreServiceImpl.class);
	/*@author varun saket
	 * @param user bean object
	 *this method retrieves the score of a user in various quizzes and puts it in a list*/
	@Override
	public Iterator<Score> getScore(User user) {
		PropertyConfigurator.configure("log4j.properties");
		ScoreDAO sd = new ScoreDAOImpl();
		logger.info("retrieving score of the user and saving in the list in service layer");
		List<Score> list = sd.getScore(user);
		Iterator<Score> iList = list.iterator();
		return iList;
	}

	/*@author varun saket
	 * @param user bean object
	 * this method retrieves the highest score of the user amongst the attempted quizzes*/ 
	@Override
	public int getBestScore(User user,String category) {
		ScoreDAO sd = new ScoreDAOImpl();
		logger.info("retrieving the best score of the user in service layer");
		int bestscore=sd.getBestScore(user,category);
		return bestscore;
	}
	
	/*@author varun saket
	 * @param user bean object and score, category and name from the user playing quiz
	 * this method adds score retrieved from the main to the database*/ 
	@Override
	public void addScore(int score, String category, String name) {
		ScoreDAO addscore = new ScoreDAOImpl();
		logger.info("Adding score to database");
		addscore.addScore(score, category, name);
	}



}
