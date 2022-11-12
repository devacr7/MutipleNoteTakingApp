<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Note Taking App - Login</title>
</head>
<style>
	h2 {
	  text-decoration: underline green 2px;
	}
	.center {
	  margin-top: 12%;
	  margin-left: 30%;
	  width: 40%;
	  border: 3px solid green;
	  padding: 20px;
	}
	.centerText {
	  text-align: center;
	}
	.btn {  
		border-radius: 5px;
		border: 2px solid green;
		background-color: white;
		width: 100px;  
		height: 40px;   
		margin-left: 200px;  
	} 
	.content {     
		margin-left: 50px;  
	} 
	.button2:hover {
		border: 2px solid black;
		background-color: #4CAF50; /* Green */
 		color: white;
		transition-duration: 0.6s;
	  	box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
	}
</style>
<body>
	<%
		//code for restricting access without login. 
		//--> using browser backbutton.
		response.setHeader("Cache-Control", "no-cache, no-control, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		session.setAttribute("requestType", "login");
	%>
	<div class="center">
		<h2 class="centerText">Note Taking App</h2><br>
		<h3 class="content">Login:</h3>
		<p class="content">Not a member? <a href="/MultipleNoteTakingApp/Signup.jsp">sign up here</a></p>
		<form action="login" method="post" class="content">
			Enter your Username : <input type="text" name="uname"><br><br>
			Enter your Password : <input type="password" name="pass"><br><br><br>
			<button class="btn button2" type="submit" name="submitBtn">Login</button>
		</form>
	</div>
</body>
</html>