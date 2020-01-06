package com.yzh.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;

public interface AdminService {
	/**
	 * 查找管理员
	 * @param admin
	 * @return
	 */
	Admin checkAdmin(Admin admin);
	
	/**
	 * 查找可容纳人数
	 * @return
	 */
	List<PeoNum> selPeoNum();
	
	/**
	 * 插入会议信息
	 * @param c
	 * @return
	 */
	int insConfer(ConferInfor c);
	
	/**
	 * 插入会议的图片
	 * @param img
	 * @return
	 */
	int insImg(Img img);
	
	/**
	 * 查找会议cid 通过名字
	 * @param name
	 * @return
	 */
	ConferInfor selConferByName(String name);
	
	/**
	 * 查找所有的会议室信息
	 * @return
	 */
	List<ConferInfor> selAllConferInfor();
	
	/**
	 * 查找相应cid的图片
	 * @param cid
	 * @return
	 */
	List<Img> selImgByConferId(int cid);
	
	/**
	 * 删除会议室信息
	 * @param name
	 * @return
	 */
	int delConfer(String name);
	
	/**
	 * 删除会议室相关的图片信息
	 * @param name
	 * @return
	 */
	int delImgByCid(int cid);
	
	/**
	 * 根据id更新会议室信息
	 * @param c
	 * @return
	 */
	int updConfer(ConferInfor c);
	
	/**
	 * 根据imgid修改图片路径
	 * @param img
	 * @return
	 */
	int updImg(Img img);
	
	/**
	 * 通过名字查找用户
	 * @param name
	 * @return
	 */
	List<User> selUserByName(String name);
	
	/**
	 * 查找所有用户
	 * @return
	 */
	List<User> selUser();
	
	/**
	 * 通过用户的id查找用户爱好
	 * @param uid
	 * @return
	 */
	List<Fav> selFavByUid(int uid);
	
	/**
	 * 插入用户爱好
	 * @param fav
	 * @return
	 */
	int insFav(Fav fav);
	
	/**
	 * 通过uid查找用户
	 * @param id
	 * @return
	 */
	User selUserById(int id);
	
	/**
	 * 根据uid更新user
	 * @param id
	 * @return
	 */
	int updUserById(User u);
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updUserStatusById(int id,int status);
	
	/**
	 * 查找响应密码的管理员是不是存在
	 * @param password
	 * @return
	 */
	Admin selAdminByPassword(String password);
	
	/**
	 * 修改管理员密码
	 * @param admin
	 * @return
	 */
	int updAdminPassword(Admin admin);
}
