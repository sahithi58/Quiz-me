package com.quizme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.quizme.bean.Question;
import com.quizme.dao.ConnectionDAO;
import com.quizme.dao.QuizDAO;

public class QuizDAOImpl implements QuizDAO {

	static Connection connection;
	static Logger logger = Logger.getLogger(QuizDAOImpl.class);

	/**
	 * @author Satya S Vundavalli
	 * @param Question
	 *            object
	 * 
	 *            Description: The question object is used to add questions to
	 *            the database. Admin does it.
	 */

	@Override
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("log4j.properties");
		connection = ConnectionDAO.openConnection();
		logger.info("Connection Established");
		String insertQuestion = "insert into Question values(?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(insertQuestion);
			statement.setString(1, question.getQuestion());
			statement.setString(2, question.getChoice1());
			statement.setString(3, question.getChoice2());
			statement.setString(4, question.getChoice3());
			statement.setString(5, question.getChoice4());
			statement.setString(6, question.getRightChoice());
			statement.setString(7, question.getCategory());
			statement.execute();
			logger.info("Statement executed");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
					logger.info("Connection closed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * @author Phanindra Pvs
	 * @param getQuiz
	 *            method
	 * 
	 *            Description: The getQuiz Method is used to get questions from
	 *            the database to the console.
	 */

	@Override
	public List<Question> getQuiz(String category) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Preparing quiz for you...");
		connection = ConnectionDAO.openConnection();
		String cate = category;

		PreparedStatement statement = null;

		List<Question> list = new ArrayList<>();
		try {
			if (cate.equalsIgnoreCase("general")) {
				// String query = "SELECT * FROM (SELECT * from question ORDER
				// BY dbms_random.value ) WHERE rownum =5";
				String query = "select * from question";
				System.out.println("working query");
				statement = connection.prepareStatement(query);
			} else {
				// String query = "select * from question where qid=(SELECT qid
				// FROM ( SELECT qid FROM question ORDER BY dbms_random.value
				// where category=?) WHERE rownum =1 ";
				String query = "select * from question where category=?";
				statement = connection.prepareStatement(query);
				statement.setString(1, cate);
			}
			ResultSet rs = statement.executeQuery();
			System.out.println("result set");

			while (rs.next()) {

				String question = rs.getString(1);
				String choice1 = rs.getString(2);
				String choice2 = rs.getString(3);
				String choice3 = rs.getString(4);
				String choice4 = rs.getString(5);
				String rightChoice = rs.getString(6);
				String category1 = rs.getString(7);
				Question quest = new Question();
				quest.setCategory(category1);
				quest.setChoice1(choice1);
				quest.setChoice2(choice2);
				quest.setChoice3(choice3);
				quest.setChoice4(choice4);
				quest.setRightChoice(rightChoice);
				quest.setQuestion(question);
				list.add(quest);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		finally {
			try {
				if (connection != null) {
					connection.close();
					// logger.info("Connection closed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// logger.error(e.getMessage());
			}
		}
		return list;
	}

}
