package com.Java_Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class LoginServlet2 extends MyHttpServletRequest {
   
	@Override
	public void doPost(HttpServletRequest reqest, HttpServletResponse response) {
		//HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		String method=reqest.getMethod();
		System.out.println(method);
		
		//1.获取请求参数：username，password
	/*	String username=request.getParameter("username");
		String password=request.getParameter("password");*/
		//System.out.println("user:"+username+","+"password:"+password);
		
		//2.获取当前WEB应用的初始化参数：user，password。
		//ServletContext servletContext=getServletConfig().getServletContext();
		
		String initUser=getServletContext().getInitParameter("user");
		String initPassword=getServletContext().getInitParameter("password");
		
		//PrintWriter out=response.getWriter();
		//3.比对
		//4.打印响应字符串
		/*if(initUser.equals(username)&&initPassword.equals(password)) {
			out.print("Hello:"+username);
		}else {
			out.print("Sorry："+username);
		}
	}*/
	
	/*@Override
	public void bind(InetSocketAddress addr, int backlog) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setExecutor(Executor executor) {
		// TODO Auto-generated method stub

	}

	@Override
	public Executor getExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stop(int delay) {
		// TODO Auto-generated method stub

	}

	@Override
	public HttpContext createContext(String path, HttpHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpContext createContext(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeContext(String path) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeContext(HttpContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public InetSocketAddress getAddress() {
		// TODO Auto-generated method stub
		return null;
	}*/
	}
}

