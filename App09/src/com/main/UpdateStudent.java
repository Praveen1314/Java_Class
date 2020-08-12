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

//@WebServlet("/update")
public class UpdateStudent implements Servlet{
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
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		//pw.print("updated");
		
		try{
			String qry="UPDATE persons SET age=?, first_name=?, last_name=? where ID=?";
			
			String ID1=request.getParameter("ID");
			String age1=request.getParameter("age");
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
		
		int ID=Integer.parseInt(ID1);
		int age=Integer.parseInt(age1);
		
		PreparedStatement ps=con.prepareStatement(qry);
		
		
		ps.setInt(1, age);
		ps.setString(2, first_name);
		ps.setString(3, last_name);
		ps.setInt(4, ID);
		
		ps.executeUpdate();
		
		/*ResultSet rs=ps.executeQuery();
		while(rs.next()){
			pw.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}*/
		
		ps.close();
		pw.print("updated !!! <a href='show'>view all student</a>");
	}catch(Exception e)
	{
		e.printStackTrace();
		pw.println("error");
	}
		
	}

}
