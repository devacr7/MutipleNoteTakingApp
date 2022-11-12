<%@page import="com.dev.dao.AllNotesDAO"%>
<%@page import="com.dev.NotesWrapper"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
</head>
<style>

	.inlineBtn{
	    display:inline-block;
	}
	h2 {
	  text-decoration: underline green 2px;
	}
	.center {
	  margin-left: 10%;
	  width: 70%;
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
		margin-left: 20px;  
	}
	
	.notebtn {  
		border-radius: 5px;
		border: 2px solid green;
		background-color: white;
		width: 300px;  
		height: 40px;   
		margin-left: 20px;  
	}
	
	.delbtn {  
		border-radius: 5px;
		border: 2px solid green;
		background-color: white;
		width: 50px;  
		height: 40px;   
		margin-left: 20px;  
	}
	.textareaStyle {
		border-radius: 5px;
		border: 3px solid green;
		padding: 10px;
	} 
	.noteNameTxtBox {
		border-radius: 5px;
		border: 3px solid green;
		padding: 10px;
	}
	.btnCenter {
		margin-left: -50px;  
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
		session.setAttribute("isNewNote", false);
		AllNotesDAO notes = new AllNotesDAO();
		session.setAttribute("notesList", notes.getUserNotes((int)(session.getAttribute("uid"))));
	%>
	<div class="center centerText">
		<form action="logout" method="get">
			<h2 class="inlineBtn">Note Taking App</h2>
			<button class="btn button2" type="submit" name="logout">Logout</button><br>
			<h2>Hello ${uname}, Please find all your notes below.</h2><br>
		</form>
		<form action="addNewNote" method="post">
			<button class="notebtn button2 btnCenter inlineBtn" type="submit" name="addNew">+ Add new note +</button><br><br>
		</form>
		<form>
			<c:forEach items="${notesList}" var="note">
				
				<button class="notebtn button2" type="submit" value='<c:out value = "${note.notesId}"/>' formaction="setNoteId" formmethod="post" name="notesBtn" ><c:out value = "${note.noteName}"/></button>
				<button class="delbtn button2" type="submit" value='<c:out value="${note.notesId}"/>' formaction="DeleteNoteServlet" formmethod="post" name="delBtn"><i class="fa fa-trash"></i></button>
				<br>
				<br>
			</c:forEach>
		</form>
	</div>
</body>
</html>