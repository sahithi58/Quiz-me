package com.quizme.dao;

import com.quizme.bean.User;

public interface UserDAO {

	public boolean loginuser(User user);

	public User signUp(User user);

	public boolean checkName(String name);

}
