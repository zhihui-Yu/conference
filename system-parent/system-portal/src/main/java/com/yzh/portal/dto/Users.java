package com.yzh.portal.dto;

import java.util.List;

import com.yzh.dao.pojo.Fav;
import com.yzh.dao.pojo.User;

public class Users {
	private User user;
	private List<Fav> fav;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Fav> getFav() {
		return fav;
	}
	public void setFav(List<Fav> fav) {
		this.fav = fav;
	}
	
}
