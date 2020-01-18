package com.yzh.service;

import java.util.Date;
import java.util.List;

import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;

public interface UserService {
	
	/**
	 * 计算相应条件的confer 的长度
	 * 
	 * @param address   地址
	 * @param size		大小
	 * @param peoCount	可容纳人数
	 * @param time		时间
	 * @return
	 */
	int selConferInfoCount(String address, int size, String peoCount, String time);
	
	/**
	 * 查找相应条件的confer
	 * 
	 * @param address	地址
	 * @param size		大小
	 * @param peoCount	可容纳人数
	 * @param time	  	时间
	 * @param pageNum 	页码
	 * @param pageSize 	页面大小
	 * @return
	 */
	List<ConferInfor> selConferInfo(String address, int size, String peoCount, String time,int pageNum, int pageSize);
	
	
	/**
	 * 查找可容纳人数
	 * @return
	 */
	List<PeoNum> selPeoNum();
	
	/**
	 * 查找对应信息的未完成订单
	 * @param name
	 * @return
	 */
	List<Approve> selApproveByName(String name, int pageNum, int pageSize);
	
	/**
	 * 查询对应信息的所有订单
	 * @param name
	 * @return
	 */
	List<Approve> selAllApproveByName(String name, int pageNum, int pageSize);
	
	/**
	 * 找用户对应的全部信息的总数
	 * @param name 会议室名
	 * @return
	 */
	int selAllCountByName(String name);
	
	/**
	 * 找用户对应的预约信息的总数
	 * @param name 会议室名
	 * @return
	 */
	int selCountByName(String name);
	
	/**
	 * 找用户对应的预约信息的总数
	 * @param uname
	 * @return
	 */
	int selAllApproveCount();
	
	/**
	 * 找用户对应的预约信息为完成的总数
	 * @param uname
	 * @return
	 */
	int selApproveCount();
	
	/**
	 * 找用户对应的预约信息
	 * @param uname
	 * @return
	 */
	List<Approve> selAllApproveByUid(String uname,int pageNum,int pageSize);
	
	/**
	 * 修改预约时间
	 * @param id
	 * @param date
	 * @return
	 */
	int updAppTimeById(int id,Date date);
	
	/**
	 * 删除预约信息
	 * @param id
	 * @return
	 */
	int delApproveByid(int id);
	
	/**
	 * 找用户对应的预约信息
	 * @param uname
	 * @return
	 */
	List<Approve> selApproveByUid(String uname,int pageNum,int pageSize);
	
	/**
	 * 找用户名
	 * @param id
	 * @return
	 */
	String selNameUserById(int id);
	
	/**
	 * 插入申请记录
	 * @param app
	 * @return
	 */
	int insApprove(Approve app);
	
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
