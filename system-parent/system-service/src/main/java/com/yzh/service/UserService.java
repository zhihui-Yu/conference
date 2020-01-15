package com.yzh.service;

import java.util.List;

import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.User;

public interface UserService {
	
	/**
	 * 删除指定用户的爱好
	 * @param uid 用户id
	 * @return
	 */
	int delUserFav(int uid);
	
	/**
	 * 保存用户的反馈信息
	 * 
	 * @param uid 用户id
	 * @param msg 提交的信息
	 * @return
	 */
	int insUserMsg(int uid,String msg);
	
	/**
	 * 通过用户的id查找用户爱好
	 * @param uid
	 * @return
	 */
	List<Fav> selFavByUid(int uid);
	
	/**
	 * 保存uid对应的爱好
	 * @param fav
	 * @return
	 */
	int insFavByUid(Fav fav);
	
	/**
	 * 查找所有的爱好
	 * @return
	 */
	List<Favorite> selAllFavorite();
	
	/**
	 * 登入
	 * @param u
	 * @return
	 */
	public User selUser(User u);
	
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
