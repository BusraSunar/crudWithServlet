package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;

@WebServlet("/beforeEditServlet")
public class beforeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public beforeEditServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<User> database=new ArrayList<User>();
			
			//connect to database
	  		Class.forName("com.mysql.jdbc.Driver");
            		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/idk?autoReconnect=true&useSSL=false", "root", "");
			//get array of ids
            		String[] idStr = request.getParameterValues("id");
           		Statement st=(Statement) con.createStatement();
          
			/*my object is to compare two sets if they are different than each other copy the arrays to resultsets
			i will compare data and list, data is a list thats filled from the database, list is from the inputs so 
			when i compare list and data i will see the difference between them and assign list to data 
			*/
			
			//get array of names and emails
			String[] name = request.getParameterValues("name");
			String[] email = request.getParameterValues("email");
			
			//fill list with our name, email, id arrays
			ArrayList<User> list=new ArrayList<User>();
			for(int i=0;i<idStr.length;i++) {
				User u= new User();
				u.setId(idStr[i]);
				u.setName(name[i]);
				u.setEmail(email[i]);
				list.add(u);
			}
			
			//fill data from the database
		        String sql ="SELECT * FROM smth";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				User u= new User();
				u.setId(rs.getString("id"));
			        u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				database.add(u);
		        }
			PreparedStatement stmt=null;
			//compare data and list and if there is a difference assign list to data 
			for(int i=0;i<list.size();i++) {
				if(database.get(i).getId().equals(list.get(i).getId())) {
					if(!database.get(i).getName().equals(list.get(i).getName()) || !database.get(i).getEmail().equals(list.get(i).getEmail())) {
						String update="UPDATE smth SET name=?, email=? WHERE id='" +database.get(i).getId() +"'";
						stmt = (PreparedStatement) con.prepareStatement(update);
						stmt.setString(1,list.get(i).getName());
						stmt.setString(2,list.get(i).getEmail());
						stmt.executeUpdate();
					}
				}
			    }

  			response.sendRedirect("searchServlet");
	}catch(Exception e) {}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
