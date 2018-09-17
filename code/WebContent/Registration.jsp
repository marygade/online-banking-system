<%@ page import="other.Extract" %>
<!DOCTYPE html>
<html>
<head>
	<title>Registration</title>
</head>
<body>
	<header>
		<h1>Online Banking System</h1>
	</header>
	<nav>
		<ul>
			<li><a href="Login.jsp">Login</a></li>
			<li><a href="Registration.jsp">Registration</a></li>
		</ul>
	</nav>
	<div class="content">
		<form action="RegistrationServlet" method="POST">
			<fieldset>
				<legend>Registration</legend>
				<label>Name</label>
				<input type="text" name="name" autofocus>
				<br>
				<label>Email</label>
				<input type="email" name="email">
				<br>
				<label>Password</label>
				<input type="password" name="password">
				<br>
				<input type="submit" value="Register">
				<br>
				<span class="pass"><%=Extract.getValue(session, "regPass") %></span>
				<span class="fail"><%=Extract.getValue(session, "regFail") %></span>
			</fieldset>
		</form>
	</div>
</body>
</html>