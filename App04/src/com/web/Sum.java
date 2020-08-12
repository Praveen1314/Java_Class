package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Sum implements Servlet{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter p1=response.getWriter();
		
		String n1=request.getParameter("n1");
		String n2=request.getParameter("n2");
		
		double number1=Double.parseDouble(n1);
		double number2=Double.parseDouble(n2);
		double sum=number1+number2;
		
		p1.print("hii");
		p1.print("<br>");
		p1.print("n1= "+n1);
		p1.print("<br>");
		p1.print("n2= "+n2);
		p1.print("<br>");
		p1.print("sum= "+sum);
		
	}

}
