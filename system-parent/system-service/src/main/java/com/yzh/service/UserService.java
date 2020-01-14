package com.yzh.service;

import com.yzh.dao.pojo.User;

public interface UserLoginService {
	/**
	 * 登入
	 * @param u
	 * @return
	 */
	public User checkUser(User u);
	
	/**
	 * 注册
	 * @param u
	 * @return
	 */
	public int addUser(User u);
	
	/**
	 * 根据uid更新user
	 * @param id
	 * @return
	 */
	int updUserById(User u);
	
	/**
	 * 修改密码
	 * 根据uid更新user
	 * @param id
	 * @return
	 */
	int updUserPassword(User u);
}
