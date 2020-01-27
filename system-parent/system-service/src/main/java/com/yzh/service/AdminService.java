package com.yzh.service;

import java.util.List;

import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Discuss;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;

public interface AdminService {
	
	/**
	 * 通过电话查找订单信息
	 * @param pageNum
	 * @param pageSize
	 * @param tel
	 * @return
	 */
	List<Approve> selUseByTel(String tel);
	
	/**
	 * 查找待使用的订单数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	int selUseCount();
	
	/**
	 * 查找待使用的订单信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Approve> selUse(int pageNum, int pageSize);
	
	/**
	 * 回复留言
	 * @param id
	 * @param aname
	 * @param asay
	 * @return
	 */
	int updDiscuss(int id, String aname, String asay);
	
	/**
	 * 指定名字的 信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	List<Discuss> selMsgByName(int pageNum, int pageSize, String name);
	
	/**
	 * 指定名字信息的数量
	 * @param name
	 * @return
	 */
	int selMsgCountByName(String name);
	
	/**
	 * 全部信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Discuss> selMsg(int pageNum, int pageSize);
	
	/**
	 * 信息的总数
	 * @return
	 */
	int selMsgCount();

	/**
	 * 查找指定名字已完成的数量
	 * @param name
	 * @return
	 */
	int selALLApproveByNameCount(String name);
	
	/**
	 * 查找指定名字的已完成订单
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Approve> selALLApproveByName(String name, int pageNum, int pageSize);
	
	/**
	 * 查找指定名字的未完成订单数量
	 * @param name
	 * @return
	 */
	int selApproveByNameCount(String name);
	
	/**
	 * 查找指定名字的未完成订单
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Approve> selApproveByName(String name, int pageNum, int pageSize);
	
	/**
	 * 修改备注
	 * @param status
	 * @param id
	 * @return
	 */
	int updApproveCommById(String comm, int id);
	
	/**
	 * 修改状态
	 * @param status
	 * @param id
	 * @return
	 */
	int updApproveStatusById(String status, int id, String name);
	
	/**
	 * 查找所有已完成的申请信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Approve> selAllApprove(int pageNum, int pageSize);
	
	/**
	 * 查找所有已完成的申请信息的数量
	 * @return
	 */
	int selAllApproveCount();
	
	/**
	 * 查找所有未完成申请信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Approve> selApprove(int pageNum, int pageSize);
	
	/**
	 * 查找所有未完成申请信息 的数量
	 * @return
	 */
	int selApproveCount();
	
	/**
	 * 查找相应cid的会议信息
	 * @param cid
	 * @return
	 */
	ConferInfor selConferByCid(int cid);
	
	/**
	 * 查找会议室信息总数
	 * @return
	 */
	int selConferInfoCount();
	
	/**
	 * 查找管理员
	 * 
	 * @param admin
	 * @return
	 */
	Admin checkAdmin(Admin admin);

	/**
	 * 查找可容纳人数
	 * 
	 * @return
	 */
	List<PeoNum> selPeoNum();

	/**
	 * 插入会议信息
	 * 
	 * @param c
	 * @return
	 */
	int insConfer(ConferInfor c);

	/**
	 * 插入会议的图片
	 * 
	 * @param img
	 * @return
	 */
	int insImg(Img img);

	/**
	 * 查找相应名字的会议信息(一个)
	 * 
	 * @param name
	 * @return
	 */
	ConferInfor selOneConferByName(String name);
	
	/**
	 * 查找相应名字的会议信息
	 * 
	 * @param name
	 * @return
	 */
	List<ConferInfor> selConferByName(String name, int first, int last);

	/**
	 * 查找类似会议室名的数量
	 * @param name
	 * @return
	 */
	int selConferInfoCountByName(String name);
	
	/**
	 * 查找所有的会议室信息
	 * 
	 * @return
	 */
	List<ConferInfor> selAllConferInfor(int first, int last);

	/**
	 * 查找相应cid的图片
	 * 
	 * @param cid
	 * @return
	 */
	List<Img> selImgByConferId(int cid);

	/**
	 * 删除会议室信息
	 * 
	 * @param name
	 * @return
	 */
	int delConfer(int cid);

	/**
	 * 删除会议室相关的图片信息
	 * 
	 * @param name
	 * @return
	 */
	int delImgByCid(int cid);

	/**
	 * 根据id更新会议室信息
	 * 
	 * @param c
	 * @return
	 */
	int updConfer(ConferInfor c);

	/**
	 * 根据imgid修改图片路径
	 * 
	 * @param img
	 * @return
	 */
	int updImg(Img img);

	/**
	 * 通过名字查找用户数量
	 * 
	 * @param name
	 * @return
	 */
	int selUserCountByName(String name);
	
	/**
	 * 通过名字查找用户
	 * 
	 * @param name
	 * @return
	 */
	List<User> selUserByName(String name, int pageNum, int pageSize);

	/**
	 * 查找所有用户
	 * 
	 * @return
	 */
	List<User> selUser(int pageNum, int pageSize);

	/**
	 * 查找所有用户的数量
	 * 
	 * @return
	 */
	int selUserCount();

	/**
	 * 通过用户的id查找用户爱好
	 * 
	 * @param uid
	 * @return
	 */
	List<Fav> selFavByUid(int uid);

	/**
	 * 插入用户爱好
	 * 
	 * @param fav
	 * @return
	 */
	int insFav(Fav fav);

	/**
	 * 通过uid查找用户
	 * 
	 * @param id
	 * @return
	 */
	User selUserById(int id);

	/**
	 * 根据uid更新user
	 * 
	 * @param id
	 * @return
	 */
	int updUserById(User u);

	/**
	 * 根据id修改状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	int updUserStatusById(int id, int status);

	/**
	 * 查找响应密码的管理员是不是存在
	 * 
	 * @param password
	 * @return
	 */
	Admin selAdminByPassword(String password);

	/**
	 * 修改管理员密码
	 * 
	 * @param admin
	 * @return
	 */
	int updAdminPassword(Admin admin);
}
