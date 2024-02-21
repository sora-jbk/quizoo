<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>top</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/login.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/earlyaccess/nicomoji.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Zen+Maru+Gothic:wght@500&display=swap" rel="stylesheet">
<link rel="icon" type="image/png" href="img/favicon.png">
</head>

<body>
	

	
	<div class="container">
		<div class="icon">
		<img src="img/quizoo.png" alt="">
		</div>
		<div class="auth-form">
			<div class="form-selector">
				<div class="active" id="select-login">
					<p>Login</p>
				</div>
				<div class="" id="select-signup">
					<p>Signup</p>
				</div>
			</div>
			<form class="login-form" id="login-form" action="login" method="post">
				<input type="text" name="id" placeholder="UserId">
				<input type="password" name="password" placeholder="Password">
				<button><p>Login</p></button>
			</form>
			<form class="signup-form" id="signup-form" action="signup" method="post">
				<input type="text" name="id" placeholder="UserId">
				<input type="text" name="name" placeholder="Nickname">
				<input type="password" name="password" placeholder="Password">
				<input type="password" name="passwordAgain" placeholder="Password Again">
				<button><p>Signup</p></button>
			</form>
		</div>
	</div>
	
	
	
	
	
	<script src="js/login.js"></script>
</body>

</html>
