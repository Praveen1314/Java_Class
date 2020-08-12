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
import javax.servlet.annotation.WebServlet;

@WebServlet("/searchupdate")
public class ShowUpdate implements Servlet{
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
		response.setContentType("text/html");
		
		try{
			String qry="SELECT * FROM models where s_no=?";
			
			String s_no1=request.getParameter("s_no");
			float s_no=Float.parseFloat(s_no1);
			
			PreparedStatement ps=con.prepareStatement(qry);
			
			
			ps.setFloat(1, s_no);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				pw.print("<form action='update' method='post'><br>");
				pw.print("s_no<input type='number' name='s_no' readonly=true value="+rs.getInt(1)+"><br>");
				pw.print("specs<input type='text' name='specs' value="+rs.getString(2)+"><br>");
				pw.print("name<input type='text' name='name' value="+rs.getString(3)+"><br>");
				pw.print("years<input type='text' name='years' value="+rs.getString(4)+"><br>");
				pw.print("<input type='submit' value='update'>");
				pw.print("</form>");
			}
			}catch(Exception e){
			e.printStackTrace();
			pw.print("id not found");
		}
	}

}
