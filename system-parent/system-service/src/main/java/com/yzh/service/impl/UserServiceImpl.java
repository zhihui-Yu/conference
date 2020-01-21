package com.yzh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzh.dao.mapper.UserMapper;
import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;
import com.yzh.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public User selUser (User u) {
		return userMapper.selUser(u);
	}

	@Override
	public int addUser(User u) {
		return userMapper.addUser(u);
	}
	
	@Override
	public int updUserById(User u) {
		return userMapper.updUserById(u);
	}

	@Override
	public int updUserPassword(User u) {
		return userMapper.updUserPassword(u);
	}

	@Override
	public int insFavByUid(Fav fav) {
		return userMapper.insFavByUid(fav);
	}

	@Override
	public List<Favorite> selAllFavorite() {
		return userMapper.selAllFavorite();
	}

	@Override
	public List<Fav> selFavByUid(int uid) {
		return userMapper.selFavByUid(uid);
	}

	@Override
	public int insUserMsg(int uid, String msg) {
		return userMapper.insUserMsg(uid, msg);
	}

	@Override
	public int delUserFav(int uid) {
		return userMapper.delUserFav(uid);
	}

	@Override
	public int insApprove(Approve app) {
		return userMapper.insApprove(app);
	}

	@Override
	public String selNameUserById(int id) {
		return userMapper.selNameUserById(id);
	}

	@Override
	public List<Approve> selApproveByUname(String uname,int pageNum,int pageSize) {
		return userMapper.selApproveByUname(uname, pageNum, pageSize);
	}

	@Override
	public int delApproveByid(int id) {
		return userMapper.delApproveByid(id);
	}

	@Override
	public int updAppTimeById(int id, Date date) {
		return userMapper.updAppTimeById(id, date);
	}

	@Override
	public List<Approve> selAllApproveByUname(String uname, int pageNum, int pageSize) {
		return userMapper.selAllApproveByUname(uname, pageNum, pageSize);
	}

	@Override
	public int selAllApproveCount(String uname) {
		return userMapper.selAllApproveCount(uname);
	}

	@Override
	public int selApproveCount(String uname) {
		return userMapper.selApproveCount(uname);
	}

	@Override
	public List<Approve> selApproveByName(String name, int pageNum, int pageSize, String uname) {
		return userMapper.selApproveByName(name, pageNum, pageSize,uname);
	}

	@Override
	public List<Approve> selAllApproveByName(String name, int pageNum, int pageSize, String uname) {
		return userMapper.selAllApproveByName(name, pageNum, pageSize,uname);
	}

	@Override
	public int selAllCountByName(String name, String uname) {
		return userMapper.selAllCountByName(name,uname);
	}

	@Override
	public int selCountByName(String name, String uname) {
		return userMapper.selCountByName(name,uname);
	}

	@Override
	public List<PeoNum> selPeoNum() {
		return userMapper.selPeoNum();
	}

	@Override
	public int selConferInfoCount(String address, int size, String peoCount, String time) {
		return userMapper.selConferInfoCount(address, size, peoCount, time);
	}

	@Override
	public List<ConferInfor> selConferInfo(String address, int size, String peoCount, String time, int pageNum, int pageSize) {
		return userMapper.selConferInfo(address, size, peoCount, time, pageNum, pageSize);
	}

	@Override
	public int insUsed(int cid, Date date) {
		return userMapper.insUsed(cid, date);
	}

	@Override
	public int updUserMoneyAdd(int uid, double moeny) {
		return userMapper.updUserMoneyAdd(uid, moeny);
	}

	@Override
	public int updUserMoneyDes(int uid, double moeny) {
		return userMapper.updUserMoneyDes(uid, moeny);
	}

	@Override
	public int updApproveStatus(int id, String status) {
		return userMapper.updApproveStatus(id, status);
	}

}
