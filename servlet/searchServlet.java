package model;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> list=new ArrayList<User>();
		try{
			if(request.getSession().getAttribute("loginYapildi").equals(true)) {
		     Class.forName("com.mysql.jdbc.Driver");
		           Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");
		           Statement st=(Statement) con.createStatement();
		           String query = request.getParameter("q");//bu search inputun name i 
		          
				   String sql="";
					if(query!=null){
						sql = "SELECT * FROM smth WHERE name LIKE '%"+query+"%' OR email LIKE '%"+query+"%'";
					}else{
						sql ="SELECT * FROM smth";
					}
		           ResultSet rs=st.executeQuery(sql);
		           while(rs.next()){
		              User u= new User();
		        	  u.setId(rs.getString("id"));
		              u.setName(rs.getString("name"));
		              u.setEmail(rs.getString("email"));
		              list.add(u);

		           }
		           
		           request.setAttribute("data", list);
		           request.getRequestDispatcher("table.jsp").forward(request, response);
			}else {
				response.sendRedirect("index.jsp");

				
			}
		           //rd.forward(request, response);
		}
		catch(Exception e){}
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> list=new ArrayList<User>();
		try{
			if(request.getSession().getAttribute("loginYapildi").equals(true)) {
		     Class.forName("com.mysql.jdbc.Driver");
		           Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");
		           Statement st=(Statement) con.createStatement();
		           String query = request.getParameter("q");//bu search inputun name i 
		          
				   String sql="";
					if(query!=null){
						sql = "SELECT * FROM smth WHERE name LIKE '%"+query+"%' OR email LIKE '%"+query+"%'";
					}else{
						sql ="SELECT * FROM smth";
					}
		           ResultSet rs=st.executeQuery(sql);
		           while(rs.next()){
		              User u= new User();
		        	  u.setId(rs.getString("id"));
		              u.setName(rs.getString("name"));
		              u.setEmail(rs.getString("email"));
		              list.add(u);

		           }
		           
		           request.setAttribute("data", list);
		           request.getRequestDispatcher("table.jsp").forward(request, response);
			}else {
				response.sendRedirect("index.jsp");

				
			}
		           //rd.forward(request, response);
		}
		catch(Exception e){}
	
	
	}

}
