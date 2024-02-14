<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>top</title>
<link rel=”icon” href= "img/favion.ico">
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
	
</head>

<body>
	<div class="container">
		<div class="row">
			<!-- 左側のコンテンツ -->
			<div class="col-md-6">
				<div class="left-contents text-center">
					<div class="top-img flex-shrink-0">
						<img src="img/quizoo.png" alt="top"><br>
						<div class="message">welcome to Quizoo!!</div>
					</div>
				</div>
			</div>

			<!-- 右側のコンテンツ -->
			<div class="col-md-6">
				<div class="container flex-grow-1 ms-3">
					<div class="row justify-content">
						<div class="fixed-col-md-7">
							<div class="card">
								<div class="card-header teal text-white">
									<ul class="nav nav-tabs nav-fill" id="myTabs">
										<li class="nav-item"><a class="nav-link active"
											id="login-tab" data-toggle="tab" href="#login" role="tab">Login</a>
										</li>
										<li class="nav-item"><a class="nav-link" id="signup-tab"
											data-toggle="tab" href="#Signup" role="tab">Sign up</a></li>
									</ul>
								</div>
								<div class="card-body">
									<div class="tab-content">
										<div class="tab-pane fade show active" id="login"
											role="tabpanel">
											<form id="login-form" method="post" action="login">
												<div class="form-group">
													<label for="login-userid"></label>
														<input type="text"
																class="textbox"
																name="id"
																id="login-username"
																placeholder="UserID"
																required
																inputmode="latin" >
														<p id="login-errorMessage" class="error">
															英数字および記号以外は使えません
														</p>
												</div>
												<div class="form-group">
													<label for="login-password"></label>
														<input type="password"
																class="textbox"
																name="password"
																id="login-password"
																placeholder="Password"
																required>
												</div>
												<div class="miss none" id="miss">
													<p>IDまたはPASSWORDが違います</p>
												</div>
												<div class="text-center">
													<button type="submit" class="btn">Login</button>
												</div>
											</form>
										</div>

										<div class="tab-pane fade" id="Signup" role="tabpanel">
											<form id="register-form" method="post" action="signup">
												<div class="form-group">
													<label for="register-userid"></label> <input type="text"
														class="textbox" name="id" id="register-userid"
														placeholder="UserID" required>
														<p id="signup-errorMessage" class="error">
															英数字および記号以外は使えません
														</p>
												</div>
												<div class="form-group">
													<label for="register-nickname"></label> <input type="text"
														class="textbox" name="name" id="register-nickname"
														placeholder="Nickname" required>
												</div>
												<div class="form-group">
													<label for="register-password"></label> <input
														type="password" class="textbox" name="password"
														id="register-password" placeholder="Password" required>
												</div>
												<div class="form-group">
													<label for="register-password"></label> <input
														type="password" class="textbox" name="passwordAgain"
														id="register-password" placeholder="Password Again" required>
												</div>
												<div class="text-center">
													<button type="submit" class="btn">Sign up</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/login.js"></script>
</body>

</html>
