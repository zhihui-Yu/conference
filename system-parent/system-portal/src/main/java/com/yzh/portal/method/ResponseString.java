package com.yzh.portal.method;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseString {
	public static void respongString(HttpServletResponse res,String str) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(str);
		writer.flush();
		writer.close();
	}
}
