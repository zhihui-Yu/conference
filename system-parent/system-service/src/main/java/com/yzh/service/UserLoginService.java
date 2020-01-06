package com.yzh.service;

import com.yzh.dao.pojo.User;

public interface UserLoginService {
	/**
	 * check user whether exist
	 * @param u
	 * @return
	 */
	public User checkUser(User u);
	
	/**
	 * add user information
	 * @param u
	 * @return
	 */
	public int addUser(User u);
}
