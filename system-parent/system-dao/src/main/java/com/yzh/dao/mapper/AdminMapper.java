package com.yzh.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;

public interface AdminMapper {
	/**
	 * 查找管理员
	 * @param admin
	 * @return
	 */
	@Select("select * from admin where adminName=#{adminName} and password=#{password}")
	Admin checkAdmin (Admin admin);
	
	/**
	 * 查找可容纳人数
	 * @return
	 */
	@Select("select * from peonum")
	List<PeoNum> selPeoNum();
	
	/**
	 * 插入会议信息
	 * @param c
	 * @return
	 */
	@Insert("insert into confer_infor values(default,#{conferName},#{size},#{price},#{peoCount},#{address},#{people},#{tel},#{status},#{comm})")
	int insConfer(ConferInfor c);
	
	/**
	 * 插入会议的图片
	 * @param img
	 * @return
	 */
	@Insert("insert into img values(default,#{cid},#{path})")
	int insImg(Img img);
	
	/**
	 * 查找相应名字的会议信息
	 * @param name
	 * @return
	 */
	@Select("select * from confer_infor where conferName = #{0}")
	ConferInfor selConferByName(String name);
	
	/**
	 * 查找所有的会议室信息
	 * @return
	 */
	@Select("select * from confer_infor")
	List<ConferInfor> selAllConferInfor();
	
	/**
	 * 删除会议室信息
	 * @param name
	 * @return
	 */
	@Delete("delete from confer_infor where conferName = #{0}")
	int delConfer(String name);
	
	/**
	 * 删除会议室相关的图片信息
	 * @param name
	 * @return
	 */
	@Delete("delete from img where cid = #{0}")
	int delImgByCid(int cid);
	
	/**
	 * 查找相应cid的图片
	 * @param cid
	 * @return
	 */
	@Select("select * from img where cid = #{0}")
	List<Img> selImgByConferId(int cid);
	
	
	/**
	 * 根据id更新会议室信息
	 * @param c
	 * @return
	 */
	@Update("update confer_infor set conferName=#{conferName} , "
			+" size=#{size} , price=#{price} , peoCount=#{peoCount} , "
			+" address=#{address} , people=#{people} , tel=#{tel} , "
			+" status=#{status} , comm=#{comm} where cid=#{cid}")
	int updConfer(ConferInfor c);
	
	/**
	 * 根据imgid修改图片路径
	 * @param img
	 * @return
	 */
	@Update("update img set path = #{path} where imgid=#{imgid}")
	int updImg(Img img);
	
	/**
	 * 通过名字查找用户
	 * @param name
	 * @return
	 */
	@Select("select * from user where username = #{0}")
	List<User> selUserByName(String name);
	
	/**
	 * 查找所有用户
	 * @return
	 */
	@Select("select * from user")
	List<User> selUser();
	
	/**
	 * 通过uid查找用户
	 * @param id
	 * @return
	 */
	@Select("select * from user where uid = #{0}")
	User selUserById(int id);
	
	/**
	 * 根据uid更新user
	 * @param id
	 * @return
	 */
	@Update("update user set username =#{username} , password = #{password},sex=#{sex},tel=#{tel}"
			+ ",money=#{money},status=#{status},comm=#{comm} where uid = #{uid}")
	int updUserById(User u);
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update user set status = #{1} where uid=#{0}")
	int updUserStatusById(int id,int status);
	
	/**
	 * 通过用户的id查找用户爱好
	 * @param uid
	 * @return
	 */
	@Select("select * from fav where uid = #{uid}")
	List<Fav> selFavByUid(int uid);
	
	/**
	 * 插入用户爱好
	 * @param fav
	 * @return
	 */
	@Insert("insert into fav values (default,#{fav.uid},#{fav.fname})")
	int insFav(Fav fav);
	
	/**
	 * 查找响应密码的管理员是不是存在
	 * @param password
	 * @return
	 */
	@Select("select * from admin where password = #{0}")
	Admin selAdminByPassword(String password);
	
	/**
	 * 修改管理员密码
	 * @param admin
	 * @return
	 */
	@Update("update admin set password = #{password} where aid=#{aid}")
	int updAdminPassword(Admin admin);
	
}
