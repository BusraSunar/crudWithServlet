<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.sql.*"  import = "java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
	  <!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	    <link rel="stylesheet" href="rehberStyle.css">
	    <script src="https://kit.fontawesome.com/41c5e081d3.js"></script>
	    <title>Rehber</title>
	</head>
	<body >
	    <header >
	        <h1 >Rehber</h1>
	    </header>
	    
	    <div class="search-box">
    		<form action="search" method="post" class="form">
		        <input class="search-txt" name="q" placeholder="Type to search">
		        <a href="#" class="search-btn" style="text-decoration: none;">
		            <i class="fas fa-search"></i>
		        </a>
	        </form>
	    </div>
	    
		    <a href="addNew.jsp" class="btn2" style="text-decoration: none;">Add New Data</a>
		    <form action="logout" method="post">
		   		<button  class="btn3" type="submit"  onclick="form.action='logoutServlet'">Logout</button>
	    	</form>
	    <form action="display" method="get" id="disp">
        <table id="rehber" align="center" >

        <thead>
            <tr bgcolor="#333">
                <th  style="width: 0%;"><font color="#fff">ID</font></th>
                <th  style="width: 0%;"><font color="#fff">NAME</font></th>
                <th  style="width: 0%;"><font color="#fff">EMAIL</font></th>
                <th  style="width: 100%;"><font color="#fff">ACTION</font></th>    
            </tr>
        </thead>
        <TBody>
			<c:forEach items="${data}" var="list">
			<tr>
			
				<td><input readonly name="id" id="id" value="<c:out value="${list.id}"/>"></td>
				<td><input name="name" id="name" value="<c:out value="${list.name}"/>"></td>
				<td><input name="email" id="email" value="<c:out value="${list.email}"/>"></td>
				<td>
					<a href="deleteServlet?id=${list.id}" style="text-decoration: none; background: rgb(163, 2, 2);" class="edit"   >Delete</a>
				</td>
			</tr>
			</c:forEach>
        </TBody>
    </table>
    <button type="submit" onclick="form.action='beforeEditServlet';" class="btn4" >Update</button>
    </form>	
	</body>
	</html>
		
		
