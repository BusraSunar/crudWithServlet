package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 try{
			 response.setContentType("text/html;charset=UTF-8");
			 PrintWriter out = response.getWriter();
			 request.getSession().setAttribute("loginYapildi", false);

			 Boolean valid=true;
			 String name= request.getParameter("name");
			 String email = request.getParameter("email");
	
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");
	
			 String stmt = "SELECT * From smth where name = ? and email = ?";
			 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(stmt);
			 pstmt.setString(1, name); 
			 pstmt.setString(2, email);
			 ResultSet rs = pstmt.executeQuery();
			 if(!rs.next()){
				 valid = false;
			 }
			 if(valid==false) {
					request.getSession().setAttribute("loginYapildi",false);
				   out.println("<meta http-equiv='refresh' content='2;URL=index.jsp'>");//redirects after 3 seconds
				   out.println("<p style='color:red;'>Name or email incorrect!</p>");
					

				   
			 }else {
				 request.getSession().setAttribute("loginYapildi", true);
				 response.sendRedirect("searchServlet");
			 }
		 }catch(Exception e) {}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}