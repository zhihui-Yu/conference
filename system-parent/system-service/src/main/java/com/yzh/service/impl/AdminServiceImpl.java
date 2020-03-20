package com.yzh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzh.dao.mapper.AdminMapper;
import com.yzh.dao.pojo.Admin;
import com.yzh.dao.pojo.Approve;
import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Discuss;
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
	public List<ConferInfor> selAllConferInfor(int first,int last) {
		return adminMapper.selAllConferInfor(first, last);
	}

	@Override
	public int delConfer(int cid) {
		return adminMapper.delConfer(cid);
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

	@Override
	public int selConferInfoCount() {
		return adminMapper.selConferInfoCount();
	}

	@Override
	public ConferInfor selConferByCid(int cid) {
		return adminMapper.selConferByCid(cid);
	}

	@Override
	public List<Approve> selAllApprove(int pageNum, int pageSize) {
		return adminMapper.selAllApprove(pageNum, pageSize);
	}

	@Override
	public List<Approve> selApprove(int pageNum, int pageSize) {
		return adminMapper.selApprove(pageNum, pageSize);
	}

	@Override
	public int updApproveStatusById(String status, int id, String name) {
		return adminMapper.updApproveStatusById(status, id, name);
	}

	@Override
	public int updApproveCommById(String comm, int id) {
		return adminMapper.updApproveCommById(comm, id);
	}

	@Override
	public int selAllApproveCount() {
		return adminMapper.selAllApproveCount();
	}

	@Override
	public int selApproveCount() {
		return adminMapper.selApproveCount();
	}

	@Override
	public int selALLApproveByNameCount(String name) {
		return adminMapper.selALLApproveByNameCount(name);
	}

	@Override
	public List<Approve> selALLApproveByName(String name, int pageNum, int pageSize) {
		return adminMapper.selALLApproveByName(name, pageNum, pageSize);
	}

	@Override
	public int selApproveByNameCount(String name) {
		return adminMapper.selApproveByNameCount(name);
	}

	@Override
	public List<Approve> selApproveByName(String name, int pageNum, int pageSize) {
		return adminMapper.selApproveByName(name, pageNum, pageSize);
	}

	@Override
	public List<Discuss> selMsgByName(int pageNum, int pageSize, String name) {
		return adminMapper.selMsgByName(pageNum, pageSize, name);
	}

	@Override
	public int selMsgCountByName(String name) {
		return adminMapper.selMsgCountByName(name);
	}

	@Override
	public List<Discuss> selMsg(int pageNum, int pageSize) {
		return adminMapper.selMsg(pageNum, pageSize);
	}

	@Override
	public int selMsgCount() {
		return adminMapper.selMsgCount();
	}

	@Override
	public int updDiscuss(int id, String aname, String asay) {
		return adminMapper.updDiscuss(id, aname, asay);
	}

	@Override
	public int selUserCountByName(String name) {
		return adminMapper.selUserCountByName(name);
	}

	@Override
	public List<User> selUserByName(String name, int pageNum, int pageSize) {
		return adminMapper.selUserByName(name, pageNum, pageSize);
	}

	@Override
	public List<User> selUser(int pageNum, int pageSize) {
		return adminMapper.selUser(pageNum, pageSize);
	}

	@Override
	public int selUserCount() {
		return adminMapper.selUserCount();
	}

	@Override
	public int selConferInfoCountByName(String name) {
		return adminMapper.selConferInfoCountByName(name);
	}

	@Override
	public List<ConferInfor> selConferByName(String name, int first, int last) {
		return adminMapper.selConferByName(name, first, last);
	}

	@Override
	public ConferInfor selOneConferByName(String name) {
		return adminMapper.selOneConferByName(name);
	}

	@Override
	public List<Approve> selUseByTel(String tel) {
		return adminMapper.selUseByTel(tel);
	}

	@Override
	public int selUseCount() {
		return adminMapper.selUseCount();
	}

	@Override
	public List<Approve> selUse(int pageNum, int pageSize) {
		return adminMapper.selUse(pageNum, pageSize);
	}

	@Override
	public Approve selAppById(int id) {
		return adminMapper.selAppById(id);
	}
	
}
