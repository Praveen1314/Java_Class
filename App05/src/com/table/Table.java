package com.table;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Table implements Servlet{
	
	public Table() {
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
	}

	@Override
	public void destroy() {
		System.out.println("MyServlet1 : destroy");
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MyServlet1 : services");
		
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");  
        try 
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
    		   
            Statement stmt = connection.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from persons");  
           out.println("table");
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>ID</th><th>age</th><th>first_name</th><th>last_name</th></tr>");  
            while (rs.next()) 
            {  
                int n1 = rs.getInt("ID");  
                int n2 = rs.getInt("age");  
                String n3 = rs.getString("first_name"); 
                String n4 = rs.getString("last_name"); 
             
                out.println("<tr><td>" + n1 + "</td><td>"  + n2 + "</td><td>" + n3 + "</td><td>" + n4 + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            connection.close();  
           }  
            catch (Exception e) 
           {  
            e.printStackTrace();
            	out.println("error");  
        }
	}
	}
