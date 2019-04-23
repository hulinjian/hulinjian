package com.Java_Web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHttpServletRequest extends MyGenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		if(req instanceof HttpServletRequest) {
			HttpServletRequest request=(HttpServletRequest)req;
			
			if(res instanceof HttpServletResponse) {
				HttpServletResponse response=(HttpServletResponse)res;
				
				service(request,response);
			}
		}

	}
 
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  //1.获取请求方式。
		String method=req.getMethod();
		
	  //2.根据请求方法再调用对应的处理方法。
		if("GET".equalsIgnoreCase(method)) {
			doGet(req,res);
		}
		if("POST".equalsIgnoreCase(method)) {
			doPost(req,res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
	}
}
