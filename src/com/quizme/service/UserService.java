package com.quizme.service;

import com.quizme.bean.User;

public interface UserService {

	public void signUpuser(User user);

	boolean loginuser(User user);

	public boolean checkName(String name);

	public boolean checkpasswordLength(String password);
}
