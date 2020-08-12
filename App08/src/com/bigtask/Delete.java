package com.bigtask;

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

@WebServlet("/big")
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
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
		}catch (ClassNotFoundException e) {
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
		
		response.setContentType("text/html");
		
		PrintWriter pw=response.getWriter();
		
		try{
			String qry="DELETE from persons where ID=?, age=?, first_name=?, last_name=?";
			
			String ID1=request.getParameter("ID_1");
			String age=request.getParameter("age_1");
			String first_name=request.getParameter("first_name_1");
			String last_name=request.getParameter("last_name_1");
				
			float ID=Float.parseFloat(ID1);
			float age1=Float.parseFloat(age);
			
			PreparedStatement ps=con.prepareStatement(qry);
			
			ps.setFloat(1, ID);
			ps.setFloat(2, age1);
			ps.setString(3, first_name);
			ps.setString(4, last_name);
			
			ps.execute();
			ps.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			pw.println("error");
		}
				
	}


}
