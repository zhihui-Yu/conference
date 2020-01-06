package com.yzh.portal.method;

import java.util.List;

import com.yzh.dao.pojo.ConferInfor;
import com.yzh.dao.pojo.Img;
import com.yzh.portal.dto.Confer;
import com.yzh.service.AdminService;

public class SelConfer {
	/**
	 * 根据名字查找详细的会议室信息
	 * @param conferName
	 * @param adminServiceImpl
	 * @param confer
	 * @return
	 */
	
	public static Confer selConfer(String conferName, AdminService adminServiceImpl, Confer confer) {
		if (conferName != "") {
			ConferInfor conferInfor = adminServiceImpl.selConferByName(conferName);
			// 找到存在会议室信息
			if (conferInfor != null) {
				// 查找相应会议的图片
				List<Img> imgs = adminServiceImpl.selImgByConferId(conferInfor.getCid());
				confer.setCi(conferInfor);
				confer.setImg(imgs);
				confer.setMsg("查找成功");
			} else {
				// 没找到会议室信息
				confer = null;
			}
		}
		return confer;
	}
}