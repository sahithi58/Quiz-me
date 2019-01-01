package com.quizme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.quizme.bean.Score;
import com.quizme.dao.ConnectionDAO;
import com.quizme.dao.ScoreDAO;
import com.quizme.service.impl.ScoreServiceImpl;
import com.quizme.bean.User;

public class ScoreDAOImpl implements ScoreDAO {
	Connection connect;
	static Logger logger = Logger.getLogger(ScoreServiceImpl.class);

	/* @author Varun Saket
	 * @param the object of User Bean class
	 * in this method we retrieve data for score of the user from the
	 * database and save it in a string to be displayed to the user
	 */
	public List<Score> getScore(User user) {
		connect = ConnectionDAO.openConnection();
		logger.info("connection Established");
		List<Score> list = new ArrayList<Score>();
		String insertquery = "select * from score where username = ?";
		PreparedStatement p1 = null;

		try {
			logger.info("Executing query");
			p1 = connect.prepareStatement(insertquery);
			p1.setString(1, user.getName());
			ResultSet rs = p1.executeQuery();

			while (rs.next()) {
				logger.info("Copying data from database to list");
				int scoreid = rs.getInt("scoreid");
				int score = rs.getInt("score");
				String category = rs.getString("category");
				String username = rs.getString("username");
				Score score1 = new Score();
				score1.setScoreId(scoreid);
				score1.setScore(score);
				score1.setCategory(category);
				score1.setUsername(username);
				list.add(score1);
				logger.info("Information saved in the list");
			}
		} catch (SQLException e) {
			logger.error("error....."+ e);
		} finally {
			try {
				p1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionDAO.closeConnection();
			logger.info("Connection Closed");
		}
		return list;
	}

	/*@author varun saket
	 *@param the object of the user bean
	 this method retrieves the best score of the user in the attempted quizzes
	 from the database and stores it in a list to be displayed*/
	@Override
	public int getBestScore(User user,String category) {
		connect = ConnectionDAO.openConnection();
		logger.info("connection Established");
		String insertquery = "select MAX(score) from score where username =(?) and category=?";
		PreparedStatement p3 = null;
		int highscore = 0;

		try {
			p3 = connect.prepareStatement(insertquery);
			p3.setString(1, user.getName());
			p3.setString(2, category);
			ResultSet rs = p3.executeQuery();
			if (rs.next()) {
				highscore = rs.getInt(1);
			}
			else{
				System.out.println("u never attempted this category");
			}
		

			logger.info("Hishest score retrieved and saved in the variable");
		} catch (SQLException e) {
			logger.error("error...."+e);
			e.printStackTrace();
		} finally {
			try {
				p3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionDAO.closeConnection();
			logger.info("Connection Closed");
		}

		return highscore;
	}

	/*@author varun saket
	 @param the score, category and name of the user who has played the quiz the quiz
	 this method is used to add the scores of the user after taking the quiz
	 to the database*/
	@Override
	public void addScore(int result, String category, String name) {
		connect = ConnectionDAO.openConnection();
		logger.info("connection Established");

		PreparedStatement p2 = null;
		try {
			String insertscore = "insert into score(score, category, username) values(?, ?, ?)";
			p2 = connect.prepareStatement(insertscore);
			p2.setInt(1, result);
			p2.setString(2, category);
			p2.setString(3, name);
			logger.info("Information stored in the database");
			p2.executeQuery();
		} catch (SQLException e) {
			logger.info("error....."+e);
			System.out.println("exception in add score");
		} finally {
			try {
				p2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionDAO.closeConnection();
			logger.info("Connection Closed");
		}
	}

}
