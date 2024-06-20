<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>sign up</title>

</head>

<body>

<%@ include file="navbar_normal.jsp" %>

	<div class="container">
		<div class="col-md-6 offset-md-3">
			<div class="card">
			<div class="card-header">
			<h3 class="text-center">
			<span>
			<i class="fa fa-users fa-2x" aria-hidden="true"></i>&nbsp; 
			</span>
			 Sign up </h3>
			</div>
			<div class="card-body">
			<form id="register-form" action=" " method="post" >
			  <div class="mb-3">
			    <label for="exampleInputName1" class="form-label">Name</label>
			    <input required name="name" type="text" class="form-control" id="exampleInputName1" aria-describedby="nameHelp">
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Email address</label>
			    <input required name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputPassword1" class="form-label">Password</label>
			    <input required name="password" type="password" class="form-control" id="exampleInputPassword1">
			  </div>
			  <div class="mb-3 form-check">
			    <input type="checkbox" class="form-check-input" id="exampleCheck1">
			    <label class="form-check-label" for="exampleCheck1">Check me out</label>
			  </div>
			  <div id="loader" class="text-center" style="display: none;" >
			  	<span class="fa fa fa-refresh fa-spin fa-3x" ></span>
			  	<h5>Please wait...</h5>
			  </div>
			  <button id="submit-btn" type="submit" class="btn btn-primary">Submit</button>
			</form>
			</div>
			<div class="card-footer"></div>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>

<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"	crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert@2.1.2/dist/sweetalert.min.js"></script>

	<script>
		$(document).ready(function() {
			console.log("ready");
			$("#register-form").on("submit", function(event) {
				event.preventDefault();
				console.log("clicked");
				$("#submit-btn").hide();
				$("#loader").show();
				let form = new FormData(this);
				$.ajax({
					url:"register",
					type: "POST",
					data: form,
					success: function(data, textStatus, jqXHR){
						console.log(data);
						$("#submit-btn").show();
						$("#loader").hide();
						if (data.trim() === "Done"){
							swal("Registered Successfully. redirect to login page.")
							.then((value) => {
							window.location="login.jsp";
							});
						}
						else {
							swal(data);
						}	
					}, 
					error: function(jqXHR, textStatus, errorThrown){
						console.log(jqXHR);
						swal("Something went wrong, try again.");
						$("#submit-btn").show();
						$("#loader").hide();
					},
					processData: false,
					contentType: false
				});
				console.log(form);
			});
		});
	</script>
</body>
</html>