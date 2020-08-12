package com.main;

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

@WebServlet("/search_update")
public class SerachForUpdate implements Servlet{
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
	try{
		
		String qry="SELECT * FROM persons WHERE ID=?";
		String ID1=request.getParameter("ID");
	/*	String age1=request.getParameter("age");
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");  */
	
		float ID=Float.parseFloat(ID1);
	//	float age=Float.parseFloat(age1);
	
		PreparedStatement ps=con.prepareStatement(qry);
		
		ps.setFloat(1, ID);
	/*	ps.setFloat(2, age);
		ps.setString(3, first_name);
		ps.setString(4, last_name);  */
		
		//ps.executeQuery();
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			pw.print("<form action='update' method='post'>");
			pw.print("ID<input type='number' name='ID' readonly=true value="+rs.getInt(1)+"><br>");
			pw.print("age<input type='number' name='age' value="+rs.getString(2)+"><br>");
			pw.print("first_name<input type='text' name='first_name' value="+rs.getString(3)+"><br>");
			pw.print("last_name<input type='text' name='last_name' value="+rs.getString(4)+"><br>");
			pw.print("<input type=\"submit\" value=\"update\">");
			pw.print("</form>");
			
		}
		else{
			pw.println("ID is wrong");
		}
		ps.close();
		
		
	}catch (Exception e){
		e.printStackTrace();
		pw.println("error");
	}
	}

}
