package com.yzh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzh.dao.mapper.UserMapper;
import com.yzh.dao.pojo.User;
import com.yzh.service.UserService;
@Service
public class UserLoginServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public User checkUser (User u) {
		return userMapper.checkUser(u);
	}

	@Override
	public int addUser(User u) {
		return userMapper.addUser(u);
	}

}
