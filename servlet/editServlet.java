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
import javax.servlet.http.HttpSession;

import java.sql.*;
@WebServlet("/editServlet")
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public editServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try{
				
			    Class.forName("com.mysql.jdbc.Driver");
	            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");	
	            
	            HttpSession session = request.getSession();
	            String data = (String) session.getAttribute("editData");
	      		String id2 = data;
	      	  	String name = request.getParameter("name");
	      		String email = request.getParameter("email");
	      		
	      		PreparedStatement stmt=null;
      			String update="UPDATE smth SET name=?, email=? WHERE id='" +id2 +"'";
      			stmt = (PreparedStatement) con.prepareStatement(update);
      			stmt.setString(1,name);
      			stmt.setString(2,email);
      			stmt.executeUpdate();
      			response.sendRedirect("searchServlet");
   
			}
			catch(Exception e){}
		
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
