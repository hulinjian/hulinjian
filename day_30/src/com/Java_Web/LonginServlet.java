package com.Java_Web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LonginServlet extends GenericServlet{
  
	@Override
	public void init() throws ServletException {
		System.out.println("初始化"+getServletConfig());
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获取的请求方式是GET还是POST
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		String method=httpServletRequest.getMethod();
		System.out.println(method);
		
		//1.获取请求参数：username，password
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("user:"+username+","+"password:"+password);
		
		//2.获取当前WEB应用的初始化参数：user，password。
		//ServletContext servletContext=getServletConfig().getServletContext();
		
		String initUser=getInitParameter("user");
		String initPassword=getInitParameter("password");
		
		PrintWriter out=response.getWriter();
		//3.比对
		//4.打印响应字符串
		if(initUser.equals(username)&&initPassword.equals(password)) {
			out.print("Hello:"+username);
		}else {
			out.print("Sorry："+username);
		}
		
	}

}
