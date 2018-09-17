<%@ page import="other.Extract" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
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
		<form action="LoginServlet" method="POST">
			<fieldset>
				<legend>Login</legend>
				<label>Email</label>
				<input type="email" name="email" autofocus>
				<br>
				<label>Password</label>
				<input type="password" name="password">
				<br>
				<input type="submit" value="Login">
				<br>
				<span class="pass"><%=Extract.getValue(session, "regPass") %></span>
				<span class="fail"><%=Extract.getValue(session, "logFail") %></span>
			</fieldset>
		</form>
	</div>
</body>
</html>