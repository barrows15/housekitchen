<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hkitchen.entities.User "%>
<%@ page import="com.hkitchen.dao.UserDao" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hkitchen.dao.ContactDao" %>
<%@ page import="com.hkitchen.entities.MessageDetail "%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>Reply</title>

</head>

<body>

	<%@ include file="navbar_normal.jsp"%>

<!-- user list -->

<div class="container">
	<div class="row">
		<div class="col md-4">
			<div class="list-group">
			  <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
			    All Users
			  </a>
			<% 
			UserDao userDao = new UserDao();
			ArrayList<User> userList1 = userDao.getAllUsers();
			for (User uu : userList1) {
			%>
			<a href="#" class="list-group-item list-group-item-action"> <%= uu.getId() %> &nbsp; <%= uu.getEmail() %></a>
			<%
			}
			%>	
			  
			 </div>

		</div>
		<div class="col md-8">
		<% 
			ContactDao contactDao = new ContactDao();
		   	ArrayList<MessageDetail> list2 = contactDao.getAllContacts();
		   	for (MessageDetail md : list2){
		   		%>
		   		<a id="cidLink" onClick ="sendReply('<%= md.getContactId() %>',this)" class="list-group-item list-group-item-action" href="#" > <%= md.getContactId()  %> &nbsp; <%= md.getName() %> </a>
		   		<% 
		   	}
		%>
		
		</div>
		<div  id="fighter" class="text-center">
			<span> <i class="fa fa-fighter-jet fa-spin fa-3x" aria-hidden="true"></i></span>
		</div>
		<div id="messages"> initial data</div>
	</div>

</div>



	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>


	<script
		src="https://cdn.jsdelivr.net/npm/sweetalert@2.1.2/dist/sweetalert.min.js"></script>

	<script>
		$(document).ready(function() {
			console.log("ready");
			$( "#user-form" ).on( "submit", function( event ) { 
			console.log( "user form Handler called." );
		  	//debugger;
			event.preventDefault();
		  	let form = new FormData(this);		  
		 	$.ajax({
				url:"GetAllUsers",
				type: "POST",
				data: form,
				success: function(data, textStatus, jqXHR){
					console.log(data);
				if (data.trim() === "Done"){
						swal("User lists.")
						.then((value) => {
						window.location="reply.jsp";
						});
					}
					else {
						swal(data);
					}	
				}, 
				error: function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR);
					swal("Something went wrong, try reply again.");
				},
				processData: false,
				contentType: false
			});
		  
		});
		});
	</script>
	

	<script>
		$(document).ready(function() {
			console.log("ready");
			$( "#reply-form" ).on( "submit", function( event ) { 
			console.log( "Handler for `submit` called." );
		  	//debugger;
			event.preventDefault();
		  	let form = new FormData(this);		  
		 	$("#send-btn").hide();
		 	$("#fighter").show();
			$("#loader").show();
			$.ajax({
				url:"reply",
				type: "POST",
				data: form,
				success: function(data, textStatus, jqXHR){
					console.log(data);
					$("#send-btn").show();
					$("#loader").hide();
					
					if (data.trim() === "Done"){
						swal("Reply sent Successfully. redirect to index.jsp page.")
						.then((value) => {
						window.location="index.jsp";
						});
					}
					else {
						swal(data);
					}	
				}, 
				error: function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR);
					swal("Something went wrong, try reply again.");
					$("#send-btn").show();
					$("#loader").hide();
				},
				processData: false,
				contentType: false
			});
		  
		});
		});
	</script>
	
		<script>
		function sendReply(contactId,temp){
			$(".cidLink").removeClass('active');
			console.log("fucntion"+contactId);
		
			$.ajax({
				url  : 'load_reply.jsp',
				data : { cId : contactId},
				success : function(data,textStatus,jqXHR){
					console.log(data);
					$("#messages").html(data);
					$(temp).addClass('active');
					
				}
			});
			
		};
	
	</script>
	

	
</body>
</html>