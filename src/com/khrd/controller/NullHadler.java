package com.khrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHadler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 error
		return null;
	}
	
	
}
