<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	//code for restricting access without login. 
	//--> using browser backbutton.
	response.setHeader("Cache-Control", "no-cache, no-control, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
		function alertName(){
			alert("User created Successfully!!!");
			window.location.href = "Login.jsp";
		} 
	</script> 
	<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>