package com.quizme.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.quizme.bean.User;
import com.quizme.dao.ConnectionDAO;
import com.quizme.dao.UserDAO;
import com.quizme.exceptions.UserNameExistsException;
import com.quizme.exceptions.UserNotFoundException;
import com.quizme.exceptions.WrongPasswordException;

public class UserDAOImpl implements UserDAO {

	static Connection connection;
	static Logger logger = Logger.getLogger(UserDAOImpl.class);
	/**
	 * @author Satya shree
	 * @param sign up for the user
	 * Description:This method takes up a user object and adds it up to the database if they sign up for the first time.
	 */

	@Override
	public User signUp(User user) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Creating your account...");
		PreparedStatement statement = null;

		boolean flag = false;
		try {
			connection = ConnectionDAO.openConnection();

			if (!flag) {
				String Query1 = "insert into player(name,password) values(?,?)";

				statement = connection.prepareStatement(Query1);

				statement.setString(1, user.getName());
				statement.setString(2, user.getPassword());

				statement.execute();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}finally {
			try {
				if (connection != null){
					connection.close();
					logger.info("Connection closed");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return user;

	}
	
	/**
	 * @author Satya shree
	 * @param login for the user
	 * Description:This method takes up a user object and checks if the users credentials are matching with the database. If they do it, logs them in
	 * if they dont it throws an exception.
	 */


	@Override

	public boolean loginuser(User user) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Logging in...");
		PreparedStatement statement = null;
		User loggingUser = new User();
		loggingUser = user;
		String coulmnpassword = null;
		connection = ConnectionDAO.openConnection();
		boolean flag = false;
		boolean checkingflag = false;
		String query = "select password from player where name=?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, loggingUser.getName());
			ResultSet rs = statement.executeQuery();
			checkingflag = rs.next();
			if (!checkingflag) {
				try {
					flag = false;
					throw new UserNotFoundException();
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				coulmnpassword = rs.getString("password");

				if (loggingUser.getPassword().equals(coulmnpassword)) {
					flag = true;
					logger.info("user is successfully logged in");
				} else {
					try {
						flag = false;
						throw new WrongPasswordException();
					} catch (WrongPasswordException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e){
			logger.error(e.getMessage());	
		}
		return flag;

	}
	
	/**
	 * @author Phanindra Pvs
	 * @param checkName method
	 * Description:This method takes up name from the console and checks whether the name is exists or not.If it exists it throws UsernameException 
	 */

	
	@Override
	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		connection = ConnectionDAO.openConnection();
		boolean flag = true;
		String inputname = name;
		String Query = "select name from player where name=?";
		try {
			System.out.println("uin try");
			statement = connection.prepareStatement(Query);
			statement.setString(1, inputname);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String name_in_data = rs.getString("name");
				if (inputname.equals(name_in_data)) {
					flag = false;
					System.out.println("user exists");
				throw new UserNameExistsException();

				}
			}
		}catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (UserNameExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null){
					connection.close();
					logger.info("Connection closed");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return flag;
	}

}
