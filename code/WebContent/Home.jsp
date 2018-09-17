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
<title>Home</title>
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
		<table>
			<caption>Account Details</caption>
			<tbody>
				<tr>
					<th>Name</th>
					<td><%=account.getName() %></td>
				</tr>
				<tr>
					<th>Account Number</th>
					<td><%=account.getAccountNumber() %></td>
				</tr>
				<tr>
					<th>Balance</th>
					<td>$ <%=account.getBalance() %></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>