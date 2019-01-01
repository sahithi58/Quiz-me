package com.quizme.dao;

import java.util.List;

import com.quizme.bean.Score;
import com.quizme.bean.User;

public interface ScoreDAO {
	
	/*@author varun saket
	 * @param user bean object and score, category and name from the user playing quiz
	 * the methods given below are overridden in the implement class*/
	public void addScore(int result, String category, String name);
	public List<Score> getScore(User user);
	public int getBestScore(User user,String category);

}
