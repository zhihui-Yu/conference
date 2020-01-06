package com.yzh.portal.method;

import java.io.File;

public class DelImgFile {
	/**
	 * 传入图像名数组，文件url
	 * @param fileName
	 * @param url
	 */
	public static void delFile(String[] fileName,String url){
		for (String name : fileName) {
			File file = new File(url + "/" + name);
			file.delete();
		}
	}
}
