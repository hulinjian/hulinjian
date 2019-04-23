package com.Java_Web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 自定义一个Servlet接口实现类：让开发的任何Servlet都继承该类
 * @author Administrator
 *
 */
public abstract class MyGenericServlet implements Servlet,ServletConfig {
  
	@Override
	public void destroy() {}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	private ServletConfig servletConfig;
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.servletConfig=config;
		init();
	}

	public  void init()throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException ;

	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return servletConfig.getInitParameter(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return servletConfig.getInitParameterNames();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return servletConfig.getServletContext();
	}

	@Override
	public String getServletName() {
		// TODO Auto-generated method stub
		return servletConfig.getServletName();
	}


}
