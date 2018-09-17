<%@ page import="other.Extract" %>
<%@ page import="model.*" %>
<%@ page import="dao.*" %>
<%
	Account account = null;
	if(session.getAttribute("loggedAccount") == null) {
		response.sendRedirect(request.getContextPath() + "/Login.jsp");
		return;
	} else {
		try {
			account = (Account) session.getAttribute("loggedAccount");
		} catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/Login.jsp");
			return;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Deposit</title>
</head>
<body>
	<header>
		<h1>Online Banking System</h1>
		<h4>Hello <%=account.getName() %></h4>
	</header>
	<nav>
		<ul>
			<li><a href="Home.jsp">Home</a></li>
			<li><a href="Withdraw.jsp">Withdraw</a></li>
			<li><a href="Deposit.jsp">Deposit</a></li>
			<li><a href="Transfer.jsp">Transfer</a></li>
			<li><a href="Transactions.jsp">Transactions</a></li>
			<li><a href="Logout.jsp">Logout</a></li>
		</ul>
	</nav>
	<div class="content">
		<form action="DepositServlet" method="POST">
			<fieldset>
				<legend>Deposit</legend>
				<label>Your balance</label>
				<input type="text" disabled="disabled" value="<%=account.getBalance() %>">
				<br>
				<label>Amount</label>
				<input type="number" name="amount">
				<br>
				<input type="submit" value="Deposit">
				<br>
				<span class="pass"><%=Extract.getValue(session, "depPass") %></span>
				<span class="fail"><%=Extract.getValue(session, "depFail") %></span>
			</fieldset>
		</form>
	</div>
</body>
</html>