package model;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> list=new ArrayList<User>();
		try{
			//if the user logged in show the page 
			if(request.getSession().getAttribute("loginYapildi").equals(true)) {
				
				//connect to database
				Class.forName("com.mysql.jdbc.Driver");
	            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");
	            Statement st=(Statement) con.createStatement();
	            
	            //get input (if there is) from the search bar
	            String query = request.getParameter("searchBar");
	          
	            //to display the table look if search bar is empty or not
			    String sql="";
				if(query!=null){
					sql = "SELECT * FROM smth WHERE name LIKE '%"+query+"%' OR email LIKE '%"+query+"%'";
				}else{
					sql ="SELECT * FROM smth";
				}
	            ResultSet rs=st.executeQuery(sql);
	            
	            //get the results from the executeQuery method
	            while(rs.next()){
	            	User u= new User();
	            	u.setId(rs.getString("id"));
	            	u.setName(rs.getString("name"));
	            	u.setEmail(rs.getString("email"));
	            	list.add(u);
	            }
	           //set the data variable to list to show our table in table.jsp
	            request.setAttribute("data", list);
	            request.getRequestDispatcher("table.jsp").forward(request, response);
			}else {
				response.sendRedirect("index.jsp");
			}
		          
		}
		catch(Exception e){}
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> list=new ArrayList<User>();
		try{
			//if the user logged in show the page 
			if(request.getSession().getAttribute("loginYapildi").equals(true)) {
				
				//connect to database
				Class.forName("com.mysql.jdbc.Driver");
	            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");
	            Statement st=(Statement) con.createStatement();
	            
	            //get input (if there is) from the search bar
	            String query = request.getParameter("searchBar");
	          
	            //to display the table look if search bar is empty or not
			    String sql="";
				if(query!=null){
					sql = "SELECT * FROM smth WHERE name LIKE '%"+query+"%' OR email LIKE '%"+query+"%'";
				}else{
					sql ="SELECT * FROM smth";
				}
	            ResultSet rs=st.executeQuery(sql);
	            
	            //get the results from the executeQuery method
	            while(rs.next()){
	            	User u= new User();
	            	u.setId(rs.getString("id"));
	            	u.setName(rs.getString("name"));
	            	u.setEmail(rs.getString("email"));
	            	list.add(u);
	            }
	           //set the data variable to list to show our table in table.jsp
	            request.setAttribute("data", list);
	            request.getRequestDispatcher("table.jsp").forward(request, response);
		}else {
			response.sendRedirect("index.jsp");
		}
		          
		}
		catch(Exception e){}
	
	
	}

}
