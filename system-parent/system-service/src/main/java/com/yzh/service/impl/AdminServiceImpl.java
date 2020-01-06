package com.yzh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzh.dao.mapper.AdminMapper;
import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.Img;
import com.yzh.dao.pojo.PeoNum;
import com.yzh.dao.pojo.User;
import com.yzh.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Resource
	private AdminMapper adminMapper;
	
	@Override
	public Admin checkAdmin(Admin admin) {
		return adminMapper.checkAdmin(admin);
	}
	
	@Override
	public int insConfer(ConferInfor c) {
		return adminMapper.insConfer(c);
	}
	
	@Override
	public int insImg(Img img) {
		return adminMapper.insImg(img);
	}

	@Override
	public List<PeoNum> selPeoNum() {
		return adminMapper.selPeoNum();
	}

	@Override
	public ConferInfor selConferByName(String name) {
		return adminMapper.selConferByName(name);
	}

	@Override
	public List<User> selUserByName(String name) {
		return adminMapper.selUserByName(name);
	}

	@Override
	public List<User> selUser() {
		return adminMapper.selUser();
	}

	@Override
	public List<Fav> selFavByUid(int uid) {
		return adminMapper.selFavByUid(uid);
	}

	@Override
	public int insFav(Fav fav) {
		return adminMapper.insFav(fav);
	}

	@Override
	public List<Img> selImgByConferId(int cid) {
		return adminMapper.selImgByConferId(cid);
	}

	@Override
	public List<ConferInfor> selAllConferInfor() {
		return adminMapper.selAllConferInfor();
	}

	@Override
	public int delConfer(String name) {
		return adminMapper.delConfer(name);
	}

	@Override
	public int delImgByCid(int cid) {
		return adminMapper.delImgByCid(cid);
	}

	@Override
	public int updConfer(ConferInfor c) {
		return adminMapper.updConfer(c);
	}

	@Override
	public int updImg(Img img) {
		return adminMapper.updImg(img);
	}

	@Override
	public User selUserById(int id) {
		return adminMapper.selUserById(id);
	}

	@Override
	public int updUserById(User u) {
		return adminMapper.updUserById(u);
	}

	@Override
	public int updUserStatusById(int id, int status) {
		return adminMapper.updUserStatusById(id, status);
	}

	@Override
	public Admin selAdminByPassword(String password) {
		return adminMapper.selAdminByPassword(password);
	}

	@Override
	public int updAdminPassword(Admin admin) {
		return adminMapper.updAdminPassword(admin);
	}

}
