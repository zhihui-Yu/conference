package com.yzh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzh.dao.mapper.UserMapper;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Favorite;
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

}
