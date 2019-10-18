package com.khrd.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControllerUsingURI extends HttpServlet{
	private HashMap<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		//configFile : //WEB-INF/commandHandler.properties
		String configFile = getInitParameter("configFile"); // web.xml의 init-param > param-name 의 값과 대소문자까지 같아야함
		//properties 파일의 절대 주소를 가져옴
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		try(FileReader fis = new FileReader(configFilePath)){
			prop.load(fis);
		}catch (Exception e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator(); 
		
		while (keyIter.hasNext()) {
			String	command = (String) keyIter.next(); // : /simple.do
			//hadlerClassName : com.khrd.handler.SimpleHandler
			String handlerClassName = prop.getProperty(command);
			try {
				//handler = new SimpleHandler();
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handler = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handler);
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}
	} //init

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}//doGet

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}//doPost
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath()) == 0){ // : /chapter18/simple.do
			// request.getContextPath() : /chapter18
			command = command.substring(req.getContextPath().length());
			// command : /simple.do
		}
		
		//command에 해당하는 class를 가져옴
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) {
			handler = new NullHadler();
		}
		
		String viewPage = null; //화면에 보일 jsp파일
			try {
				viewPage = handler.process(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			
			if(viewPage != null) {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
	}//process
	
}
