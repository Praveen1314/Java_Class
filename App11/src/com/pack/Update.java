package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/update1")
public class Update implements Servlet{
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
		ServletContext sc=cfgs.getServletContext();
		try {
			Class.forName(sc.getInitParameter("driverClassName"));
			con=DriverManager.getConnection(sc.getInitParameter("dbUrl"), sc.getInitParameter("username"), sc.getInitParameter("password"));
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
		
		
		try{
			String qry="UPDATE models SET company=?, processor=?, colour=? WHERE s_no=?";
			
			String s_no1=request.getParameter("s_no");
			String company=request.getParameter("company");
			String processor=request.getParameter("processor");
			String colour=request.getParameter("colour");
		
			float s_no=Float.parseFloat(s_no1);
			
			PreparedStatement ps=con.prepareStatement(qry);
			
		
		ps.setString(1, company);
		ps.setString(2, processor);
		ps.setString(3, colour);
		ps.setFloat(4, s_no);
		
		ps.executeUpdate();
		ps.close();
		
			pw.print("Updated !!! <a href='Show'> ShowAll </a>");
		}catch(Exception e){
			e.printStackTrace();
			pw.print("error");
		}
		
		
		
	}

}
