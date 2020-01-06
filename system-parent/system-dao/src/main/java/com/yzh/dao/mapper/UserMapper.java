package com.yzh.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yzh.dao.pojo.User;

public interface UserMapper {
	 
	@Select("select * from user where username=#{username} and password=#{password}")
	User checkUser(User u);
	
	@Insert("insert into user values (default,#{username},#{password},#{sex},#{tel},#{money},#{status},#{comm})")
	int addUser(User u);
}
