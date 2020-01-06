package com.yzh.dao.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Deal {
	private int id;
	private int uid;
	private int cid;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	private double money;
	private String comm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	
}
