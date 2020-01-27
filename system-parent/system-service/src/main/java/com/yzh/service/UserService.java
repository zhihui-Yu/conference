package com.yzh.service;

import java.util.Date;
import java.util.List;

import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Discuss;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;

public interface UserService {
	
	/**
	 * 查相应用户名的用户是不是存在
	 * @param name
	 * @return
	 */
	User selUserByName(String name);
	
	/**
	 * 查相应用电话号码的用户是不是存在
	 * @param tel
	 * @return
	 */
	User selUserByTel(String tel);
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	int delDiscussById(int id);
	
	/**
	 * 查找回复信息的数量
	 * @param name
	 * @return
	 */
	int selDiscussCount(String name);
	
	/**
	 * 查找回复信息
	 * @param name
	 * @return
	 */
	List<Discuss> selDiscuss(String name, int pageNum, int pageSize);
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updApproveStatus(int id,String status);
	
	/**
	 * 缴费
	 * 修改表中的金额
	 * @param uid
	 * @param moeny
	 * @return
	 */
	int updUserMoneyDes(int uid,double moeny);
	
	/**
	 * 充值 缴费
	 * 修改表中的金额
	 * @param uid
	 * @param moeny
	 * @return
	 */
	int updUserMoneyAdd(int uid,double moeny);
	
	/**
	 * 存入使用时间
	 * @param cid
	 * @param date
	 * @return
	 */
	int insUsed(int cid, Date date);
	
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
	List<Approve> selApproveByName(String name, int pageNum, int pageSize, String uname);
	
	/**
	 * 查询完成的所有订单
	 * @param name
	 * @return
	 */
	List<Approve> selAllApproveByName(String name, int pageNum, int pageSize, String uname);
	
	/**
	 * 找用户对应的全部信息的总数
	 * @param name 会议室名
	 * @return
	 */
	int selAllCountByName(String name, String uname);
	
	/**
	 * 找用户对应的预约信息的总数
	 * @param name 会议室名
	 * @return
	 */
	int selCountByName(String name, String uname);
	
	/**
	 * 找用户对应的已完成预约信息的总数
	 * @param uname
	 * @return
	 */
	int selAllApproveCount(String uname);
	
	/**
	 * 找用户对应的预约信息未完成的总数
	 * @param uname
	 * @return
	 */
	int selApproveCount(String uname);
	
	/**
	 * 找用户对应的预约信息
	 * @param uname
	 * @return
	 */
	List<Approve> selAllApproveByUname(String uname,int pageNum,int pageSize);
	
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
	List<Approve> selApproveByUname(String uname,int pageNum,int pageSize);
	
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
