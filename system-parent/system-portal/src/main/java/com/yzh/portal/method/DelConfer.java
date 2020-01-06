package com.yzh.portal.method;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yzh.dao.pojo.Img;
import com.yzh.service.AdminService;

public class DelConfer {
	public static String delConfer(HttpServletRequest req, AdminService adminServiceImpl, String name) {
		// 先删除本地图片
		String url = req.getSession().getServletContext().getRealPath("/upload") + '/';
		List<Img> selImgByConferId = adminServiceImpl.selImgByConferId(adminServiceImpl.selConferByName(name).getCid());
		for (Img img : selImgByConferId) {
			File file = new File(url + img.getPath());
			System.out.println(url + img.getPath());
			file.delete();
		}
		// 删除会议室图片 必须要先删除图片 不然没有cid
		int delImgByCid = adminServiceImpl.delImgByCid(adminServiceImpl.selConferByName(name).getCid());
		// 删除会议室信息
		int delConfer = adminServiceImpl.delConfer(name);
		String msg = "";
		if (delConfer > 0 && delImgByCid > 0) {
			msg = "删除成功";
		} else {
			msg = "删除失败";
		}
		return msg;
	}
}
