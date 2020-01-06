package com.yzh.portal.method;

import java.util.List;

import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Img;
import com.yzh.portal.dto.Confer;
import com.yzh.service.AdminService;

public class SelAllConfer {
	public static List<Confer> selAllConfer(AdminService adminServiceImpl, List<Confer> confer){
		String msg = null;
		//查找会议室
		List<ConferInfor> conferInfors = adminServiceImpl.selAllConferInfor();
		if(conferInfors != null){
			for (int i = 0; i < conferInfors.size(); i++) {
				msg = "查找成功";
				Confer c = new Confer();
				//查找会议室图片
				List<Img> imgs = adminServiceImpl.selImgByConferId(conferInfors.get(i).getCid());
				c.setCi(conferInfors.get(i));
				c.setImg(imgs);
				c.setMsg(msg);
				//放入集合
				confer.add(c);
			}
		}else{
			msg = "无会议室信息";
			Confer c = new Confer();
			c.setMsg(msg);
			confer.add(c);
		}
		return confer;
	}
}
