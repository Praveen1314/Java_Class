package com.example;

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


@WebServlet("/update")
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
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("ldbc:mysql://localhost:3306/laptops","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		try{
		String qry="UPDATE models SET specs=?, name=?, years=? WHERE s_no=?";
		
		String s_no1=request.getParameter("s_no");
		String specs=request.getParameter("specs");
		String name=request.getParameter("name");
		String years1=request.getParameter("years");
		
		float s_no=Float.parseFloat(s_no1);
		float years=Float.parseFloat("years1");
		
		PreparedStatement ps=con.prepareStatement(qry);
		
		ps.setString(1, specs);
		ps.setString(2, name);
		ps.setFloat(3, years);		
		ps.setFloat(4, s_no);
		
		ps.executeUpdate();
		ps.close();
		pw.print("Updated !!! <a href='show'>Show Table</a>");
		}catch(Exception e){
			e.printStackTrace();
			pw.print("error");
		}
		
	}

}
