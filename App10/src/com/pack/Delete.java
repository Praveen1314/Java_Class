package com.pack;

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

//@WebServlet("/Delete")
public class Delete implements Servlet{
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
		PrintWriter pw=response.getWriter();
		pw.println("Deleted");
		
		try{
			
		String qry="delete from models where s_no=?";
		String s_no1=request.getParameter("s_no");
		
		float s_no=Float.parseFloat(s_no1);
		
		PreparedStatement ps=con.prepareStatement(qry);
		
		ps.setFloat(1, s_no);
		
		ps.execute();
		ps.close();
		pw.print("deleted <a href='Show'>ShowAll</a>");
		}catch(Exception e){
			e.printStackTrace();
			pw.print("error");
		}
				
		
	}

}
