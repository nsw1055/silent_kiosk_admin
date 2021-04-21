<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Login</title>

<!-- Custom fonts for this template-->
<link href="../resources/sb-admin/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../resources/sb-admin/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">
	${error }
	${logout }

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
									</div>
									<form class="user" method="post" action="/admin/login">
										<div class="form-group">
											<input type="text" name="username" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp"
												placeholder="Enter Email Address...">
										</div>
										<div class="form-group">
											<input type="password" name="password" class="form-control form-control-user"
												id="exampleInputPassword" placeholder="Password">
										</div>
										<input type="text" name="_csrf" value="${_csrf.token }"/>
										<div class="input-group">
										  <div class="input-group-prepend">
										    <div class="input-group-text">
										    <input type="radio" name="auth" value="manager" aria-label="Radio button for following text input">
										    </div>
										  </div>
										  <input type="text" value="manager" class="form-control" aria-label="Text input with radio button" readonly="readonly">
										</div>
										<div class="input-group">
										  <div class="input-group-prepend">
										    <div class="input-group-text">
										    <input type="radio" name="auth" value="admin" aria-label="Radio button for following text input">
										    </div>
										  </div>
										  <input type="text" value="admin" class="form-control" aria-label="Text input with radio button" readonly="readonly">
										</div>
										<div class="input-group">
										  <div class="input-group-prepend">
										    <div class="input-group-text">
										    <input type="radio" name="auth" value="advertiser" aria-label="Radio button for following text input">
										    </div>
										  </div>
										  <input type="text" value="advertiser" class="form-control" aria-label="Text input with radio button" readonly="readonly">
										</div>
										
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" name="remember-me" class="custom-control-input" id="customCheck">
													<label class="custom-control-label" for="customCheck">Remember Me</label>
											</div>
										</div>
										<button class="btn btn-primary btn-user btn-block"> Login </button>
										<hr>
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="forgot-password.html">Forgot
											Password?</a>
									</div>
									<div class="text-center">
										<a class="small" href="register.html">Create an Account!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../resources/sb-admin/jquery.min.js"></script>
	<script src="../resources/sb-admin/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../resources/sb-admin/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../resources/sb-admin/sb-admin-2.min.js"></script>

</body>

</html>