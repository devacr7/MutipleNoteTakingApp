<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.dev.dao.RetrieveNotesFromDB"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Note Taking App</title>
</head>
<style>
	.inlineBtn{
	    display:inline-block;
	}
	h2 {
	  text-decoration: underline green 2px;
	}
	.center {
	  margin-top: 5%;
	  margin-left: 12%;
	  width: 70%;
	  padding: 20px;
	}
	.centerText {
	  text-align: center;
	}
	.centerTextHeading {
		margin-left: 425px;
	}
	.btn {  
		border-radius: 5px;
		border: 2px solid green;
		background-color: white;
		width: 100px;  
		height: 40px;   
		margin-left: 20px;  
	}
	.textareaStyle {
		border-radius: 5px;
		border: 3px solid green;
		padding: 10px;
	} 
	.noteNameTxtBox {
		width: 750px;
		height: 30px;
		border-radius: 5px;
		border: 3px solid green;
		padding: 10px;
	}
	.btnCenter {
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
		/*response.setHeader("Cache-Control", "no-cache, no-control, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);*/
		//--> by directly hitting page URL. redirecting user to login page.
		System.out.println("uname:"+session.getAttribute("uname"));
		if(session.getAttribute("uname") == null) {
			response.sendRedirect("Login.jsp");
		}
		
		
		String storedNotes = "";
		String storedName = "";
		String notes[] = new String[2];
		
		if(!(boolean)session.getAttribute("isNewNote") == true) {
			int noteId = (int)session.getAttribute("currentNoteId");
			session.setAttribute("currentNoteId", noteId);
			//Retrieving data form DB to show the notes in all scenarions.
			RetrieveNotesFromDB notesDB = new RetrieveNotesFromDB();
			notes = notesDB.retrieveNotes(noteId);
			storedNotes = notes[0];
			storedName = notes[1];
		}
		System.out.println("storedNotes:"+storedNotes);
	%>
	<div class="center">
		<form action="logout" method="get">
			<h2 class="inlineBtn centerTextHeading">Note Taking App</h2>
	    	<button  class="btn button2 inlineBtn btnCenter" type="submit">Logout</button>
	    </form>
		<h3 class="centerText">Please take your notes down below.</h3><br>
		<form  class="centerText" action="saveNotes" method="post">
			<input class="noteNameTxtBox inlineBtn" type="text" name="noteName" value="<%out.println(storedName);%>"><br><br>
			<textarea class="textareaStyle" rows="20" cols="100" name="notes"><% out.println(storedNotes); %></textarea><br><br>
		    <button  class="btn button2" type="submit" name="saveNotes">Save</button>
		    <button  class="btn button2" type="submit" name="deleteNotes" formaction="deleteNotes" formmethod="post">Delete</button>
		    <button  class="btn button2" type="submit" name="back" formaction="backButton" formmethod="post">Back</button>
	    </form><br>
    </div>
</body>
</html>