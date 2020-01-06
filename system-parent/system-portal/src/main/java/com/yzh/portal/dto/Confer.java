package com.yzh.portal.dto;

import java.util.List;

import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Img;

public class Confer {
	private List<Img> img;
	private ConferInfor ci;
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Img> getImg() {
		return img;
	}
	public void setImg(List<Img> imgs) {
		this.img = imgs;
	}
	public ConferInfor getCi() {
		return ci;
	}
	public void setCi(ConferInfor ci) {
		this.ci = ci;
	}
}	
