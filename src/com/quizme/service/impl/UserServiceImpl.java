package com.quizme.service.impl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.quizme.bean.User;
import com.quizme.dao.UserDAO;
import com.quizme.dao.impl.UserDAOImpl;
import com.quizme.exceptions.PasswordInvalidException;
import com.quizme.service.UserService;

public class UserServiceImpl implements UserService {

	static Logger logger = Logger.getLogger(UserServiceImpl.class);
	/**
	 * @author Satya shree
	 * @param sign up for the user
	 * Description:This method passes the user info  to teh DAO class for the service layer to talk to the database for signing up of the user
	 */

	
	@Override
	public void signUpuser(User user) {
		PropertyConfigurator.configure("log4j.properties");
		UserDAO userdao = new UserDAOImpl();
		userdao.signUp(user);
	}
	/**
	 * @author Satya shree
	 * @param sign up for the user
	 * Description:This method passes the user info  to the DAO class for the service layer to talk to the database for logging of the user.
	 */

	@Override
	public boolean loginuser(User user) {
		// TODO Auto-generated method stub
		UserDAO  userdao = new UserDAOImpl();
		return userdao.loginuser(user);
	}


	@Override
	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		logger.info("Checking if your username is available...");
		UserDAO userdao=new UserDAOImpl();
		return userdao.checkName(name);
	}


	@Override
	public boolean checkpasswordLength(String password) {
		int l = password.length();
		boolean flag = false;
		if (l < 10 && l > 4) {
			flag = true;
		} else {
			try {
				throw new PasswordInvalidException();
			} catch (PasswordInvalidException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}


	

}
