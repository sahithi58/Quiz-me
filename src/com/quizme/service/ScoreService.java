package com.quizme.service;

import java.util.Iterator;

import com.quizme.bean.Score;
import com.quizme.bean.User;

public interface ScoreService {
	/*@author varun saket
	 * @param user bean object and score, category and name from the user playing quiz
	 * this is the service layer
	 * the methods given below are overridden in the implement class
	 * the below methods are overridden in the implement class*/
	public void addScore(int score, String category, String name);
	public Iterator<Score> getScore(User user);
	public int getBestScore(User user, String category);
}
