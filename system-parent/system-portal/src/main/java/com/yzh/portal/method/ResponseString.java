package com.yzh.portal.method;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseString {
	public static void respongString(HttpServletResponse res,String msg) throws IOException{
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(msg);
		writer.flush();
		writer.close();
	}
}
