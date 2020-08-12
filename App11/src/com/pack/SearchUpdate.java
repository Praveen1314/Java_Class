 package com.pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Search")
public class SearchUpdate implements Servlet{
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
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		
		try{
			String qry="SELECT * FROM models WHERE s_no=?";
			
			String s_no1=request.getParameter("s_no");
			
			float s_no=Float.parseFloat(s_no1);
			
			PreparedStatement ps=con.prepareStatement(qry);
			
			ps.setFloat(1, s_no);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				pw.print("<form action='update1' method='post'><br>");
				pw.print("s_no<input type='number' name='s_no' readonly=true value="+rs.getInt(1)+"><br>");
				pw.print("company<input type='text' name='company' value="+rs.getString(2)+"><br>");
				pw.print("processor<input type='text' name='processor' value="+rs.getString(3)+"><br>");
				pw.print("colour<input type='text' name='colour' value="+rs.getString(4)+"><br>");
				pw.print("<input type='submit' value='update'>");
				pw.print("</form>");
			}
			else{
				pw.print("id not found");
			}
			
			ps.close();
			
		}catch(Exception e){
			e.printStackTrace();
			pw.print("error");
		}
		
		
	}

}
