<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>contact</title>

</head>

<body>
	<%@ include file="navbar_normal.jsp"%>

	<div class="container">
		<div class="col-md-6 offset-md-3">
			<div class="card">
				<div class="card-header">
					<h3 class="text-center">
						<span class="fa fa-envelope-o" aria-hidden="true"></span>&nbsp;
						Contact Us
					</h3>
				</div>
				<div class="card-body">
					<form id="contact-form" action="" method="post">
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">From
								Email address</label> <input required name="fromEmail" type="email"
								class="form-control" id="exampleInputEmail1">
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail2" class="form-label">To
								Email address</label> <input required name="email" type="email"
								class="form-control" id="exampleInputEmail2">
						</div>

						<div class="mb-3">
							<label for="exampleInputName3" class="form-label">subject</label>
							<input required name="subject" type="text" class="form-control"
								id="exampleInputName3">
						</div>

						<div class="mb-3">
							<label for="exampleInputName4" class="form-label">body</label> <input
								name="body" type="text" class="form-control"
								id="exampleInputName4">
						</div>

						<button id="send-btn" type="submit" class="btn btn-primary">Send</button>

					</form>
					<div id="loader" class="text-center" style="display: none;">
						<span class="fa fa fa-refresh fa-spin fa-3x"></span>
						<h5>Please wait...</h5>
					</div>
				</div>
				<div class="card-footer"></div>
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


	<script
		src="https://cdn.jsdelivr.net/npm/sweetalert@2.1.2/dist/sweetalert.min.js"></script>

	<script>
		$(document).ready(function() {
			console.log("ready");
			   $( "#contact-form" ).on( "submit", function( event ) { 
		  console.log( "Handler for `submit` called." );
			
		  event.preventDefault();
		  
		  let form = new FormData(this);
		  
		 	$("#send-btn").hide();
			$("#loader").show();
			
			$.ajax({
				url:"contact",
				type: "POST",
				data: form,
				success: function(data, textStatus, jqXHR){
					console.log(data);
					$("#send-btn").show();
					$("#loader").hide();
					
					if (data.trim() === "Done"){
						swal("Email sent Successfully. redirect to index.jsp page.")
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
					swal("Something went wrong, try again.");
					$("#send-btn").show();
					$("#loader").hide();
				},
				processData: false,
				contentType: false
			});
		  
		});
		});
	</script>
</body>
</html>