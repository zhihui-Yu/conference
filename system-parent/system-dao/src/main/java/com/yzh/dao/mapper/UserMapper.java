package com.yzh.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Discuss;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.Used;
import com.yzh.dao.pojo.User;

public interface UserMapper {
	
	/**
	 * 查詢所有的使用情況
	 * @return
	 */
	@Select("select * from used")
	List<Used> selUsed();
	
	/**
	 * 更新使用时间
	 * @param time
	 * @param id
	 * @return
	 */
	@Update("update used set time = #{0} where id = #{1}")
	int updUsed(Date time,int id);
	
	/**
	 * 删除取消预约的时间
	 * @param id
	 * @return
	 */
	@Delete("delete from used where id = #{0}")
	int delUsed(int id);
	
	/**
	 * 查相应用户名的用户是不是存在
	 * @param name
	 * @return
	 */
	@Select("select * from user where username = #{0} ")
	User selUserByName(String name);
	
	/**
	 * 查相应用电话号码的用户是不是存在
	 * @param tel
	 * @return
	 */
	@Select("select * from user where tel = #{0}")
	User selUserByTel(String tel);
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@Delete("delete from discuss where id = #{0}")
	int delDiscussById(int id);
	
	/**
	 * 查找回复信息的数量
	 * @param name
	 * @return
	 */
	@Select("select count(*) from discuss where uname = #{0}  and aname is not null")
	int selDiscussCount(String name);
	
	/**
	 * 查找回复信息
	 * @param name
	 * @return
	 */
	@Select("select * from discuss where uname = #{0} and aname is not null limit #{1},#{2}")
	List<Discuss> selDiscuss(String name, int pageNum, int pageSize);
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update approve set status = #{1} where id = #{0}")
	int updApproveStatus(int id,String status);
	
	/**
	 * 存入使用时间
	 * @param cid
	 * @param date
	 * @return
	 */
	@Insert("insert into used values(default,#{cid},#{time})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	int insUsed(Used used);
	
	/**
	 * 计算相应条件的confer 的长度
	 * 
	 * @param address   地址
	 * @param size		大小
	 * @param peoCount	可容纳人数
	 * @param time		时间
	 * @return
	 */
	@Select("select count(*) from confer_infor where address like CONCAT('%',#{0},'%') and (size > #{1} or size = #{1})"
			+ " and peoCount like CONCAT('%',#{2},'%') and cid NOT IN (select cid from used where time like CONCAT('%',#{3},'%'))")
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
	@Select("select * from confer_infor where address like CONCAT('%',#{0},'%') and (size > #{1} or size = #{1})"
			+ " and peoCount like CONCAT('%',#{2},'%') and cid NOT IN (select cid from used where time like CONCAT('%',#{3},'%')) limit #{4},#{5}")
	List<ConferInfor> selConferInfo(String address, int size, String peoCount, String time,int pageNum, int pageSize);
	
	/**
	 * 查找可容纳人数
	 * @return
	 */
	@Select("select * from peonum")
	List<PeoNum> selPeoNum();
	
	/**
	 * 查找对应信息的未完成订单
	 * @param name
	 * @return
	 */
	@Select("select * from approve where uname = #{3} and status != '已完成' and status != '待使用' and status != '已拒绝' and cname like CONCAT('%',#{0},'%')  limit #{1},#{2}")
	List<Approve> selApproveByName(String name, int pageNum, int pageSize, String uname);
	
	/**
	 * 查询对应信息的所有订单
	 * @param name
	 * @return
	 */
	@Select("select * from approve where uname = #{3} and cname like CONCAT('%',#{0},'%') and (status = '已完成' or status = '待使用' or status = '已拒绝') limit #{1},#{2}")
	List<Approve> selAllApproveByName(String name, int pageNum, int pageSize, String uname);
	
	/**
	 * 找用户对应的已完成的订单的总数
	 * @param name 会议室名
	 * @return
	 */
	@Select("select count(*) from approve where uname = #{1} and cname like CONCAT('%',#{0},'%') and  (status = '已完成' or status = '待使用' or status = '已拒绝') ")
	int selAllCountByName(String name, String uname);
	
	/**
	 * 找用户未完成的预约信息的总数
	 * @param name 会议室名
	 * @return
	 */
	@Select("select count(*) from approve where uname = #{1} and status != '已完成' and status != '待使用' and status != '已拒绝' and cname like CONCAT('%',#{0},'%') ")
	int selCountByName(String name, String uname);
	
	/**
	 * 找用户对应的已完成的预约信息的总数
	 * @return
	 */
	@Select("select count(*) from approve where uname = #{0} and (status = '已完成' or status = '待使用' or status = '已拒绝')")
	int selAllApproveCount(String uname);
	
	/**
	 * 找用户对应的预约信息为完成的总数
	 * @return
	 */
	@Select("select count(*) from approve where uname = #{0} and status != '已完成' and status != '待使用' and status != '已拒绝'")
	int selApproveCount(String uname);
	
	
	/**
	 * 找用户对应的已完成预约信息
	 * @param uname
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from approve where uname = #{0} and (status = '已完成' or status = '待使用' or status = '已拒绝') limit #{1},#{2}")
	List<Approve> selAllApproveByUname(String uname,int pageNum,int pageSize);
	
	/**
	 * 找用户对应的预约信息
	 * @param uname
	 * @return
	 */
	@Select("select * from approve where uname = #{0} and status != '已完成' and status != '待使用' and status != '已拒绝' limit #{1},#{2}")
	List<Approve> selApproveByUname(String uname,int pageNum,int pageSize);
	
	/**
	 * 修改预约时间
	 * @param id
	 * @param date
	 * @return
	 */
	@Update("update approve set time = #{1} where id = #{0}")
	int updAppTimeById(int id,Date date);
	
	/**
	 * 删除预约信息
	 * @param id
	 * @return
	 */
	@Delete("delete from approve where id = #{0}")
	int delApproveByid(int id);

	/**
	 * 找用户名
	 * @param id
	 * @return
	 */
	@Select("select username from user where uid = #{0}")
	String selNameUserById(int id);
	
	/**
	 * 插入申请记录
	 * @param app
	 * @return
	 */
	@Insert("insert into approve values (default,#{uname},#{tel},#{cname},#{aname},#{time},#{money}"
			+ ",#{status},#{dealtime},#{comm},#{usedid})")
	int insApprove(Approve app);
		
	/**
	 * 删除指定用户的爱好
	 * @param uid 用户id
	 * @return
	 */
	@Delete("delete from fav where uid = #{0}")
	int delUserFav(int uid);
	
	/**
	 * 保存用户的反馈信息
	 * 
	 * @param uid 用户id
	 * @param msg 提交的信息
	 * @return
	 */
	@Insert("insert into discuss (id,uname,usay) values(default,#{0},#{1})")
	int insUserMsg(String uname,String msg);
	
	/**
	 * 通过用户的id查找用户爱好
	 * @param uid
	 * @return
	 */
	@Select("select * from fav where uid = #{uid}")
	List<Fav> selFavByUid(int uid);
	
	/**
	 * 保存uid对应的爱好
	 * @param fav
	 * @return
	 */
	@Insert("insert into fav values(default,#{uid},#{fname})")
	int insFavByUid(Fav fav);
	
	/**
	 * 查找所有的爱好
	 * @return
	 */
	@Select("select * from favorite")
	List<Favorite> selAllFavorite();
	
	/**
	 * 登入控制器
	 * 查找相应的账号密码是不是存在
	 * @param u
	 * @return
	 */
	@Select("select * from user where username=#{username}")
	User selUser(User u);
	
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
	@Update("update user set username =#{username},sex=#{sex},tel=#{tel}"
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
	@Update("update user set money = money+ #{1} where uid = #{0}")
	int updUserMoneyAdd(int uid,double moeny);
	
	/**
	 * 缴费
	 * 修改表中的金额
	 * @param uid
	 * @param moeny
	 * @return
	 */
	@Update("update user set money = money - #{1} where uid = #{0}")
	int updUserMoneyDes(int uid,double moeny);
}
