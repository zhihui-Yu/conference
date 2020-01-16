package com.yzh.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.User;

public interface UserMapper {

	/**
	 * 找用户对应的预约信息
	 * @param uname
	 * @return
	 */
	@Select("select * from approve where uname = #{0} limit #{1},#{2}")
	List<Approve> selApproveByUid(String uname,int pageNum,int pageSize);
	
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
	@Insert("insert into approve values (default,#{uname},#{cname},#{aname},#{time},#{money}"
			+ ",#{status},#{comm})")
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
	@Insert("insert into discuss(id,uid,usay) values(default,#{0},#{1})")
	int insUserMsg(int uid,String msg);
	
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
	@Select("select * from user where username=#{username} and password=#{password}")
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
	@Update("update user set money = #{1} where uid = #{0}")
	int updUserMoney(int uid,double moeny);
}
