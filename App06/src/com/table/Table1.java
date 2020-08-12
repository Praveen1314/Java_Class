package com.table;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mysql.jdbc.Connection;


public class Table1 implements Servlet{
	Connection con=null;
	
	public Table1() {
		System.out.println("MyServlet1 : - 0 Param");
	}

	
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("MyServlet1 : getServletConfig");
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("MyServlet1 : getServletInfo");
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("MyServlet1 : init");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
					java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public void destroy() {
		System.out.println("MyServlet1 : destroy");
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("MyServlet1 : service");
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		pw.println("<html><body>");
		
		String ID_1=request.getParameter("ID_1");
		String age_1=request.getParameter("age_1");
		String first_name_1=request.getParameter("first_name_1");
		String last_name_1=request.getParameter("last_name_1");	
		
		float ID=Float.parseFloat(ID_1);
		float age=Float.parseFloat(age_1);
		
		
		pw.print("hii");
		pw.print("<br>");
		pw.print("ID= "+ID);
		pw.print("<br>");
		pw.print("age= "+age);
		pw.print("<br>");
		pw.print("first_name= "+first_name_1);
		pw.print("<br>");
		pw.print("last_name= "+last_name_1);
		
		pw.println("</html></body>");	
		
		
			
		
	}
}