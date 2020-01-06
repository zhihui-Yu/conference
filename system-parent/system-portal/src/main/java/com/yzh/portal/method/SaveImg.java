package com.yzh.portal.method;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class SaveImg {
	/**
	 * 
	 * 保存图像到本地 并返回图像名
	 * 
	 * @param file
	 * @param url 保存图像的文件目录地址
	 */
	public static String[] saveFile(MultipartFile[] file,String url){
		String path = "";
		String[] paths = new String[file.length];
		int k = 0;
		try {
			//循环遍历获得图片地址
			for (MultipartFile f : file) {
				if (!f.isEmpty()) {
					//生成随机的图片名
					String name = UUID.randomUUID().toString().replace("-", "");
					//获取后缀
					String ext = FilenameUtils.getExtension(f.getOriginalFilename());
					//将图片放入地址
					f.transferTo(new File(url + "/" + name + "." + ext));
					//获取随机生成的图像名
					path = name + "." + ext;
					if (k < file.length) {
						paths[k] = path;
						k++;
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paths;
	}
}
