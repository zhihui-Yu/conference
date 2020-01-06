package com.yzh.dao.pojo;

public class ConferInfor {
	private int cid;
	private String conferName;
	private int size;
	private double price;
	private String peoCount;
	private String address;
	private String people;
	private String tel;
	private String status;
	private String comm;
	
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	@Override
	public String toString() {
		return "ConferInfor [cid=" + cid + ", conferName=" + conferName + ", size=" + size + ", price=" + price
				+ ", peoCount=" + peoCount + ", address=" + address + ", people=" + people + ", tel=" + tel
				+ ", status=" + status + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getConferName() {
		return conferName;
	}
	public void setConferName(String conferName) {
		this.conferName = conferName;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPeoCount() {
		return peoCount;
	}
	public void setPeoCount(String peoCount) {
		this.peoCount = peoCount;
	}
	
	
	
}
