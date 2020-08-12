package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/delete")
public class DeleteStudent implements Servlet{
Connection con=null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void init(ServletConfig cfgs) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName(cfgs.getInitParameter("driverClassName"));
			con=DriverManager.getConnection(cfgs.getInitParameter("dbUrl"), cfgs.getInitParameter("username"), cfgs.getInitParameter("password"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void service(ServletRequest request, ServletResponse response)	throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("Deleted");
		
		try{
			String qry="DELETE from persons where ID=?";
			String ID1=request.getParameter("ID");
	
		
		float ID=Float.parseFloat(ID1);
	
		
		PreparedStatement ps=con.prepareStatement(qry);
		
		ps.setFloat(1, ID);
	
		ps.execute();
		ps.close();
		
		pw.print("Deleted !!!<a href='show'>show all students</a>");
	}catch(Exception e)
	{
		e.printStackTrace();
		pw.println("error");
	}
	}

}
