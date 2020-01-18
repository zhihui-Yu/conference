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
	public List<Approve> selApproveByUid(String uname,int pageNum,int pageSize) {
		return userMapper.selApproveByUid(uname, pageNum, pageSize);
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
	public List<Approve> selAllApproveByUid(String uname, int pageNum, int pageSize) {
		return userMapper.selAllApproveByUid(uname, pageNum, pageSize);
	}

	@Override
	public int selAllApproveCount() {
		return userMapper.selAllApproveCount();
	}

	@Override
	public int selApproveCount() {
		return userMapper.selApproveCount();
	}

	@Override
	public List<Approve> selApproveByName(String name, int pageNum, int pageSize) {
		return userMapper.selApproveByName(name, pageNum, pageSize);
	}

	@Override
	public List<Approve> selAllApproveByName(String name, int pageNum, int pageSize) {
		return userMapper.selAllApproveByName(name, pageNum, pageSize);
	}

	@Override
	public int selAllCountByName(String name) {
		return userMapper.selAllCountByName(name);
	}

	@Override
	public int selCountByName(String name) {
		return userMapper.selCountByName(name);
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

}
