package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.User;

public interface UserDAO {

	public User getUserByName(String Name);
	
	public void saveUser(User user);
	
	public void changePasswordOfUser(String nickName, String password);
	
	public List<User> getAllusers();
	
}
