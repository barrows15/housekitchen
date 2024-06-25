<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hkitchen.entities.User"%>
<%@ page errorPage="error.jsp"%>
<%
User puser = (User) session.getAttribute("currentuser");
if (puser == null) {
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

	<!-- navbar start -->

	


<nav class="navbar navbar-expand-lg navbar-light bg-light ">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
    <span><i class="fa fa-handshake-o" aria-hidden="true"></i></span>
    Ashutosh Technology</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">
          <span><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; </span>
          Home</a>
        </li>
        <li class="nav-item">
        
          <a class="nav-link" href="login.jsp">
          <span><i class="fa fa-user-circle" aria-hidden="true"></i></span>
          Login</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="signup.jsp">
          <span>
			<i class="fa fa-users fa-1x" aria-hidden="true"></i>&nbsp; 
			</span>
          Sign up</a>
        </li>
        
         <li class="nav-item">
          <a class="nav-link" href="contact.jsp">
          <span> <i class="fa fa-envelope-o" aria-hidden="true"></i></span>&nbsp;
          contact</a>
        </li>
       
         <li class="nav-item">
          <a class="nav-link" href="about.jsp">
          <span><i class="fa fa-info" aria-hidden="true"></i></span>
          Help</a>
        </li>


		<li class="nav-item">
			<a class="nav-link" href="logout">
				<span><i class="fa fa-sign-out" aria-hidden="true"></i></span>
			Logout</a>
		</li>
					
		<li class="nav-item">
		<a class="nav-link" href="#!"
			data-bs-toggle="modal" data-bs-target="#profileModal"> <span> <i class="fa fa-pencil-square-o" aria-hidden="true"></i> </span>
			<%=puser.getName()%>
		</a>
		</li>
				
        
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
      
    </div>
  </div>
</nav>
	<!-- navbar end -->

	<%
	String msg = (String) session.getAttribute("loginMessage");
	if (msg != null) {
	%>
	<%=msg%>
	<%
	session.removeAttribute("loginMessage");
	}
	%>

	<%
	String pmsg = (String) session.getAttribute("profileMessage");
	if (pmsg != null) {
	%>
	<%=pmsg%>
	<%
	session.removeAttribute("profileMessage");
	}
	%>

	<!-- Modal -->
	<div class="modal fade" id="profileModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header ">
					
					<h3 class="modal-title" id="exampleModalLabel">Edit User
						Profile</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="profile-form" action="profile" method="post">
						<div class="mb-3">
							<label for="exampleInputId1" class="form-label">User ID</label> <input
								name="id" type="text" class="form-control" id="exampleInputId1"
								aria-describedby="idHelp" value='<%=puser.getId()%>' >
						</div>
						<div class="mb-3">
							<label for="exampleInputName1" class="form-label">Name</label> <input
								name="name" required type="text" class="form-control"
								id="exampleInputName1" aria-describedby="nameHelp"
								value='<%=puser.getName()%>'>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Email
								address</label> <input required name="email" type="email"
								class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" value='<%=puser.getEmail()%>'>
							<div id="emailHelp" class="form-text">We'll never share
								your email with anyone else.</div>
						</div>
						<div class="mb-3">
							<label for="exampleInputPassword1" class="form-label">Password</label>
							<input required name="password" type="password"
								class="form-control" id="exampleInputPassword1">
						</div>
						<div class="text-center">
						<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
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
	<script>
		$(document).ready(function(e) {
			console.log("ready..");
			/* $("#profile-form").on("submit", function(event){
				event.preventDefault();		
				console.log("profile-form");
				let f = new FormData(this);
				console.log(f);
				$.ajax({
					url: "profile",
					method: "POST",
					data: f,
					success: (f,status,jqXHR),
					failure: (jqXHR, status, error),
					processData: false,
					contentData: false
				});
			}); */
		});
	</script>
</body>
</html>
