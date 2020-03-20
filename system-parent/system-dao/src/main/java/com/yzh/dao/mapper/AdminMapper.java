package com.yzh.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Discuss;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;

public interface AdminMapper {
	
	/**
	 * 根据id找订单
	 * @param id
	 * @return
	 */
	@Select("select * from approve where id = #{0}")
	Approve selAppById(int id);
	
	/**
	 * 通过电话查找订单信息
	 * @param pageNum
	 * @param pageSize
	 * @param tel
	 * @return
	 */
	@Select("select * from approve where status = '待使用' and tel = #{0} ")
	List<Approve> selUseByTel(String tel);
	
	/**
	 * 查找待使用的订单数量
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select count(*) from approve where status = '待使用' ")
	int selUseCount();
	
	/**
	 * 查找待使用的订单信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from approve where status = '待使用' limit #{0},#{1}")
	List<Approve> selUse(int pageNum, int pageSize);
	
	/**
	 * 回复留言
	 * @param id
	 * @param aname
	 * @param asay
	 * @return
	 */
	@Update("update discuss set aname = #{1} ,asay = #{2} where id = #{0}")
	int updDiscuss(int id, String aname, String asay);
	
	/**
	 * 指定名字的 信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	@Select("select * from discuss where uname = #{2} and asay is null limit #{0}, #{1}")
	List<Discuss> selMsgByName(int pageNum, int pageSize, String name);
	
	/**
	 * 指定名字信息的数量
	 * @param name
	 * @return
	 */
	@Select("select count(*) from discuss where uname = #{0} and asay is null ")
	int selMsgCountByName(String name);
	
	/**
	 * 全部信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from discuss where asay is null limit #{0}, #{1}")
	List<Discuss> selMsg(int pageNum, int pageSize);
	
	/**
	 * 信息的总数
	 * @return
	 */
	@Select("select count(*) from discuss where asay is null")
	int selMsgCount();

	/**
	 * 查找指定名字已完成的数量
	 * 
	 * @param name
	 * @return
	 */
	@Select("select count(*) from approve where cname like CONCAT('%',#{0},'%') and (status='已完成' or status='已拒绝')")
	int selALLApproveByNameCount(String name);

	/**
	 * 查找指定名字的已完成订单
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from approve where cname like CONCAT('%',#{0},'%') and (status='已完成' or status='已拒绝') limit #{1}, #{2}")
	List<Approve> selALLApproveByName(String name, int pageNum, int pageSize);

	/**
	 * 查找指定名字的未完成订单数量
	 * 
	 * @param name
	 * @return
	 */
	@Select("select count(*) from approve where cname like CONCAT('%',#{0},'%') and (status!='已完成' and status!='已拒绝') ")
	int selApproveByNameCount(String name);

	/**
	 * 查找指定名字的未完成订单
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from approve where cname like CONCAT('%',#{0},'%') and (status!='已完成' and status!='已拒绝') limit #{1}, #{2}")
	List<Approve> selApproveByName(String name, int pageNum, int pageSize);

	/**
	 * 修改备注
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@Update("update approve set comm = #{0} where id = #{1}")
	int updApproveCommById(String comm, int id);

	/**
	 * 修改状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@Update("update approve set status = #{0},aname=#{2} where id = #{1}")
	int updApproveStatusById(String status, int id, String name);

	/**
	 * 查找所有已完成的申请信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from approve where (status='已完成' or status='已拒绝') limit #{0},#{1}")
	List<Approve> selAllApprove(int pageNum, int pageSize);

	/**
	 * 查找所有已完成的申请信息的数量
	 * 
	 * @return
	 */
	@Select("select count(*) from approve where (status='已完成' or status='已拒绝')")
	int selAllApproveCount();

	/**
	 * 查找所有未完成申请信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from approve where (status!='已完成' and status!='已拒绝') limit #{0},#{1}")
	List<Approve> selApprove(int pageNum, int pageSize);

	/**
	 * 查找所有未完成申请信息 的数量
	 * 
	 * @return
	 */
	@Select("select count(*) from approve where (status!='已完成' and status!='已拒绝')")
	int selApproveCount();

	/**
	 * 查找相应cid的会议信息
	 * 
	 * @param cid
	 * @return
	 */
	@Select("select * from confer_infor where cid = #{0}")
	ConferInfor selConferByCid(int cid);

	/**
	 * 查找管理员
	 * 
	 * @param admin
	 * @return
	 */
	@Select("select * from admin where adminName=#{adminName}")
	Admin checkAdmin(Admin admin);

	/**
	 * 查找可容纳人数
	 * 
	 * @return
	 */
	@Select("select * from peonum")
	List<PeoNum> selPeoNum();

	/**
	 * 插入会议信息
	 * 
	 * @param c
	 * @return
	 */
	@Insert("insert into confer_infor values(default,#{conferName},#{size},#{price},#{peoCount},#{address},#{people},#{tel},#{comm})")
	int insConfer(ConferInfor c);

	/**
	 * 插入会议的图片
	 * 
	 * @param img
	 * @return
	 */
	@Insert("insert into img values(default,#{cid},#{path})")
	int insImg(Img img);

	/**
	 * 查找相应名字的会议信息(一个)
	 * 
	 * @param name
	 * @return
	 */
	@Select("select * from confer_infor where conferName = #{0}")
	ConferInfor selOneConferByName(String name);
	
	/**
	 * 查找相应名字的会议信息
	 * 
	 * @param name
	 * @return
	 */
	@Select("select * from confer_infor where conferName like CONCAT('%',#{0},'%') limit #{1},#{2}")
	List<ConferInfor> selConferByName(String name, int first, int last);

	/**
	 * 查找类似会议室名的数量
	 * @param name
	 * @return
	 */
	@Select("select count(*) from confer_infor where conferName like CONCAT('%',#{0},'%');")
	int selConferInfoCountByName(String name);
	
	/**
	 * 查找所有的会议室信息
	 * 
	 * @return
	 */
	@Select("select * from confer_infor limit #{0},#{1}")
	List<ConferInfor> selAllConferInfor(int first, int last);

	/**
	 * 查找会议室信息总数
	 * 
	 * @return
	 */
	@Select("select count(*) from confer_infor;")
	int selConferInfoCount();

	/**
	 * 删除会议室信息
	 * 
	 * @param name
	 * @return
	 */
	@Delete("delete from confer_infor where cid = #{0}")
	int delConfer(int cid);

	/**
	 * 删除会议室相关的图片信息
	 * 
	 * @param name
	 * @return
	 */
	@Delete("delete from img where cid = #{0}")
	int delImgByCid(int cid);

	/**
	 * 查找相应cid的图片
	 * 
	 * @param cid
	 * @return
	 */
	@Select("select * from img where cid = #{0}")
	List<Img> selImgByConferId(int cid);

	/**
	 * 根据id更新会议室信息
	 * 
	 * @param c
	 * @return
	 */
	@Update("update confer_infor set conferName=#{conferName} , "
			+ " size=#{size} , price=#{price} , peoCount=#{peoCount} , "
			+ " address=#{address} , people=#{people} , tel=#{tel} , "
			+ " comm=#{comm} where cid=#{cid}")
	int updConfer(ConferInfor c);

	/**
	 * 根据imgid修改图片路径
	 * 
	 * @param img
	 * @return
	 */
	@Update("update img set path = #{path} where imgid=#{imgid}")
	int updImg(Img img);

	/**
	 * 通过名字查找用户数量
	 * 
	 * @param name
	 * @return
	 */
	@Select("select count(*) from user where username like CONCAT('%',#{0},'%') ")
	int selUserCountByName(String name);
	
	/**
	 * 通过名字查找用户
	 * 
	 * @param name
	 * @return
	 */
	@Select("select * from user where username like CONCAT('%',#{0},'%') limit #{1}, #{2}")
	List<User> selUserByName(String name, int pageNum, int pageSize);

	/**
	 * 查找所有用户
	 * 
	 * @return
	 */
	@Select("select * from user limit #{0}, #{1}")
	List<User> selUser(int pageNum, int pageSize);

	/**
	 * 查找所有用户的数量
	 * 
	 * @return
	 */
	@Select("select count(*) from user")
	int selUserCount();
	
	/**
	 * 通过uid查找用户
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from user where uid = #{0}")
	User selUserById(int id);

	/**
	 * 根据uid更新user
	 * 
	 * @param id
	 * @return
	 */
	@Update("update user set username =#{username} , password = #{password},sex=#{sex},tel=#{tel}"
			+ ",money=#{money},comm=#{comm} where uid = #{uid}")
	int updUserById(User u);

	/**
	 * 根据id修改状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update user set status = #{1} where uid=#{0}")
	int updUserStatusById(int id, int status);

	/**
	 * 通过用户的id查找用户爱好
	 * 
	 * @param uid
	 * @return
	 */
	@Select("select * from fav where uid = #{uid}")
	List<Fav> selFavByUid(int uid);

	/**
	 * 插入用户爱好
	 * 
	 * @param fav
	 * @return
	 */
	@Insert("insert into fav values (default,#{fav.uid},#{fav.fname})")
	int insFav(Fav fav);

	/**
	 * 查找响应密码的管理员是不是存在
	 * 
	 * @param password
	 * @return
	 */
	@Select("select * from admin where password = #{0}")
	Admin selAdminByPassword(String password);

	/**
	 * 修改管理员密码
	 * 
	 * @param admin
	 * @return
	 */
	@Update("update admin set password = #{password} where aid=#{aid}")
	int updAdminPassword(Admin admin);

}
