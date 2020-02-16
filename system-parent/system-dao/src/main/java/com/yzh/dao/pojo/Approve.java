package com.yzh.dao.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Approve {
	
	private int id;
	private String uname;
	private String cname;
	private String aname;
	//接收参数的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//输出参数的格式
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date time;
	//接收参数的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//输出参数的格式
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dealtime;
	private double money;
	private String status;
	private String tel;
	private String comm;
	private int usedid;
	
	
	public int getUsedid() {
		return usedid;
	}
	public void setUsedid(int usedid) {
		this.usedid = usedid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getDealtime() {
		return dealtime;
	}
	public void setDealtime(Date dealtime) {
		this.dealtime = dealtime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	
	
}
