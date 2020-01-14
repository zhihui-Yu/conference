package com.yzh.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzh.dao.pojo.User;

public interface UserMapper {
	 
	/**
	 * 登入控制器
	 * 查找相应的账号密码是不是存在
	 * @param u
	 * @return
	 */
	@Select("select * from user where username=#{username} and password=#{password}")
	User checkUser(User u);
	
	/**
	 * 注册控制器
	 * 添加用户
	 * @param u
	 * @return
	 */
	@Insert("insert into user values (default,#{username},#{password},#{sex},#{tel},#{money},#{status},"
			+ "#{birth},#{address},#{comm})")
	int addUser(User u);
	
	/**
	 * 修改个人信息
	 * 根据uid更新user
	 * @param id
	 * @return
	 */
	@Update("update user set username =#{username} , password = #{password},sex=#{sex},tel=#{tel}"
			+ ",address=#{address},birth=#{birth},status=#{status},comm=#{comm} where uid = #{uid}")
	int updUserById(User u);
	
	/**
	 * 修改密码
	 * 根据uid更新user
	 * @param id
	 * @return
	 */
	@Update("update user set  password = #{password} where uid = #{uid}")
	int updUserPassword(User u);
	
	/**
	 * 充值
	 * 修改表中的金额
	 * @param uid
	 * @param moeny
	 * @return
	 */
	@Update("update user set money = #{1} where uid = #{0}")
	int updUserMoney(int uid,double moeny);
}
