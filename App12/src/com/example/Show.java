package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebServlet;


//@WebServlet("/show")
public class Show implements Servlet{
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
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/laptops","root","");
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
		//pw.print("show");
		
		
		try{
			String qry="SELECT * FROM models";
			
			pw.println("<table border=5 width=70% height=70%>");
			pw.println("<tr><th>s_no</th><th>specs</th><th>name</th><th>years</th></tr>");
			
		PreparedStatement ps=con.prepareStatement(qry);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			int n1=rs.getInt("s_no");
			String n2=rs.getString("specs");
			String n3=rs.getString("name");
			int n4=rs.getInt("years");
			
			pw.println("<tr><td>"+ n1 +"</td><td>"+ n2 +"</td><td>"+ n3 +"</td><td>"+ n4 +"</tr></td>");
		}
		pw.println("</table>");
		pw.println("</body></html>");
		
		}catch(Exception e){
			e.printStackTrace();
			pw.print("error");
		}
		
	}

}
