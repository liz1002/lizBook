package com.khrd.handler.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;

public class UserLogoutHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		session.removeAttribute("Auth");
		resp.sendRedirect(req.getContextPath() + "/product/list.do");
		return null;
	}

}
